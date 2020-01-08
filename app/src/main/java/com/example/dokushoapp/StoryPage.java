package com.example.dokushoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.Array;
import java.util.Arrays;
import java.util.Objects;

public class StoryPage extends AppCompatActivity {

    private static final String TAG = "StoryPage";
    private TextView storyContent;

    //Connection to Firebase
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference dbStories = db.collection("Stories");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_page);

        String storyId = getIntent().getStringExtra("storyId");
        Log.d(TAG, "onSuccess, storyid: " + storyId);

        storyContent = findViewById(R.id.story_content);

        getStory(storyId);

    }

    private void getStory(String storyId) {

        dbStories.document(storyId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        String sentence = "";
//                        Objects[] pages = new Objects[5];

                        for (QueryDocumentSnapshot snapshots : queryDocumentSnapshots) {

                            Story story = snapshots.toObject(Story.class);

                            sentence = story.getPages()[0].content;

//                            sentence = pages[0].content;

                            Log.d(TAG, "onSuccess Pages: " + sentence);

                        }
                        storyContent.setText(sentence);
//
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onError: " + e.toString());
                    }
                });
    }
}
