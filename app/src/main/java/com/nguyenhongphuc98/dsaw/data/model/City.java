package com.nguyenhongphuc98.dsaw.data.model;

import java.util.ArrayList;
import java.util.List;

public class City {
    private String code;
    private String name;

    public City() { }

    public City(String code, String name, List<District> district) {
        this.code = code;
        this.name= name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return this.name; // What to display in the Spinner list.
    }
}
