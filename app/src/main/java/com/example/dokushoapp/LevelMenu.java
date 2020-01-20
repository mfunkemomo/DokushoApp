package com.example.dokushoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LevelMenu extends AppCompatActivity {

    private Button selectLevel1;
    private Button selectLevel2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_menu);

        selectLevel1 = findViewById(R.id.lvl1_button);
        selectLevel2 = findViewById(R.id.lvl2_button);

        selectLevel1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(LevelMenu.this, BooksMenu.class);
                intent.putExtra("level", 1);
                startActivity(intent);
            }
        });

        selectLevel2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(LevelMenu.this, BooksMenu.class);
                intent.putExtra("level", 2);
                startActivity(intent);
            }
        });
    }
}
