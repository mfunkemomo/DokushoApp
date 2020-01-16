package com.example.dokushoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
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
                        String vocab = "";
                        ArrayList<String> answersList = new ArrayList<>();
                        Integer spanIndexStart = null;
                        Integer spanIndexEnd = null;

                        for (QueryDocumentSnapshot snapshots : queryDocumentSnapshots) {

                            Story story = snapshots.toObject(Story.class);

                            if (snapshots.getId().equals(storyId)){

                                bookTitle = story.getTitle();
                                storyPages = story.getPages();
                                sentence += storyPages.get(pageNum).getContent();
                                vocab = storyPages.get(pageNum).getVocab();
                                spanIndexStart = storyPages.get(pageNum).getSpanIndex().get(0);
                                spanIndexEnd = storyPages.get(pageNum).getSpanIndex().get(1);

                                answersList.add(storyPages.get(pageNum).getTranslation());
                                answersList.add(storyPages.get(pageNum).getFakeTranslation().get(0));
                                answersList.add(storyPages.get(pageNum).getFakeTranslation().get(1));
                            }

                        }


                        SpannableString spanSentence = new SpannableString(sentence);
                        final String finalVocab = vocab;

                        UnderlineSpan underlineSpan = new UnderlineSpan();
                        ForegroundColorSpan fcsBlack = new ForegroundColorSpan(Color.BLACK);

                        ClickableSpan clickableSpanTest = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View widget) {
                                Toast.makeText(StoryPage.this, finalVocab, Toast.LENGTH_SHORT).show();
                            }
                        };

                        spanSentence.setSpan(clickableSpanTest, spanIndexStart, spanIndexEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        spanSentence.setSpan(underlineSpan, spanIndexStart, spanIndexEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        spanSentence.setSpan(fcsBlack, spanIndexStart, spanIndexEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        storyContent.setText(spanSentence);
                        storyContent.setMovementMethod(LinkMovementMethod.getInstance());
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
                checkAnswer(userChoice);
                break;

            case R.id.answer_button_2:
                userChoice = answerButton2.getText().toString();
                checkAnswer(userChoice);
                break;

            case R.id.answer_button_3:
                userChoice = answerButton3.getText().toString();
                checkAnswer(userChoice);
                break;

            case R.id.next_button:
                if (pageNum+1 < storyPages.size()){
                    pageNum = (pageNum + 1) % storyPages.size();
                    getStory(storyId, pageNum);

                } else {
                    goToLastPage();
                }
                 break;
            case R.id.prev_button:
                if (pageNum > 0) {
                    pageNum = (pageNum - 1) % storyPages.size();
                    getStory(storyId, pageNum);
                } else {
                    Toast.makeText(StoryPage.this, "Not possible", Toast.LENGTH_SHORT).show();
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

    private void goToLastPage(){
        Intent intent = new Intent(StoryPage.this, LastPage.class);
        startActivity(intent);
    }


}
