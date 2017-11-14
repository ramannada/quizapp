package com.blogspot.ramannada.quizapp.model;

/**
 * Created by ramannada on 11/13/2017.
 */

public class Question {
    private int id;
    private String question;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private String keyAnswer;

    public Question(int id, String question, String answerA, String answerB, String answerC, String answerD, String keyAnswer) {
        this.id = id;
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.keyAnswer = keyAnswer;
    }

    public int getId(){
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswerA() {
        return answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public String getKeyAnswer() {
        return keyAnswer;
    }
}
