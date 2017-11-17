package com.blogspot.ramannada.quizapp.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blogspot.ramannada.quizapp.App;
import com.blogspot.ramannada.quizapp.R;
import com.blogspot.ramannada.quizapp.base.BaseActivity;
import com.blogspot.ramannada.quizapp.data.SharedData;
import com.blogspot.ramannada.quizapp.model.User;

public class ResultActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView tvName = findViewById(R.id.tv_name);
        TextView tvScore = findViewById(R.id.tv_score);

        tvName.setText(SharedData.getSharedData().getName());
        tvScore.setText(String.valueOf(SharedData.getSharedData().getScore()));
    }

    public void tryAgain(View view) {
        Intent i = new Intent(ResultActivity.this, WelcomeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    public void saveResult(View view) {
        App.db.saveUser(new User(SharedData.getSharedData().getName(),
                        SharedData.getSharedData().getScore())
        );
    }
}
