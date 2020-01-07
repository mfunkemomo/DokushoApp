package com.example.dokushoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class BooksMenu extends AppCompatActivity {

    private static final String TAG = "BooksMenu";
    private TextView bookSelection;
    private TextView booksMenuHeader;

    //Connection to Firebase
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference dbStories = db.collection("Stories");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_menu);

        int level = getIntent().getIntExtra("level", 1);
        bookSelection = findViewById(R.id.bookTitleAuthor);

        String booksMenuHeaderText = "Level " + String.valueOf(level) + " Books";
        booksMenuHeader = findViewById(R.id.books_menu_header);
        booksMenuHeader.setText(booksMenuHeaderText);

        Log.d(TAG, "onCreate: before getStories");
        getStories();
        Log.d(TAG, "onCreate: after getStories");

        bookSelection.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(BooksMenu.this, StoryPage.class);
                startActivity(intent);
            }
        });
    }

    private void getStories(){

        dbStories.get()
            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                    String data = "";
                    for (QueryDocumentSnapshot snapshots : queryDocumentSnapshots) {
                        Log.d(TAG, "onSuccess: " + snapshots.getId());

                        Story story = snapshots.toObject(Story.class);

                        data += story.getTitle() + " \n"
                                + "By" + story.getAuthor() + "\n\n" ;

                    }
                    bookSelection.setText(data);

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
