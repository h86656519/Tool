package com.example.sharedpreferences;

import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener{
    View view;
    EditText ed_input;
    Button btn_next;
    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_main, container, false);
        ed_input = view.findViewById(R.id.ed_input);
        btn_next = view.findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);
        return view;

    }

    /**
     * MODE_PRIVATE 只允許本應用程式內存取
     * MODE_MULTI_PROCESS 允許多個行程同時存取這個設定檔，這個設定在Android 2.3(含)以前都是預設啟用的，但2.3之後得要指定這個參數才允許多行程同時存取設定檔。
     * MODE_WORLD_READABLE 讓手機中的所有app都能讀取這個設定檔，因為風險性太高，從API 17版開始就不建議使用這個參數了。
     * MODE_WORLD_WRITEABLE 讓手機中的所有app都能存取、寫入這個設定檔
     * commit() 有回傳 boolean結果，是異步執行 = 不會立刻存擋
     * apply沒有回傳結果，是跟 uithread 同步執行 = 直接立刻寫進檔案
     * */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                SharedPreferences pref = getActivity().getSharedPreferences("mySave", MODE_PRIVATE);//會建立一個 mySave.xml
                pref.edit()
                        .putString("USER", ed_input.getText().toString())
                        .commit();
//                      .apply();
//                jumpFragment(new MainFragment2());
                String input = ed_input.getText().toString();
                if (input.equals("123")){
                    ed_input.setPaintFlags(ed_input.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG); //新增文字刪除線
                }else {
                    ed_input.setPaintFlags(0); //清除線
                }
                break;
        }
    }

    public void jumpFragment(Fragment target) {
        try {
            FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_layout, target);
            ft.addToBackStack("MainFragment");
            ft.commit();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
