package com.example.myapplication;

public class News {
    private String title;
    //    private String content;
    //    private String author;
    private String date;
    //    private String imageUrl;

    // 构造函数、getter和setter方法
    public News(String title, String date) {
        this.title = title;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }
}