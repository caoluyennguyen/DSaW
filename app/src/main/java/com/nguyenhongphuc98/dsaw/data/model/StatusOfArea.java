package com.nguyenhongphuc98.dsaw.data.model;

public class StatusOfArea {

    private String last_update;

    private String num_patient;

    private String status;

    public StatusOfArea() {}

    public StatusOfArea(String last_update, String num_patient, String status) {
        this.last_update = last_update;
        this.num_patient = num_patient;
        this.status = status;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    public String getNum_patient() {
        return num_patient;
    }

    public void setNum_patient(String num_patient) {
        this.num_patient = num_patient;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
