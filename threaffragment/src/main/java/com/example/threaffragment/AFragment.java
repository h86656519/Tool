package com.example.threaffragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AFragment extends Fragment {

    View view;

    public AFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_a, container, false);

        Button btn_next = view.findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
//                FragmentTransaction ft = fm.beginTransaction();
                BFragment bFragment = new BFragment();

//                ft.replace(R.id.fragment_layout, bFragment);
//                ft.commit();
//                ft.commitAllowingStateLoss();


                FragmentTransaction ft = fm.beginTransaction();
                ft.addToBackStack("AFragment"); //用tag 堆疊，這樣back 實體件才會回到上一頁
                BFragment bf = (BFragment) fm.findFragmentById(R.id.fragment_b);

                if (bf == null || bf != bFragment) {
                    ft.add(R.id.fragment_b, bFragment);
                }

                List<Fragment> ls = fm.getFragments();

                if (ls != null) {
                    for (Fragment f : ls) {
                        ft.hide(f);
                    }

                }

                ft.show(bFragment);

                ft.commit();

            }
        });
        return view;
    }

}
