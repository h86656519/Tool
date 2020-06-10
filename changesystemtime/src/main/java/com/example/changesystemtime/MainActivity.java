package com.example.changesystemtime;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    /**
     * 修改系統時間
     * 1.在Manifeest 加  android:sharedUserId="android.uid.system"
     * 2.製作一個包了pem和pk8的簽名擋
     * 3.要產出到apk才會包入 = 不能直接部，要用apk裝入
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(5000);
//                    PowerManager pManager=(PowerManager) getSystemService(POWER_SERVICE);
//                    pManager.reboot("for Leo test");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String now = getCurrentTime();
                Log.i(TAG, "before: " + now);
//                boolean aaa = SystemClock.setCurrentTimeMillis(when); //沒反應不會變，也沒看到錯誤log

                Calendar c = Calendar.getInstance();
                c.set(2010, 1, 1, 12, 00, 00);
                AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
                am.setTime(c.getTimeInMillis());
                Log.i(TAG, "after003: " + getCurrentTime());
            }
        }).start();

    }

    public static String getCurrentTime() {
        SimpleDateFormat timeFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        timeFmt.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return timeFmt.format(new Date());
    }
}
