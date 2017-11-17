
package com.blogspot.ramannada.quizapp.question;

import android.content.Intent;
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

import com.blogspot.ramannada.quizapp.App;
import com.blogspot.ramannada.quizapp.R;
import com.blogspot.ramannada.quizapp.user.ResultActivity;
import com.blogspot.ramannada.quizapp.data.SharedData;
import com.blogspot.ramannada.quizapp.model.Question;


public class QuestionFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {
//    DatabaseQuiz db;
    View rootView;
    TextView tvQuestion;
    RadioGroup radioGroup;
    int point = 20;
    int page;
    String answer;

    public QuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        db = new DatabaseQuiz(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_question, container, false);
        tvQuestion = rootView.findViewById(R.id.tv_question);
        radioGroup = rootView.findViewById(R.id.radio_group_answer);
        Button btnNext = rootView.findViewById(R.id.btn_next);
        page = getArguments().getInt("page");
        setQuestion(page);

        radioGroup.setOnCheckedChangeListener(this);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getPage()+1 < App.db.getQuestionSize()) {
                    if (answer != null) {
                        storeAnswer(answer, getPage());

                        ((MainActivity)getActivity()).setCurrentPage(getPage()+1);

                    } else {
                        Toast.makeText(getActivity(), "Pilih jawaban terlebih dahulu",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (answer != null) {
                        storeAnswer(answer, getPage());
                        checkAnswer();

                       Intent i = new Intent(getActivity(), ResultActivity.class);
                       i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP
                               | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                       startActivity(i);
                    } else {
                        Toast.makeText(getActivity(), "Pilih jawaban terlebih dahulu",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return rootView;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.radio_answer_a:
                answer = "a";
                break;
            case R.id.radio_answer_b:
                answer = "b";
                break;
            case R.id.radio_answer_c:
                answer = "c";
                break;
            case R.id.radio_answer_d:
                answer = "d";
                break;
            default:
                answer = null;
                break;
        }
    }

    void setQuestion(int i) {

//        Toast.makeText(getActivity(), "soal no " + i , Toast.LENGTH_SHORT).show();
        Question q = App.db.getQuestion(i);

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

    private void storeAnswer(String s, int i) {
        ((MainActivity)getActivity()).storeAnswer[i] =  s;
    }

    private void checkAnswer() {
        String[] s = ((MainActivity)getActivity()).storeAnswer;

        for (int i = 0; i<s.length; i++) {
            if (s[i].equals(App.db.getQuestion(i+1).getKeyAnswer())) {
                SharedData.getSharedData().addScore(point);
            }
        }
    }

    private int getPage() {
        return ((MainActivity)getActivity()).getPage();
    }


}
