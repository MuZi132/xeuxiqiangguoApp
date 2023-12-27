package com.example.myapplication;

import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.*;

public class UserInfoActivity extends AppCompatActivity {
    private User user;
    private UserRepository userRepository;
    private ListView listView;
    private String[] titles = new String[]{"姓名", "手机号","密码"};
    private String[] contents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        userRepository = new UserRepository(UserInfoActivity.this);
        user = userRepository.getUserById(getIntent().getIntExtra("userid", 0));
        contents = new String[]{user.getName(), user.getUsername(), user.getPassword()};
        listView = findViewById(R.id.user_info_list);
        listView.addHeaderView(getLayoutInflater().inflate(R.layout.user_info_header, null));
        List<Map<String, Object>> listitem = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < titles.length; i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("title", titles[i]);
            map.put("content", contents[i]);
            listitem.add(map);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter
                (this, listitem, R.layout.user_info_item_layout, new String[]{"title", "content"},
                        new int[]{R.id.title, R.id.content});
        listView.setAdapter(simpleAdapter);
    }
}