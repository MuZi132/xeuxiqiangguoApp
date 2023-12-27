package com.example.myapplication;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.LinkedList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private int userid;
    private ListView news_list;
    private NewsAdapter newsAdapter;
    private List<News> newsList;
    private TextView user_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        userid = getIntent().getIntExtra("userid", 0);
        news_list = (ListView) findViewById(R.id.news_list);
        user_info = (TextView) findViewById(R.id.user_info);
        newsList = new LinkedList<News>();
        newsList.add(new News("新闻标题1，这里是新闻标题1", "2023-12-01"));
        newsList.add(new News("新闻标题2，这里是新闻标题2", "2023-12-02"));
        newsList.add(new News("新闻标题3，这里是新闻标题3", "2023-12-03"));
        newsAdapter = new NewsAdapter((LinkedList<News>) newsList, HomeActivity.this);
        news_list.setAdapter(newsAdapter);

        user_info.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // 跳转到用户信息界面
                Intent intent = new Intent(HomeActivity.this, UserInfoActivity.class);
                intent.putExtra("userid", userid);
                startActivity(intent);
            }
        });
    }
}