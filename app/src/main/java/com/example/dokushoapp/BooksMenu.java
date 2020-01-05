package com.example.dokushoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BooksMenu extends AppCompatActivity {

    private Button selectedBook;

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
