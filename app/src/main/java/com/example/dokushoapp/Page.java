package com.example.dokushoapp;

import java.util.ArrayList;

public class Page {

    private String content;
    private String translation;
    private ArrayList<String> fakeTranslation;
    private String vocab;
    private  ArrayList<Integer> spanIndex;

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

    public String getVocab() {
        return vocab;
    }

    public ArrayList<Integer> getSpanIndex() {
        return spanIndex;
    }
}
