package com.corazon98.dsaw.data.model;

public class TrackingStatus {

    private String location;

    private String update_time;

    public TrackingStatus() {}

    public TrackingStatus(String location, String update_time) {
        this.location = location;
        this.update_time = update_time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}
