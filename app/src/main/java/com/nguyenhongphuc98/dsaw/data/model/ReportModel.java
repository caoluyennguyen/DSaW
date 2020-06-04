package com.nguyenhongphuc98.dsaw.data.model;

import java.util.ArrayList;
import java.util.List;

public class ReportModel {

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
}
