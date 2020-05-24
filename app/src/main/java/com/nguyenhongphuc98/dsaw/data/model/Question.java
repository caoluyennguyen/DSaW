package com.nguyenhongphuc98.dsaw.data.model;

import java.util.ArrayList;

public class Question {
    String id;
    ArrayList<Answer> mAnswer;
    String survey;
    String title;
    String type;

    public Question(String id, ArrayList<Answer> lsAnswer, String survey, String title, String type)
    {
        this.id = id;
        mAnswer = lsAnswer;
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

    public ArrayList<Answer> getAnswers() {
        return mAnswer;
    }

    public String getType() {
        return type;
    }
}
