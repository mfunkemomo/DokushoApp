package com.example.dokushoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class LastPage extends AppCompatActivity {

    private static final String TAG = "LastPage";
    private TextView owari;
    private TextView theEnd;
    private Button returnBooksMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_page);

        owari = findViewById(R.id.owari);
        theEnd = findViewById(R.id.the_end);
        returnBooksMenu = findViewById(R.id.return_books_menu);

        returnBooksMenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(LastPage.this, LevelMenu.class);
                startActivity(intent);
            }
        });
    }
}
