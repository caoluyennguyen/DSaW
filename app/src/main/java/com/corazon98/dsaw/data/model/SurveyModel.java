package com.corazon98.dsaw.data.model;

public class SurveyModel {

    // Survey model mean survey to apply to listview to show
    // It can be not same model map to database

    private String id;

    private String count;

    private String name;

    private String type;

    public SurveyModel() {

    }

    public SurveyModel(String id, String count, String name, String type) {
        this.id = id;
        this.count = count;
        this.name = name;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
