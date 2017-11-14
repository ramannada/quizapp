package com.blogspot.ramannada.quizapp;

import android.app.Application;

import com.blogspot.ramannada.quizapp.data.SharedData;

/**
 * Created by ramannada on 11/13/2017.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SharedData.init(this);
    }
}
