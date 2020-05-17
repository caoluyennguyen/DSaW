package com.nguyenhongphuc98.dsaw.data.model;

import java.util.List;

public class RouteData {

    private String id;

    private String user;

    private List<TrackingStatus> status;

    public RouteData(String id, String user, List<TrackingStatus> status) {
        this.id = id;
        this.user = user;
        this.status = status;
    }

    public void addStatus(TrackingStatus st){
        status.add(st);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<TrackingStatus> getStatus() {
        return status;
    }

    public void setStatus(List<TrackingStatus> status) {
        this.status = status;
    }
}
