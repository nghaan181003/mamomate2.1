package com.example.memomate.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memomate.Adapters.FolderAdapter;
import com.example.memomate.Adapters.FolderRecyclerViewAdapter;
import com.example.memomate.Models.Folder;
import com.example.memomate.R;

import java.util.ArrayList;

public class TabFoldersFragment extends Fragment {
    private RecyclerView folderRecyclerView;
    private FolderRecyclerViewAdapter folderAdapter;
    private LinearLayoutManager layoutManager;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);

        folderRecyclerView = view.findViewById(R.id.recyclerview_folder);
        folderAdapter = new FolderRecyclerViewAdapter(view.getContext());
        folderAdapter.setData(getListFolder());
        folderRecyclerView.setLayoutManager(layoutManager);
        folderRecyclerView.setAdapter(folderAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_folders, container, false);
    }

    private ArrayList<Folder> getListFolder()
    {
        ArrayList<Folder> data = new ArrayList<>();
        data.add(new Folder("Hello", 2, R.drawable.images, "thanhhoa"));
        data.add(new Folder("Xin chao", 2, R.drawable.images, "thanhhoa11"));
        data.add(new Folder("Hello", 2, R.drawable.images, "thanhhoa"));
        data.add(new Folder("Hello", 2, R.drawable.images, "thanhhoa"));
        data.add(new Folder("Hello", 2, R.drawable.images, "thanhhoa"));
        return data;
    }
}