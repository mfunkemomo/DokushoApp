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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

public class BooksMenu extends AppCompatActivity {

    private Button selectedBook;
    private TextView booksMenuHeader;
    private static final String TAG = "BooksMenu";

    //Connection to Firebase
//    private FirebaseFirestore db = FirebaseFirestore.getInstance();
//    private CollectionReference dbStories = db.collection("Stories");

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference dbStories = db.getReference("Stories");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_menu);

        selectedBook = findViewById(R.id.book1_button);
        int level = getIntent().getIntExtra("level", 1);

        booksMenuHeader = findViewById(R.id.books_menu_header);
        booksMenuHeader.setText("Level " + String.valueOf(level) + " Books");

        getStories();

        selectedBook.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(BooksMenu.this, StoryPage.class);
                startActivity(intent);
            }
        });
    }

    private void getStories(){
        dbStories.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
//        dbStories.get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//
//                        String data = "";
//                        for (QueryDocumentSnapshot snapshots : queryDocumentSnapshots) {
//                            Log.d(TAG, "onSuccess: " + snapshots.getId());
//
//                            Story story = snapshots.toObject(Story.class);
//
//                            data += story.getTitle() + " \n"
//                                    + "By" + story.getAuthor() + "\n\n" ;
//
//                        }
//                        selectedBook.setText(data);
//
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.d(TAG, "onError: " + e.toString());
//                    }
//                });
//    }
}
