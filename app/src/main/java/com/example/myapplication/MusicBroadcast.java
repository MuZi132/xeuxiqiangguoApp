package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MusicBroadcast extends BroadcastReceiver {
    /*需要这几个常量
    1.action、key；
    2.play、pause。
     */
    public static final String ACTION = "MusicReceiver.ACTION";
    public static final String ACTION_KEY = "action_type";
    public static final String ACTION_PLAY = "play";
    public static final String ACTION_PAUSE = "pause";
    //接收广播
    @Override
    public void onReceive(Context context, Intent intent) {//用来接收到广播后做出的响应
        //第一步：接收到的广播内容，给action_type 接收
        String actionType = intent.getStringExtra(ACTION_KEY);
        //第二步：编写接收到广播后的响应动作
        if (actionType != null) {//判空很重要，避免空指针
            Intent serviceintent = new Intent(context, MyService.class);
            switch (actionType) {
                case MusicBroadcast.ACTION_PLAY:
                    context.startService(serviceintent);
                    Toast.makeText(context, "静态注册:接收到播放的广播", Toast.LENGTH_SHORT).show();
                    break;
                case MusicBroadcast.ACTION_PAUSE:
                    if (MyService.isPlaying) {
                        context.stopService(serviceintent);
                        Toast.makeText(context, "静态注册:接收到暂停的广播", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }
}
