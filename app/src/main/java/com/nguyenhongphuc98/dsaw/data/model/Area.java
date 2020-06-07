package com.nguyenhongphuc98.dsaw.data.model;

import java.util.HashMap;
import java.util.List;

public class Area {

    private String name;

    private String id;

    private List<HashMap<String,Object>> update_status;

    public Area(){}

    public Area(String name, String id, List<HashMap<String, Object>> update_status) {
        this.name = name;
        this.id = id;
        this.update_status = update_status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<HashMap<String, Object>> getUpdate_status() {
        return update_status;
    }

    public void setUpdate_status(List<HashMap<String, Object>> update_status) {
        this.update_status = update_status;
    }
}
