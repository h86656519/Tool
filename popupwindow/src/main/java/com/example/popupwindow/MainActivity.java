package com.example.popupwindow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity {
    private PopupWindow popupWindow;
    private View contentView;
    private Button button;
    private ConstraintLayout view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopwindow();
            }
        });
        view = findViewById(R.id.parent_view);

    }

    private void showPopwindow() {
        //載入彈出框的佈局
        contentView = LayoutInflater.from(this).
                inflate(R.layout.bottom_pop, null);
//這邊會決定最後的大小
        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        popupWindow.setFocusable(true);
        //注意  要是點選外部空白處彈框訊息  那麼必須給彈框設定一個背景色  不然是不起作用的
        popupWindow.setBackgroundDrawable(new BitmapDrawable()); //也可以用xml 來設
        //點選外部消失
        popupWindow.setOutsideTouchable(true);
        //設定可以點選
        popupWindow.setTouchable(true);
        //進入退出的動畫，指定剛才定義的style
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        // 按下android回退物理鍵 PopipWindow消失解決

//        showAtLocation 是相對於整個佈局
//        showAsDropDown 是相對於某個元件

        //底部  第一個參數是parentView，xy 是偏移
//        popupWindow.showAtLocation(button, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        //頂部
        popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, 100, 0); //上面設 match_parent 偏移等於無用
//        popupWindow.showAsDropDown(button,  100, 0);

    }
}
