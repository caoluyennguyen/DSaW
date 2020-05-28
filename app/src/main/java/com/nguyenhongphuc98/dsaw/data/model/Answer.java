package com.nguyenhongphuc98.dsaw.data.model;

import java.util.List;

public class Answer {
    String surveyKey;
    String accountKey;
    List<String> answerKey;
    String image_file;
    String text_answer;

    public Answer(String answer) {
        this.text_answer = answer;
    }

    public String getTextAnswer() {
        return text_answer;
    }
}
