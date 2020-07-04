package com.example.intentservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        test1();
        test2();

    }

    public void test1() {
        //只有一個intent
        Intent intent = new Intent(this, MyIntentService.class);
        intent.putExtra("task", "播放音乐");
        startService(intent);
        intent.putExtra("task", "播放视频");
        startService(intent);
        intent.putExtra("task", "播放图片");
        startService(intent);
    }

    public void test2() {
        //new 出多個intent
        Intent intent = new Intent(this, MyIntentService.class);
        intent.putExtra("task", "播放音乐");
        startService(intent);

        Intent intent2 = new Intent(this, MyIntentService.class);
        intent2.putExtra("task", "播放音乐2");
        startService(intent2);
    }

}
