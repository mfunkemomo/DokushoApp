package com.example.dokushoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class BooksMenu extends AppCompatActivity {

    private Button selectedBook;

    //get all the stories with the appropriate levels
    // ex. stories_level1 = db.stories.level1

    //Connection to Firebase
//    private FirebaseFirestore db = FirebaseFirestore.getInstance();
//    private DocumentReference dbStories = db.collection("Stories");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_menu);

        selectedBook = findViewById(R.id.book1_button);

        selectedBook.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(BooksMenu.this, StoryPage.class);
                startActivity(intent);
            }
        });
    }
}
