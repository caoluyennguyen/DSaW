package com.nguyenhongphuc98.dsaw.data.model;

public class Case {

    private String area;
    private String begin_time;
    private String end_time;
    private String f;
    private String id;
    private String location;
    private String user; //identity
    private String name; // name of user

    public Case() {}

    public Case(String area, String begin_time, String end_time, String f, String id, String location, String user, String name) {
        this.area = area;
        this.begin_time = begin_time;
        this.end_time = end_time;
        this.f = f;
        this.id = id;
        this.location = location;
        this.user = user;
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(String begin_time) {
        this.begin_time = begin_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
