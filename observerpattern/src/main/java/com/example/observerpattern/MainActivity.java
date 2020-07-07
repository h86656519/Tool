package com.example.observerpattern;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


/*
 * 練習Observer Pattern
 * observer 觀察者，訂閱者
 * subject(Observable) 主體/目標 ，被訂閱者
 * 1.建立Observer和Observable 的 interface
 * 2.建立一個 new 去implement Observable
 * 3.建立一個 Reader 去implement Observer
 * 4.建立一個 Reader 和 New 變數
 * 5.Reader 變數去 訂閱 new的變數
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
