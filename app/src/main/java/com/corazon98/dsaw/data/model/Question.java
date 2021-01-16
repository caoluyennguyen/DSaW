package com.corazon98.dsaw.data.model;

import java.util.ArrayList;

public class Question {
    ArrayList<String> answers;
    String id;
    String survey;
    String title;
    String type;

    public Question() {
        answers = new ArrayList<>();
    }

    public Question(String id, ArrayList<String> lsAnswer, String survey, String title, String type)
    {
        this.id = id;
        answers = lsAnswer;
        this.survey = survey;
        this.title = title;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public String getSurvey() {
        return survey;
    }

    public String getType() {
        return type;
    }


    public void setSurvey(String survey) {
        this.survey = survey;
    }

    public void setmAnswers(ArrayList<String> mAnswers) {
        this.answers = mAnswers;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }
}
