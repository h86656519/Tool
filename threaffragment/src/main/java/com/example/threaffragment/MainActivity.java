package com.example.threaffragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    /**
     * 1.實驗再fragment 跳傳後，前一個fragment new 出的thread 還是否存在?
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // TODO: 2020/2/13 fragment 跳傳


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(10000);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//                    Log.i(TAG, "我還活著阿~ 沒死");
//                }
//            }
//        }).start();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        AFragment aFragment = new AFragment();
        ft.addToBackStack("home"); //用tag 堆疊，這樣back 實體件才會回到上一頁
        ft.replace(R.id.fragment_layout, aFragment);
        ft.commit();
    }


}
