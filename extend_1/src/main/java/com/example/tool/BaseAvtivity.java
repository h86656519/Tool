package com.example.tool;

import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class BaseAvtivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        Log.i("BaseAvtivity", "基本功能"); //功能
    }

}
