package com.corazon98.dsaw.data.model;

import java.util.ArrayList;
import java.util.List;

public class ReportModel {
    private String author;

    private String dateSubmit;

    private String imageUrl;

    private List<String> lsAnswers;

    public ReportModel() {
        lsAnswers = new ArrayList<>();
    }

    public ReportModel(String imageUrl, List<String> lsAnswers) {
        this.imageUrl = imageUrl;
        this.lsAnswers = lsAnswers;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getLsAnswers() {
        return lsAnswers;
    }

    public void setLsAnswers(List<String> lsAnswers) {
        this.lsAnswers = lsAnswers;
    }

    public String getDateSubmit() {
        return dateSubmit;
    }

    public void setDateSubmit(String dateSubmit) {
        this.dateSubmit = dateSubmit;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
