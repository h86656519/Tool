package com.example.tool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * 練習繼承
 */
public class MainActivity extends BaseAvtivity {
    Button button, button2;
    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v); //所有按鈕功能都有 基本功能
        //也要實現2個功能
        switch (v.getId()) {
            case R.id.button:
//                super.onClick(v);   //只有 button 才有基本功能，button2 沒有基本功能
                Log.i(TAG, "button 功能一");
                break;

            case R.id.button2:
                Log.i(TAG, "button2 功能一");
                break;

        }
    }

}