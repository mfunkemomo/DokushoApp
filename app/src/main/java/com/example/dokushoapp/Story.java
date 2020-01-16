package com.example.dokushoapp;

import java.util.ArrayList;

public class Story {
    private String title;
    private String author;
    private int level;
    private ArrayList<Page> pages;

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

    public ArrayList<Page> getPages() {
        return pages;
    }

}
