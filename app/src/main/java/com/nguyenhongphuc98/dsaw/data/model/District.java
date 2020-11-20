package com.nguyenhongphuc98.dsaw.data.model;

import java.util.List;

public class District {
    private String name;
    private List<String> lsWards;

    public String getName() {
        return name;
    }

    public List<String> getWards() {
        return lsWards;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLsWards(List<String> lsWards) {
        this.lsWards = lsWards;
    }
}
