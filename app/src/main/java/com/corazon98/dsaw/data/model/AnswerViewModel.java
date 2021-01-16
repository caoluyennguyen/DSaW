package com.corazon98.dsaw.data.model;

import java.util.List;

public class AnswerViewModel {

    private String questionTitle;

    private List<String> answers;

    public AnswerViewModel() {}

    public AnswerViewModel(String questionTitle, List<String> answers) {
        this.questionTitle = questionTitle;
        this.answers = answers;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}
