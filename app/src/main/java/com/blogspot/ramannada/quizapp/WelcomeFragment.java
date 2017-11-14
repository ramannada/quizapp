package com.blogspot.ramannada.quizapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blogspot.ramannada.quizapp.data.SharedData;


/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeFragment extends Fragment {


    public WelcomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_welcome, container, false);

        final EditText etName = rootView.findViewById(R.id.et_name);
        Button btnEnter = rootView.findViewById(R.id.btn_enter);

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedData.getSharedData().setName("labib");

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_content, new QuestionFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        return rootView;
    }

}
