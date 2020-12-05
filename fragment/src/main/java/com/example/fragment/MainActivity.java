package com.example.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/*
* 實作目標一: 了解fragment 跳轉
* 實作目標二: 實作新的back OnBackPressedCallback
* */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    MyFragment1 fragment1;
    MyFragment2 fragment2;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeFragment(fragment1);
//                hideFragment(fragment2);
            }
        });
        fragment1 = MyFragment1.newInstance();
        fragment2 = MyFragment2.newInstance();
//        replaceFragment(fragment1);
//        addFragment(fragment2);

        //情境 先add 在replace
        //結果: fragment1
        addFragment(fragment1);
        addFragment(fragment2);
//        replaceFragment(fragment2);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, fragment); //container 只會有一個view
//        transaction.add(R.id.container, fragment); //container 裡的view 會疊加上去
        transaction.addToBackStack(fragment.getTag()); //有多少個fragment
        transaction.commit();
    }

    private void addFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.container, fragment); //container 裡的view 會疊加上去
        transaction.addToBackStack(fragment.getTag()); //有多少個fragment
        transaction.commit();
    }

    private void removeFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.remove(fragment); //只走到onDestroyView 沒到onDestroy，移除的只有view，並不是整個fragment
        transaction.commit();
    }

    private void hideFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.hide(fragment); //只會停在onResume
        transaction.commit();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy:");
    }
}