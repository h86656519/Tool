package com.example.constraintlayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.view.View;

import com.example.constraintlayout.databinding.DynamicLayoutBinding;

public class MainActivity extends AppCompatActivity {
    private DynamicLayoutBinding binding;

    /*
     *動態設定 constraintlayout
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.dynamic_layout);
        binding = DynamicLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move();
            }
        });
    }

    private void move() {
        ConstraintSet constraintSet = new ConstraintSet();
        //要先把constraintlayout 複製起來
        constraintSet.clone(binding.constraintlayout);
        //開始設定
//        connect(“移動view”,移動view的方向,"嵾考view",嵾考view 的方向)
        //layout_constraintStart_toEndOf
        constraintSet.connect(binding.button.getId(), ConstraintSet.START, binding.textview.getId(), ConstraintSet.END);
        //layout_constraintBottom_toTopOf
        constraintSet.connect(binding.button.getId(), ConstraintSet.BOTTOM, binding.textview.getId(), ConstraintSet.TOP);

        constraintSet.applyTo(binding.constraintlayout); //送出設定
    }

}