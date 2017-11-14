
package com.blogspot.ramannada.quizapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.ramannada.quizapp.data.DatabaseQuiz;
import com.blogspot.ramannada.quizapp.model.Question;


public class QuestionFragment extends Fragment {
    DatabaseQuiz db;
    View rootView;
    TextView tvQuestion;
    RadioGroup radioGroup;
    int page = 1;

    public QuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseQuiz(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_question, container, false);
        tvQuestion = rootView.findViewById(R.id.tv_question);
        radioGroup = rootView.findViewById(R.id.radio_group_answer);
        Button btnNext = rootView.findViewById(R.id.btn_next);

        if (page < db.getQuestionSize()) {
            setQuestion();
        }

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (page < db.getQuestionSize()) {
                    Toast.makeText(getActivity(), "Page " + String.valueOf(page) + " questionSize" +String.valueOf(db.getQuestionSize()), Toast.LENGTH_SHORT).show();
                    page += 1;
                    setQuestion();
                } else {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_content, new ResultFragment())
                            .addToBackStack(null)
                            .commit();
                }
            }
        });

        return rootView;
    }

    private void setQuestion() {


        Question q = db.getQuestion(page);

        tvQuestion.setText(q.getQuestion());

        String[] s = new String[4];
        s[0] = q.getAnswerA();
        s[1] = q.getAnswerB();
        s[2] = q.getAnswerC();
        s[3] = q.getAnswerD();

        setRadioText(s);

    }

    private void setRadioText(String[] s) {
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            ((RadioButton)radioGroup.getChildAt(i)).setText(s[i]);
        }
    }

}
