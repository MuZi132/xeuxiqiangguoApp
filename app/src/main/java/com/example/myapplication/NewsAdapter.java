package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;

public class NewsAdapter extends BaseAdapter {
    private LinkedList<News> newsList;
    private Context context;

    public NewsAdapter(LinkedList<News> newsList, Context context) {
        this.newsList = newsList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.news_item_layout, parent, false);
        TextView titleTextView = (TextView) convertView.findViewById(R.id.titleTextView);
        TextView dateTextView = (TextView) convertView.findViewById(R.id.dateTextView);
        TextView start = (TextView) convertView.findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MyService.class);
                if (MyService.isPlaying == false){
                    context.startService(intent);
                    start.setText("暂停");
                }else {
                    context.stopService(intent);
                    start.setText("播放");
                }
            }
        });
        titleTextView.setText(newsList.get(position).getTitle());
        dateTextView.setText(newsList.get(position).getDate());
        return convertView;
    }
}


// 设置数据
