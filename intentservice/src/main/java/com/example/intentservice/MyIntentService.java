package com.example.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {
private final String TAG = "MyIntentService";
    public MyIntentService() {
        super("MyIntentService");
        Log.i(TAG, "MyIntentService " );
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate" );
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand" );
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //這裡是背景執行
        Log.i(TAG, "工作线程是: " + Thread.currentThread().getName() );
        String task = intent.getStringExtra("task");
        Log.i(TAG, "任务是 :" + task);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
