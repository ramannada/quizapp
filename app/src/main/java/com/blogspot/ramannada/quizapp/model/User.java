package com.blogspot.ramannada.quizapp.model;

/**
 * Created by ramannada on 11/17/2017.
 */

public class User {
    int id;
    String name;
    int score;

    public User(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public User(int id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
