package com.example.dokushoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

public class BooksMenu extends AppCompatActivity {

    private Button selectedBook;
    private TextView booksMenuHeader;

    //get all the stories with the appropriate levels
    // ex. stories_level1 = db.stories.level1

    //Connection to Firebase
//    private FirebaseFirestore db = FirebaseFirestore.getInstance();
//    private DocumentReference dbStories = db.collection("Stories");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_menu);

        int level = getIntent().getIntExtra("level", 1);

        selectedBook = findViewById(R.id.book1_button);
        booksMenuHeader = findViewById(R.id.books_menu_header);

        booksMenuHeader.setText("Level " + String.valueOf(level) + " Books");

        selectedBook.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(BooksMenu.this, StoryPage.class);
                startActivity(intent);
            }
        });
    }
}
