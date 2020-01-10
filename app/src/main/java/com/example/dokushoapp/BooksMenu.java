package com.example.dokushoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

public class BooksMenu extends AppCompatActivity {

    private static final String TAG = "BooksMenu";
    private TextView booksMenuHeader;
    private List<String> storyIdCollection;
    private ListView booksListView;
    private ArrayList<String> storyArrayList;
    private ArrayAdapter storyArrayAdapter;

    //Connection to Firebase
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference dbStories = db.collection("Stories");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_menu);

        booksListView = findViewById(R.id.books_list_view);
        storyArrayList = new ArrayList<>();

        storyIdCollection = new ArrayList<>();

        int selectedLevel = getIntent().getIntExtra("level", 1);

        String booksMenuHeaderText = "Level " + String.valueOf(selectedLevel) + " Books";
        booksMenuHeader = findViewById(R.id.books_menu_header);
        booksMenuHeader.setText(booksMenuHeaderText);

        getStories(selectedLevel);

        booksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onSuccess: " + position);
                Log.d(TAG, "onSuccess: storycollectionid " + storyIdCollection.get(position));
                Intent intent = new Intent(BooksMenu.this, StoryPage.class);
                intent.putExtra("storyId", storyIdCollection.get(position));
                startActivity(intent);
            }
        });
    }

    private void getStories(final int selectedLevel){

        dbStories.get()
            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                    for (QueryDocumentSnapshot snapshots : queryDocumentSnapshots) {

                            Story story = snapshots.toObject(Story.class);

                            if (story.getLevel() == selectedLevel){
                                Log.d(TAG, "onSuccess: " + snapshots.getId());

                                storyIdCollection.add(snapshots.getId());
                                storyArrayList.add(story.getTitle() + " \n"
                                        + " by " + story.getAuthor());
                            }

                    }
                    storyArrayAdapter = new ArrayAdapter<>(
                      BooksMenu.this,
                      android.R.layout.simple_list_item_1,
                      storyArrayList
                    );
                    Log.d(TAG, "onSuccess: storyarraylist " + storyArrayList);
                    booksListView.setAdapter(storyArrayAdapter);
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
