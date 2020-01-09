package com.example.dokushoapp;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

public class Story {
    private String title;
    private String author;
    private int level;
    private Page[] pages;

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

    public Page[] getPages() {
        return pages;
    }
}
