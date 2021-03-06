package com.example.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.jakewharton.rxbinding3.view.RxView;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * 1.利用Rxjava 來擋住過快的二次連點，防手抖
 * 2.建立一個非同步事件(ex:呼api，撈db 等等...)
 * 3.建立多個任務同時執行，優化寫法
 * 4.功能實做 interval，在task2上
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Button btnOk;
    CountDownLatch latch = new CountDownLatch(1);

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOk = findViewById(R.id.btnOk);
        //寫法一
        // RxView.clicks(btnOk).throttleFirst(1, TimeUnit.SECONDS).subscribe(observer);
        //寫法二
//        RxView.clicks(btnOk).throttleFirst(1, TimeUnit.SECONDS).subscribe(click -> {
//            Log.i("ClickActivity", "點太快");
//        });
        Observable<Integer> observableTask1 = task1();
        Observable<Integer> observableTask2 = task2();

        //  observableTask1.subscribe(observer);
        observableTask2.subscribe(observer);
    }

    private Observable<Integer> task2() {
        return Observable.
                interval(1, TimeUnit.SECONDS) //每個1秒呼一次 onNext
                .create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        for (int i = 1; i <= 15; i++) {
                            try {
                                Thread.sleep(1000);
                                //  if (i == 15) {
//                                    emitter.onNext(15);
                                emitter.onNext(i);
                                //}
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    }).subscribeOn(Schedulers.io()); //subscribeOn() 控制 onNext 的 Thread

    }

    private Observable<Integer> task1() {
        //  1. 創建被觀察者 Observable
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            // 2. 在subscribe（）裡定義需要被發送的事件，時間到了，api回來了....
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                // 通过 ObservableEmitter类对象产生事件并通知观察者
                // ObservableEmitter类介绍
                // a. 定义：事件发射器
                // b. 作用：定义需要发送的事件 & 向观察者发送事件
//                emitter.onNext(1);
//                emitter.onNext(2);
//                emitter.onNext(3);
                for (int i = 1; i <= 10; i++) {
                    try {
                        Thread.sleep(1000);
                        if (i == 5) {
                            emitter.onNext(i);
                        }

                        if (i == 10) {
                            emitter.onNext(i);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).subscribeOn(Schedulers.io());
    }

    // 创建观察者
    Observer observer = new Observer() {
        private Disposable mDisposable;

        @Override
        public void onSubscribe(Disposable d) {
            Log.d(TAG, "开始采用subscribe连接");
        }

        @Override
        public void onNext(Object value) {
            //   Log.i("ClickActivity", "點太快");

            if (value.toString().equals("5")) {
                Log.d(TAG, "5秒到了");
            }
            if (value.toString().equals("10")) {
                Log.d(TAG, "10秒到了");
            }

            if (value.toString().equals("15")) {
                Log.d(TAG, "15秒到了");
            }
            Log.d(TAG, "value" + value);
            // mDisposable.dispose(); //切斷观察者和被观察者的连接
        }

        @Override
        public void onError(Throwable e) {
            Log.d(TAG, "走了 onError");
        }

        @Override
        public void onComplete() {
            Log.d(TAG, "就不會走 onComplete，反之");
            latch.countDown();
        }
    };

}
