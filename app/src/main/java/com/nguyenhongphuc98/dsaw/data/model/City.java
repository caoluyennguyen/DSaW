package com.nguyenhongphuc98.dsaw.data.model;

import java.util.List;

public class City {
    private String code;
    private String name;
    private List<District> lsDistrict;

    public City() {}

    public City(String name) {
        this.name= name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setLsDistrict(List<District> lsDistrict) {
        this.lsDistrict = lsDistrict;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public List<District> getLsDistrict() {
        return lsDistrict;
    }
}
