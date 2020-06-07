package com.nguyenhongphuc98.dsaw.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PublicDataModel {

    private String infected;

    private String deceased;

    private String recovered;

    private String lastUpdatedApify;

    public PublicDataModel() {
    }

    public PublicDataModel(String infected, String deceased, String recovered, String lastUpdatedAtSource) {
        this.infected = infected;
        this.deceased = deceased;
        this.recovered = recovered;
        this.lastUpdatedApify = lastUpdatedAtSource;
    }

    public String getInfected() {
        return infected;
    }

    public void setInfected(String infected) {
        this.infected = infected;
    }

    public String getDeceased() {
        return deceased;
    }

    public void setDeceased(String deceased) {
        this.deceased = deceased;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getLastUpdatedApify() {
        return lastUpdatedApify;
    }

    public void setLastUpdatedApify(String lastUpdatedAtSource) {
        this.lastUpdatedApify = lastUpdatedAtSource;
    }
}
