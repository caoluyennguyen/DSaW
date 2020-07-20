package com.nguyenhongphuc98.dsaw.data.model;

public class Warning {
    public String content;
    public String creator;
    public String title;
    public String link;

    public Warning(String title, String content, String creator)
    {
        this.title = title;
        this.content = content;
        this.creator = creator;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLink(String link) {
        this.link = link;
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

    public String getLink() {
        return link;
    }
}
