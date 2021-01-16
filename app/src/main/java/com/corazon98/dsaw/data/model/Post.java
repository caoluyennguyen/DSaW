package com.corazon98.dsaw.data.model;

public class Post {
    private String author;
    private String content;
    private String cover;
    private String createtime;
    private String id;
    private String link;
    private String title;
    private String type;

    public Post(String author, String content, String cover, String createtime, String id, String link, String title, String type) {
        this.author = author;
        this.content = content;
        this.cover = cover;
        this.createtime = createtime;
        this.id = id;
        this.link = link;
        this.title = title;
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
