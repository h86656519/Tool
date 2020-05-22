package com.example.sharedpreferences;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment2 extends Fragment {
    View view;
    TextView tv_result;
    public MainFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       view= inflater.inflate(R.layout.fragment_main2, container, false);
        tv_result = view.findViewById(R.id.tv_result);
        String userid = getActivity().getSharedPreferences("mySave", MODE_PRIVATE)
                .getString("USER", "99999999999");
        tv_result.setText(userid);
        return view;
    }
}
