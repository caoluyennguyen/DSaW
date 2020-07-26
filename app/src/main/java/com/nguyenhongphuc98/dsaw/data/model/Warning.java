package com.nguyenhongphuc98.dsaw.data.model;

import java.util.ArrayList;
import java.util.List;

public class Warning {
    public String content;
    public String creator;
    public String title;
    public List<String> receiver;

    public Warning() {}

    public Warning(String title, String content, String creator, List<String> receiver)
    {
        this.title = title;
        this.content = content;
        this.creator = creator;
        this.receiver = receiver;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCreator() {
        return creator;
    }

    public List<String> getReceiver() {
        return receiver;
    }

    public void setReceiver(List<String> receiver) {
        this.receiver = receiver;
    }
}
