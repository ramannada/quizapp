package com.blogspot.ramannada.quizapp.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blogspot.ramannada.quizapp.App;
import com.blogspot.ramannada.quizapp.R;
import com.blogspot.ramannada.quizapp.model.User;

import java.util.List;

public class ListScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_score);

        List<User> users = App.db.getAllUser();

        RecyclerView recyclerView = findViewById(R.id.recycler_view_user);
        UserAdapter userAdapter = new UserAdapter(this, users);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(userAdapter);

    }
}
