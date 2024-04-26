package com.example.memomate.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.memomate.Adapters.StudySetSelectAdapter;
import com.example.memomate.Models.FlashCard;
import com.example.memomate.Models.StudySet;
import com.example.memomate.R;

import java.util.ArrayList;

public class AddSetActivity extends AppCompatActivity {
    private RecyclerView studySetRecyclerView;
    private StudySetSelectAdapter studySetAdapter;
    private LinearLayoutManager layoutManager;
    private ImageButton btnBack;
    private TextView btnDone, btnCreateANewSet;

    ArrayList<StudySet> studySets = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_set);

        addEvent();
        populateDummyFlashcards();
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        ArrayList<String> studySetArrayList = new ArrayList<>();
        studySetRecyclerView = findViewById(R.id.recyclerview_study_set);
        studySetAdapter = new StudySetSelectAdapter(studySets);
        studySetRecyclerView.setLayoutManager(layoutManager);
        studySetRecyclerView.setAdapter(studySetAdapter);
    }
    private void populateDummyFlashcards(){
        ArrayList<FlashCard> flashCards1 = new ArrayList<>();
        flashCards1.add(new FlashCard("1", "Một"));
        flashCards1.add(new FlashCard("11", "Một một"));
        flashCards1.add(new FlashCard("111", "Một một một"));

        ArrayList<FlashCard> flashCards2 = new ArrayList<>();
        flashCards2.add(new FlashCard("2", "Hai"));
        flashCards2.add(new FlashCard("22", "Hai hai"));
        flashCards2.add(new FlashCard("222", "Hai hai hai"));
        flashCards2.add(new FlashCard("2222", "Hai hai hai hai"));

        studySets.add(new StudySet("Toan", R.drawable.han, "ngochandethuong", flashCards1));
        studySets.add(new StudySet("AV", R.drawable.han, "ngochandethuong", flashCards2));
    }
    public void addEvent(){
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnDone = findViewById(R.id.btnDone);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddSetActivity.this, FolderActivity.class);
                i.putExtra("getStudySets", studySetAdapter.getSelectedStudySets());

                ((Activity)AddSetActivity.this).startActivityForResult(i, 13);
            }
        });
        btnCreateANewSet = findViewById(R.id.btnCreateANewSet);
        btnCreateANewSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddSetActivity.this, CreateSetActivity.class);
                //i.putExtra("getSelectedStudySets", studySetAdapter.getSelectedStudySets());
//                i.putExtra("getAvatar", folder.getAvatar());
//                i.putExtra("getUserName", folder.getAuthor());
//                i.putExtra("position", holder.getAdapterPosition());
                ((Activity)AddSetActivity.this).startActivityForResult(i, 12);
            }
        });
    }
}