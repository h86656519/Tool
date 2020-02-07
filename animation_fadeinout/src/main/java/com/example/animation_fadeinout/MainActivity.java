package com.example.animation_fadeinout;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import static android.view.animation.Animation.ABSOLUTE;
import static android.view.animation.Animation.RELATIVE_TO_PARENT;
import static android.view.animation.Animation.RELATIVE_TO_SELF;

/**
 * 用animation 實作淡入淡出
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "MainActivity";
    private TransitionDrawable transitionDrawable;
    ImageView iv_alpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        iv_alpha = (ImageView) findViewById(R.id.iv_alpha);
        iv_alpha.setImageResource(R.drawable.rotate);
    }

    @Override
    protected void onResume() {
        super.onResume();
        int dw =  (iv_alpha.getDrawable().getBounds().width());
        Log.i(TAG, "getWidth: " + iv_alpha.getWidth());
        Log.i(TAG, "dw: " + dw);
        Log.i(TAG, "getIntrinsicWidth: " + iv_alpha.getDrawable().getIntrinsicWidth());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                fadeInOut();
                break;
            case R.id.button2:
                fadeInOut2();
                break;
            case R.id.button3:
                fadeInOut3();
                break;
            case R.id.button4:
                remRotate();
                break;
            case R.id.button5:
                rotateTranslate();
                break;
            case R.id.button6:
                rotateTranslateByXml();
                break;
        }
    }

    /**
     * 效果:淡入淡出
     */
    private void fadeInOut() {
        //淡入淡出动画需要先设置一个Drawable数组，用于变换图片
        Drawable[] drawableArray = {
                this.getDrawable(R.drawable.alpha_begin),
                this.getDrawable(R.drawable.alpha_end)
        };
        transitionDrawable = new TransitionDrawable(drawableArray);
        iv_alpha.setImageDrawable(transitionDrawable);
        transitionDrawable.startTransition(5000);
    }

    /**
     * 用animation來達到淡入淡出效果
     */
    private void fadeInOut2() {
        iv_alpha.setImageResource(R.drawable.live_pluse_sparklean);
//        Animation am = new TranslateAnimation(10,10,80,200); 移動
        Animation am = new AlphaAnimation(0.1f, 1.0f);
        //動畫開始到結束的時間
        am.setDuration(1000);
        // 動畫重覆次數 (-1表示一直重覆，0表示不重覆執行，所以只會執行一次)
        am.setRepeatCount(-1);
        // RESTART表示從頭開始，REVERSE表示從尾倒播
        am.setRepeatMode(Animation.REVERSE);
        //將動畫寫入ImageView
        am.startNow();
    }

    /**
     * 建立一個xml(alphactions) 來實現，其實也就是把 Animation 要做的動作寫在xml
     */
    private void fadeInOut3() {
        iv_alpha.setImageResource(R.drawable.unnamed);
        Animation am = AnimationUtils.loadAnimation(this, R.anim.alphactions);
        iv_alpha.setAnimation(am);
        am.startNow();
    }

    /**
     * 旋轉效果
     */
    private void remRotate() {
        iv_alpha.setImageResource(R.drawable.rotate);
        Animation am = new RotateAnimation(0.0f, // //起始角度
                360.0f, //結束角度
                ABSOLUTE, //X轴的伸缩模式
                // ，ABSOLUTE: 絕對座標，旋转中心在屏幕上X轴的值
                // ，RELATIVE_TO_SELF: 想對於自身座標，为旋转中心在父控件水平位置百分比，如0.5表示在父控件水平方向中间位置
                // ，RELATIVE_TO_PARENT :相對於父控件座標，旋转中心在控件自身水平位置百分比，如果X和Y的Value都设置为0.5，则该控件以自身中心旋转
                300,//設定x旋轉中心點
                ABSOLUTE, //Y轴的伸缩模式
                300);//設定y旋轉中心點
        am.setDuration(1000);
        am.setRepeatCount(-1);
        iv_alpha.setAnimation(am);
        am.startNow();
    }

    /*
     * 綜合效果 自我旋轉 + 向右位移
     * 做法一:要取得 圖片要用xml src去設，getWidth才可以抓到值
     * 做法二:若想讓 xml 設wrap_content 的話，就等於是讓圖去決定大小，所以只能直接去抓圖片來取得長寬值
     * */
    private void rotateTranslate() { //作法二
        iv_alpha.setImageResource(R.drawable.rotate);

        int dw =  (iv_alpha.getDrawable().getBounds().width()); // 不能用 iv_alpha.getWidth() or iv_alpha.getMeasuredWidth()，只能直接去抓原始圖片
        int dh =  (iv_alpha.getDrawable().getBounds().height());
        AnimationSet animationSet = new AnimationSet(true); //將AnimationSet 改成 AnimatorSet

        TranslateAnimation moveLefttoRight = new TranslateAnimation(0, 200, 0, 0);
        moveLefttoRight.setDuration(2000);

        Animation rotateAnimationm = new RotateAnimation(0,
                360,
                ABSOLUTE,
                dw / 2,
                ABSOLUTE,
                dh / 2);
        rotateAnimationm.setDuration(2000);
      //  Log.i(TAG, "dwww: " + dw);
//        Log.i(TAG, "getIntrinsicWidth: " + iv_alpha.getDrawable().getIntrinsicWidth());
        animationSet.addAnimation(rotateAnimationm); //旋轉要先執行，也可以改用AnimatorSet來確保
        animationSet.addAnimation(moveLefttoRight);

        iv_alpha.startAnimation(animationSet);
    }



    /**
     * flash 效果
     */
    private void rotateTranslateByXml() {
        iv_alpha.setImageResource(R.drawable.red);
        Animation xml = AnimationUtils.loadAnimation(this, R.anim.rotate_translate);
        iv_alpha.startAnimation(xml);
    }

//    private void aaa(){
//        AnimatorSet animationSet = new AnimatorSet();
//        TranslateAnimation moveLefttoRight = new TranslateAnimation(0, 200, 0, 0);
//        moveLefttoRight.setDuration(2000);
//        Animation rotateAnimationm = new RotateAnimation(0, 360, ABSOLUTE, 0.5f, ABSOLUTE, 0.5f);
//        rotateAnimationm.setDuration(2000);
//        iv_alpha.startAnimation(animationSet);
//    }
}
