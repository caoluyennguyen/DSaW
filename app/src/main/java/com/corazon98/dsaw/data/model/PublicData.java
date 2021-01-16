package com.corazon98.dsaw.data.model;

public class PublicData {
    private String area;

    private String id;

    private String location;

    private String num_confirmed;

    private String num_death;

    private String num_recovered;

    private String update_time;

    private String update_date;

    public PublicData() {
    }

    public PublicData(String area, String id, String location,
                      String num_confirmed, String num_death,
                      String num_recovered, String update_time) {
        this.area = area;
        this.id = id;
        this.location = location;
        this.num_confirmed = num_confirmed;
        this.num_death = num_death;
        this.num_recovered = num_recovered;
        this.update_time = update_time;
        this.update_date = update_time.substring(0,10);
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNum_confirmed() {
        return num_confirmed;
    }

    public void setNum_confirmed(String num_confirmed) {
        this.num_confirmed = num_confirmed;
    }

    public String getNum_death() {
        return num_death;
    }

    public void setNum_death(String num_death) {
        this.num_death = num_death;
    }

    public String getNum_recovered() {
        return num_recovered;
    }

    public void setNum_recovered(String num_recovered) {
        this.num_recovered = num_recovered;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }
}
