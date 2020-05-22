package com.example.sharedpreferences;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    /**
     * 練習sharedPreferences 值的傳遞
     * 就是cookie的概念
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jumpFragment(new MainFragment());
    }


    public void jumpFragment(Fragment target) {
        try {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_layout, target);
            ft.addToBackStack("home");
//            ft.commit();
            ft.commitAllowingStateLoss();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
