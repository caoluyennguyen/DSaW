package com.nguyenhongphuc98.dsaw.data.model;

import java.util.ArrayList;

public class Question {
    String id;
    ArrayList<String> mAnswers;
    String survey;
    String title;
    String type;

    public Question(String id, ArrayList<String> lsAnswer, String survey, String title, String type)
    {
        this.id = id;
        this.mAnswers = lsAnswer;
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

    public ArrayList<String> getAnswers() {
        return mAnswers;
    }

    public String getSurvey() {
        return survey;
    }

    public String getType() {
        return type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSurvey(String survey) {
        this.survey = survey;
    }
}
