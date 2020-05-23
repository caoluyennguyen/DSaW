package com.nguyenhongphuc98.dsaw.data.model;

import java.util.List;

public class Answer {
    String surveyKey;
    String accountKey;
    List<String> answerKey;
    String imageFile;
    String textAnswer;

    public Answer(String answer) {
        this.textAnswer = answer;
    }

    public String getTextAnswer() {
        return textAnswer;
    }
}
