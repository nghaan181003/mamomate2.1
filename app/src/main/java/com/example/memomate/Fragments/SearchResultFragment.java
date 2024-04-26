package com.example.memomate.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memomate.Adapters.SearchResultAdapter;
import com.example.memomate.Adapters.StudySetRecyclerViewAdapter;
import com.example.memomate.Models.FlashCard;
import com.example.memomate.Models.StudySet;
import com.example.memomate.R;

import java.util.ArrayList;

public class SearchResultFragment extends Fragment {
    private RecyclerView listResultRecyclerView;
    private ArrayAdapter<String> adapterResult;
    private TextView btnClose;
    private SearchView searchView;
    private SearchResultAdapter searchResultAdapter;
    private RecyclerView studySetRecyclerView;
    private StudySetRecyclerViewAdapter studySetRecyclerViewAdapter;
    private LinearLayoutManager layoutManager;
    private LinearLayoutManager layoutManager1;
    private String query;
    private ArrayList<String> filteredResults = new ArrayList<String>();
    private String[] names = {"Alan", "James", "Guy", "Chet", "Joshua", "Brian", "Mark", "David", "Kirk", "Susan", "Edward", "Michael", "Emily", "John", "Steve"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_result, container, false);
        searchView = view.findViewById(R.id.search_view);
        searchView.requestFocus();

        btnClose = view.findViewById(R.id.btnCancel);

        layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        layoutManager1 = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);

        listResultRecyclerView = view.findViewById(R.id.recyclerview_search_result);

        studySetRecyclerView = view.findViewById(R.id.recyclerview_searched_study_set);
        addEvents();
        return view;
    }
    public void addEvents(){
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().remove(SearchResultFragment.this).commit();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                filteredResults.clear();
                if (newText != null && !newText.isEmpty()) {
                    for (String name : names) {
                        if (name.contains(newText)) {
                            filteredResults.add(name);
                        }
                    }
                }

                searchResultAdapter = new SearchResultAdapter(filteredResults);
                listResultRecyclerView.setLayoutManager(layoutManager);
                //searchResultAdapter = new SearchResultAdapter(filteredResults, listResultRecyclerView, studySetRecyclerView);
                searchResultAdapter = new SearchResultAdapter(filteredResults, listResultRecyclerView, studySetRecyclerView, layoutManager1);
                listResultRecyclerView.setAdapter(searchResultAdapter);

                return false;
            }
        });

    }
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


}