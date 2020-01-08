package com.example.dokushoapp;

public class Story {
    private String title;
    private String author;
    private int level;

    //empty constructor for Firebase
    public Story(){ }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getLevel() {
        return level;
    }
}
