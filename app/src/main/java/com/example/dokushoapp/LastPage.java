package com.example.dokushoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class LastPage extends AppCompatActivity {

    private static final String TAG = "LastPage";
    private ArrayList<String> lastPage;
    private TextView owari;
    private TextView theEnd;
    private Button returnBooksMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_page);

        lastPage = getIntent().getStringArrayListExtra("lastPageContent");
        Log.d(TAG, "getIntent: " + lastPage);

        owari = findViewById(R.id.owari);
        theEnd = findViewById(R.id.the_end);
        returnBooksMenu = findViewById(R.id.return_books_menu);

        owari.setText(lastPage.get(0));
        theEnd.setText(lastPage.get(1));

        returnBooksMenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(LastPage.this, BooksMenu.class);
                intent.putExtra("level", 1);
                startActivity(intent);
            }
        });
    }
}
