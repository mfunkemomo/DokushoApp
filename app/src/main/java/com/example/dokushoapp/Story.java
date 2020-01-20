package com.example.dokushoapp;

import java.util.ArrayList;

public class Story {
    private String title;
    private String titleTranslation;
    private String author;
    private String authorTranslation;
    private int level;
    private ArrayList<Page> pages;

    //empty constructor for Firebase
    public Story(){ }

    public String getTitle() {
        return title;
    }

    public String getTitleTranslation() {
        return titleTranslation;
    }

    public String getAuthor() {
        return author;
    }

    public String getAuthorTranslation() {
        return authorTranslation;
    }

    public int getLevel() {
        return level;
    }

    public ArrayList<Page> getPages() {
        return pages;
    }

}
