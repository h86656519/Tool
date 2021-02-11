package com.example.rxjava;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
 * 建立2個不同步任務A,B，無論誰先完成都要等另一個任務完成後才能執行任務 C
 * */
public class MainActivity2 extends AppCompatActivity {
    private static final String TAG = "MainActivity2";
    private CompositeDisposable comparable = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable<Integer> taskA = task1(15); //假設taskA 要花5秒完成

        Observable<Integer> taskB = task1(10);//假設taskB 要花10秒完成

        Observable.merge(taskA, taskB)
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        comparable.add(d);
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "時間： " + value);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "出錯惹");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "任務完成");
                    }
                });
    }

    private Observable<Integer> task1(int time) {
        return Observable
                .create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) {
                        for (int i = 1; i <= time; i++) {
                            try {
                                Thread.sleep(1000);
                                emitter.onNext(i);
//                                if (i == 5) // 假設情境: 5秒就算完成了
                                if (i == time)
                                    emitter.onComplete(); //條件完成後要呼叫onComplete
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).subscribeOn(Schedulers.io()); //subscribeOn() 控制 onNext 的 Thread
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        comparable.dispose();
    }
}
