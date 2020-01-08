package com.example.dokushoapp;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Story {
    private String title;
    private String author;
    private int level;
    private Array pages;

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

    public Array getPages() {
        return pages;
    }
}
