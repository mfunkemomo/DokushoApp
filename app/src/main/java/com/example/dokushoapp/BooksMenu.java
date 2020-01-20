package com.example.dokushoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

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

                                storyIdCollection.add(snapshots.getId());
                                String jpTitle = story.getAuthor()
                                        + " の「" + story.getTitle() + "」\n";
                                String enAuthor = " by " + story.getAuthorTranslation();
                                String enTitle = "\""+story.getTitleTranslation()+"\"";
                                storyArrayList.add(jpTitle + enTitle + enAuthor);
                            }

                    }
                    storyArrayAdapter = new ArrayAdapter<>(
                      BooksMenu.this,
                      android.R.layout.simple_list_item_1,
                      storyArrayList
                    );
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
