package com.blogspot.ramannada.quizapp.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.blogspot.ramannada.quizapp.R;
import com.blogspot.ramannada.quizapp.base.BaseActivity;
import com.blogspot.ramannada.quizapp.data.SharedData;
import com.blogspot.ramannada.quizapp.question.MainActivity;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_welcome);

        final EditText etName = findViewById(R.id.et_name);
        Button btnEnter = findViewById(R.id.btn_enter);

        SharedData.getSharedData().clearScore();

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedData.getSharedData().setName(etName.getText().toString());

                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            }
        });
    }
}
