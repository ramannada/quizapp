package com.blogspot.ramannada.quizapp.data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ramannada on 11/13/2017.
 */

public class SharedData {
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private final String DATA_NAME = "com.blogspot.ramannada.quizapp.shareddata";
    private final int PREFERENCES_MODE = 0;
    private static String NAME = "name";
    private static String SCORE = "score";
    public static SharedData sharedData;

    public SharedData(Context context) {
        sharedPreferences = context.getSharedPreferences(DATA_NAME, PREFERENCES_MODE);
        editor = sharedPreferences.edit();
    }

    public static void init(Context context) {
        sharedData = new SharedData(context);
    }

    public synchronized static SharedData getSharedData() {
        return sharedData;
    }

    public void setName(String s) {
        editor.putString(NAME, s);
        editor.commit();
    }

    public String getName() {
        return sharedPreferences.getString(NAME, "");
    }

    public int getScore() {
        return sharedPreferences.getInt(SCORE, 0);
    }

    public void addScore(int i) {
        int score = getScore() + i;
        editor.putInt(SCORE, score);
        editor.commit();
    }

    public void clearScore() {
        editor.putInt(SCORE, 0);
        editor.commit();
    }
}
