package com.example.dokushoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class StoryPage extends AppCompatActivity {

    //Connection to Firebase
//    private FirebaseFirestore db = FirebaseFirestore.getInstance();
//    private DocumentReference storyRef = db.document("Stories/")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_page);
    }
}
