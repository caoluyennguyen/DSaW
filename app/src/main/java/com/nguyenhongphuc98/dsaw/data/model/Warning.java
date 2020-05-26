package com.nguyenhongphuc98.dsaw.data.model;

public class Warning {
    public String content;
    public String creator;
    public String title;

    public Warning(String title, String content)
    {
        this.title = title;
        this.content = content;
    }
}
