package com.example.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.jakewharton.rxbinding3.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * 利用Rxjava 來擋住過快的二次連點，防手抖
 * */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOk = findViewById(R.id.btnOk);
        //寫法一
       // RxView.clicks(btnOk).throttleFirst(1, TimeUnit.SECONDS).subscribe(observer);
        //寫法二
        RxView.clicks(btnOk).throttleFirst(1, TimeUnit.SECONDS).subscribe(click -> {
            Log.i("ClickActivity", "點太快");
        });

    }

    // 创建观察者
    Observer observer = new Observer() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(Object value) {
            Log.i("ClickActivity", "點太快");
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    };
}
