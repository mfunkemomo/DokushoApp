package com.example.dokushoapp;

import java.util.ArrayList;
import java.util.Objects;

public class Page {

    private String content;
    private String translation;
    private ArrayList<String> fakeTranslation;

    //empty constructor for Firebase
    public Page(){ }

    public String getContent() {
        return content;
    }

    public String getTranslation() {
        return translation;
    }

    public ArrayList<String> getFakeTranslation() {
        return fakeTranslation;
    }
}
