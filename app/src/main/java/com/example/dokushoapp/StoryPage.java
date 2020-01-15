package com.example.dokushoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class StoryPage extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "StoryPage";
    private String storyId;
    private TextView bookTitleHeader;
    private TextView storyContent;
    private String sentence;
    private ArrayList<Page> storyPages;
    private Button answerButton1;
    private Button answerButton2;
    private Button answerButton3;
    private Button nextButton;
    private Button prevButton;
    private int pageNum;


    //Connection to Firebase
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference dbStories = db.collection("Stories");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_page);

        storyId = getIntent().getStringExtra("storyId");
        pageNum = 0;

        storyContent = findViewById(R.id.story_content);
        bookTitleHeader = findViewById(R.id.book_title_header);
        answerButton1 = findViewById(R.id.answer_button_1);
        answerButton2 = findViewById(R.id.answer_button_2);
        answerButton3 = findViewById(R.id.answer_button_3);
        nextButton = findViewById(R.id.next_button);
        prevButton = findViewById(R.id.prev_button);

        getStory(storyId, pageNum);

        answerButton1.setOnClickListener(StoryPage.this);
        answerButton2.setOnClickListener(StoryPage.this);
        answerButton3.setOnClickListener(StoryPage.this);
        nextButton.setOnClickListener(StoryPage.this);
        prevButton.setOnClickListener(StoryPage.this);

    }

    private void getStory(final String storyId, final int pageNum) {

        dbStories.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String bookTitle = "";
                        sentence = "";
                        ArrayList<String> answersList = new ArrayList<>();

                        for (QueryDocumentSnapshot snapshots : queryDocumentSnapshots) {

                            Story story = snapshots.toObject(Story.class);

                            if (snapshots.getId().equals(storyId)){

                                bookTitle = story.getTitle();

                                storyPages = story.getPages();

                                sentence += storyPages.get(pageNum).getContent();

                                answersList.add(storyPages.get(pageNum).getTranslation());
                                answersList.add(storyPages.get(pageNum).getFakeTranslation().get(0));
                                answersList.add(storyPages.get(pageNum).getFakeTranslation().get(1));
                            }

                        }
                        storyContent.setText(sentence);
                        bookTitleHeader.setText("書名：" + bookTitle);

                        Collections.shuffle(answersList);

                        answerButton3.setText(answersList.get(0));
                        answerButton2.setText(answersList.get(1));
                        answerButton1.setText(answersList.get(2));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onError: " + e.toString());
                    }
                });
    }

    @Override
    public void onClick(View v) {
        String userChoice = "";
        switch (v.getId()) {
            case R.id.answer_button_1:
                userChoice = answerButton1.getText().toString();
                Log.d(TAG, "userChoice: " + userChoice);
                checkAnswer(userChoice);
                break;

            case R.id.answer_button_2:
                userChoice = answerButton2.getText().toString();
                Log.d(TAG, "userChoice: " + userChoice);
                checkAnswer(userChoice);
                break;

            case R.id.answer_button_3:
                userChoice = answerButton3.getText().toString();
                Log.d(TAG, "userChoice: " + userChoice);
                checkAnswer(userChoice);
                break;

            case R.id.next_button:
                if (pageNum+1 == storyPages.size()){

                    ArrayList<String> lastPageContent = new ArrayList<String>();
                    lastPageContent.add(storyPages.get(pageNum).getContent());
                    lastPageContent.add(storyPages.get(pageNum).getTranslation());

                    Intent intent = new Intent(StoryPage.this, LastPage.class);
                    intent.putExtra("lastPage", lastPageContent);
                    startActivity(intent);
                } else {
                    pageNum = (pageNum + 1) % storyPages.size();
                    getStory(storyId, pageNum);
                }
                break;
            case R.id.prev_button:
                if (pageNum > 0) {
                    pageNum = (pageNum - 1) % storyPages.size();
                    getStory(storyId, pageNum);
                }
        }

    }

    private void checkAnswer(String userChoice){
        String correctTranslation = storyPages.get(pageNum).getTranslation();
        if (userChoice == correctTranslation){
            Toast.makeText(StoryPage.this,"✅ まる ", Toast.LENGTH_SHORT)
                    .show();
        } else {
            Toast.makeText(StoryPage.this,"❌ ばつ", Toast.LENGTH_SHORT)
                    .show();
        }
    }


}
