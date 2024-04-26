package com.example.memomate.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memomate.Adapters.StudySetAdapter;
import com.example.memomate.Adapters.StudySetRecyclerViewAdapter;
import com.example.memomate.Models.FlashCard;
import com.example.memomate.Models.StudySet;
import com.example.memomate.R;

import java.util.ArrayList;
public class TabStudySetsFragment extends Fragment {
    private RecyclerView studySetRecyclerView;
    private StudySetRecyclerViewAdapter studySetRecyclerViewAdapter;
    private LinearLayoutManager layoutManager;

    private StudySetRecyclerViewAdapter studySetAdapter;

    ArrayList<StudySet> studySets = new ArrayList<>();
    EditText edtFilter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        populateDummyFlashcards();
        edtFilter = view.findViewById(R.id.edtFilter);
        layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        ArrayList<String> studySetArrayList = new ArrayList<>();
        studySetRecyclerView = view.findViewById(R.id.recyclerview_study_set);
        studySetAdapter = new StudySetRecyclerViewAdapter(studySets);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_study_sets, container, false);
    }
    public void hideEditText() {
        edtFilter.setVisibility(View.GONE);
    }
}