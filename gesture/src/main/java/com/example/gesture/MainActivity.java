package com.example.gesture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import com.github.chrisbanes.photoview.PhotoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
        photoView.setImageResource(R.drawable.picture);
      //  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); 解除menifeast裡限定 直橫式，視需求決定啦
    }


    /**
     * menifeast 也要設
     * android:configChanges="orientation|screenSize"
     * android:screenOrientation="portrait"
     * 不然 onConfigurationChanged 不work
     * */
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int currentOrientation = getResources().getConfiguration().orientation;

        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.v("TAG", "Landscape !!!");
        }else {
            Log.v("TAG", "portrait !!!");
        }
    }
}
