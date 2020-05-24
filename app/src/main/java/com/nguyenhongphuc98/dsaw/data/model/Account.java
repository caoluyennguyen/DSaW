package com.nguyenhongphuc98.dsaw.data.model;

public class Account {

    private String birthday;

    private String identity;

    private String password;

    private String phonenumber;

    private String role;

    private String username;

    private String area;

    private String area_management;


    public Account() {}

    public Account(String birthday, String identity, String password, String phonenumber, String role, String username, String area, String area_management) {
        this.birthday = birthday;
        this.identity = identity;
        this.password = password;
        this.phonenumber = phonenumber;
        this.role = role;
        this.username = username;
        this.area = area;
        this.area_management = area_management;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea_management() {
        return area_management;
    }

    public void setArea_management(String area_management) {
        this.area_management = area_management;
    }
}
