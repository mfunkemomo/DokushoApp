package com.example.dokushoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class StoryPage extends AppCompatActivity {

    private static final String TAG = "StoryPage";
    private TextView bookTitleHeader;
    private TextView storyContent;
    private String sentence;
    private ArrayList<Page> storyPages;
    private Button fakeAnswer1Button;
    private Button fakeAnswer2Button;
    private Button correctAnswerButton;

    //Connection to Firebase
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference dbStories = db.collection("Stories");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_page);

        String storyId = getIntent().getStringExtra("storyId");

        storyContent = findViewById(R.id.story_content);
        bookTitleHeader = findViewById(R.id.book_title_header);
        fakeAnswer1Button = findViewById(R.id.fake_answer_button_1);
        fakeAnswer2Button = findViewById(R.id.fake_answer_button_2);
        correctAnswerButton = findViewById(R.id.correct_answer_button);

        getStory(storyId);
        Log.d(TAG, "onSuccess: storypages outside method " + storyPages);


    }

    private void getStory(final String storyId) {

        dbStories.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String bookTitle = "";
                        sentence = "";
                        String translation = "";
                        String fakeTranslation1 = "";
                        String fakeTranslation2 = "";

                        for (QueryDocumentSnapshot snapshots : queryDocumentSnapshots) {

                            Story story = snapshots.toObject(Story.class);

                            if (snapshots.getId().equals(storyId)){

                                bookTitle = story.getTitle();

                                storyPages = story.getPages();

                                //display first page of storyPages
                                //page = 0
                                //if user clicks next, page += 1 and redisplay page

                                sentence += storyPages.get(0).getContent();

                                translation += storyPages.get(0).getTranslation();

                                fakeTranslation1 += storyPages.get(0).getFakeTranslation().get(0);

                                fakeTranslation2 += storyPages.get(0).getFakeTranslation().get(1);
                            }

                        }
                        storyContent.setText(sentence);
                        bookTitleHeader.setText("Book title: " + bookTitle);
                        correctAnswerButton.setText(translation);
                        fakeAnswer1Button.setText(fakeTranslation1);
                        fakeAnswer2Button.setText(fakeTranslation2);

                        Log.d(TAG, "onSuccess: " + sentence);
                        Log.d(TAG, "onSuccess: storypages after adding" + storyPages);
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
