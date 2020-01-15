package com.example.dokushoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class LastPage extends AppCompatActivity {

    private Object lastPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_page);

        lastPage = getIntent().getSerializableExtra("lastPage");
    }
}
