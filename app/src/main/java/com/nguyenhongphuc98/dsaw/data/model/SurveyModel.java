package com.nguyenhongphuc98.dsaw.data.model;

public class SurveyModel {

    // Survey model mean survey to apply to listview to show
    // It can be not same model map to database

    private String id;

    private String count;

    private String name;

    public SurveyModel() {

    }

    public SurveyModel(String id, String count, String name) {
        this.id = id;
        this.count = count;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
