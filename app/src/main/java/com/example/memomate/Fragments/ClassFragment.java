package com.example.memomate.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memomate.Activities.CreateClassActivity;
import com.example.memomate.Adapters.ClassRecyclerViewAdapter;
import com.example.memomate.Models.Class;
import com.example.memomate.R;

import java.util.ArrayList;


public class ClassFragment extends Fragment {
    private RecyclerView classRecyclerView;
    private ClassRecyclerViewAdapter classRecyclerViewAdapter;
    private LinearLayoutManager layoutManager;
    private ImageButton btnCreate;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);

        classRecyclerView = view.findViewById(R.id.recyclerview_class);
        classRecyclerViewAdapter = new ClassRecyclerViewAdapter(getClasses());
        classRecyclerView.setLayoutManager(layoutManager);
        classRecyclerView.setAdapter(classRecyclerViewAdapter);
        btnCreate = view.findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(view.getContext(), CreateClassActivity.class);
                startActivity(i);
            }
        });
    }

    private ArrayList<Class> getClasses()
    {
        ArrayList<Class> classes = new ArrayList<>();
        classes.add(new Class("ABC1", 5));
        classes.add(new Class("ABC2", 6));
        classes.add(new Class("ABC", 7));
        classes.add(new Class("ABC", 8));
        classes.add(new Class("ABC", 9));
        return classes;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_class, container, false);
    }
}