package com.example.dokushoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class StoryPage extends AppCompatActivity {

    private static final String TAG = "StoryPage";
    private TextView storyContent;
//    private Page[] pages;
    private String sentence;

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

    private void getStory(final String storyId) {

        dbStories.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for (QueryDocumentSnapshot snapshots : queryDocumentSnapshots) {

                            Story story = snapshots.toObject(Story.class);
                            sentence = "";

                            if (snapshots.getId().equals(storyId)){
                                Log.d(TAG, "onSuccess: " + snapshots.getId());

                                ArrayList<Page> storyPages = story.getPages();

                                sentence += storyPages.get(0).getContent();
                                Log.d(TAG, "onSuccess: " + sentence);
                            }

                        }
                        storyContent.setText(sentence);

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
