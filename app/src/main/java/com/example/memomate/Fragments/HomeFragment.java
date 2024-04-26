package com.example.memomate.Fragments;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.memomate.Adapters.ClassAdapter;
import com.example.memomate.Adapters.FolderAdapter;
import com.example.memomate.Adapters.NotificationAdapter;
import com.example.memomate.Adapters.StudySetAdapter;
import com.example.memomate.Models.Class;
import com.example.memomate.Models.FlashCard;
import com.example.memomate.Models.Folder;
import com.example.memomate.Models.Notification;
import com.example.memomate.Models.StudySet;
import com.example.memomate.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private FirebaseDatabase database;
    private ViewPager studySetViewPager, folderViewPager, classViewPager;
    private LinearLayoutManager linearLayoutManager;
    private FolderAdapter folderAdapter;
    private ClassAdapter classAdapter;
    private NotificationAdapter notificationAdapter;
    private ImageButton btnNotification;
    private ArrayList<Notification> notificationArrayList;
    private SearchResultFragment searchResultsFragment;
    private TextView btnViewAllSets, btnViewAllFolders, btnViewAllClasses;
    private View searchView;
    ViewPager2 viewPagerStudySet, viewPagerFolder, viewPagerClass;
    StudySetAdapter studySetAdapter;
    ArrayList<StudySet> studySets = new ArrayList<>();
    ArrayList<Folder> folders = new ArrayList<>();
    ArrayList<Class> classes = new ArrayList<>();
    private Handler slideHandler = new Handler();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        studySetSlider();
        folderSlider();
        classSlider();
        addEvents(view);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    public void initView(View view){
        searchView = view.findViewById(R.id.searchView);
        viewPagerStudySet = view.findViewById(R.id.studyset_viewpager);
        viewPagerFolder = view.findViewById(R.id.folder_viewpager);
        viewPagerClass = view.findViewById(R.id.class_viewpager);
    }
    public void addEvents(View view){
        btnNotification = view.findViewById(R.id.btn_notification);
        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotificationBottomSheetDialog(v);
            }
        });

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchResultsFragment = new SearchResultFragment();

                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.addToBackStack(null);
                transaction.replace(R.id.search_results_container, searchResultsFragment).commit();
            }
        });
        btnViewAllSets = view.findViewById(R.id.btnViewAllSets);
        btnViewAllSets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToLibraryFragment();
            }
        });
        btnViewAllFolders = view.findViewById(R.id.btnViewAllFolders);
        btnViewAllFolders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToLibraryFragment();
                switchToTab(v, 2);
            }
        });
        btnViewAllClasses = view.findViewById(R.id.btnViewAllClasses);
        btnViewAllClasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToClassFragment();
            }
        });
    }
    private void studySetSlider() {
        studySets = getListStudySet();
        //studySets = getDataFromFireBase();
        studySetAdapter = new StudySetAdapter(studySets);
        viewPagerStudySet.setAdapter(studySetAdapter);
        viewPagerStudySet.setClipToPadding(false);
        viewPagerStudySet.setClipChildren(false);
        viewPagerStudySet.setOffscreenPageLimit(2);
        viewPagerStudySet.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(10));

        viewPagerStudySet.setPageTransformer(compositePageTransformer);
        viewPagerStudySet.setCurrentItem(0);
        viewPagerStudySet.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                slideHandler.removeCallbacks(slideRStudySet);
            }
        });
    }
    private void folderSlider() {
        folders = getListFolder();
        folderAdapter = new FolderAdapter(folders);
        viewPagerFolder.setAdapter(folderAdapter);
        viewPagerFolder.setClipToPadding(false);
        viewPagerFolder.setClipChildren(false);
        viewPagerFolder.setOffscreenPageLimit(2);
        viewPagerFolder.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(10));


        viewPagerFolder.setPageTransformer(compositePageTransformer);
        viewPagerFolder.setCurrentItem(0);
        viewPagerFolder.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                slideHandler.removeCallbacks(slideRFolder);
            }
        });
    }
    private void classSlider() {
        classes = getListClass();
        classAdapter = new ClassAdapter(classes);
        viewPagerClass.setAdapter(classAdapter);
        viewPagerClass.setClipToPadding(false);
        viewPagerClass.setClipChildren(false);
        viewPagerClass.setOffscreenPageLimit(2);
        viewPagerClass.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(10));

        viewPagerClass.setPageTransformer(compositePageTransformer);
        viewPagerClass.setCurrentItem(0);
        viewPagerClass.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                slideHandler.removeCallbacks(slideRClass);
            }
        });
    }

    private Runnable slideRStudySet = new Runnable() {
        @Override
        public void run() {
            viewPagerStudySet.setCurrentItem(viewPagerStudySet.getCurrentItem());
        }
    };
    private Runnable slideRFolder = new Runnable() {
        @Override
        public void run() {
            viewPagerFolder.setCurrentItem(viewPagerFolder.getCurrentItem());
        }
    };
    private Runnable slideRClass = new Runnable() {
        @Override
        public void run() {
            viewPagerClass.setCurrentItem(viewPagerClass.getCurrentItem());
        }
    };
    public void switchToTab(View view, int position) {
        LibraryFragment libraryFragment = (LibraryFragment) getChildFragmentManager().findFragmentById(R.id.view_pager);

        if (libraryFragment != null) {
            // Call the switchToTab method with the desired tab position
            libraryFragment.switchToTab(1); // 1 for the second tab, adjust as needed
        }
    }

    public void switchToLibraryFragment() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        LibraryFragment libraryFragment = new LibraryFragment();
        fragmentTransaction.replace(R.id.container, libraryFragment);
        fragmentTransaction.commit();
        setSelectedItem(R.id.nav_library);
    }
    public void switchToClassFragment() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ClassFragment classFragment = new ClassFragment();
        fragmentTransaction.replace(R.id.container, classFragment);
        fragmentTransaction.commit();
        setSelectedItem(R.id.nav_class);
    }
    public void setSelectedItem(int itemId) {
        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottom_nav);
        bottomNavigationView.getMenu().findItem(itemId).setChecked(true);    }

    private ArrayList<StudySet> getListStudySet(){

        ArrayList<StudySet> studySets = new ArrayList<>();
        ArrayList<FlashCard> flashCardList = new ArrayList<>();
        studySets.add(new StudySet("Hello", R.drawable.images, "thanhhoa11", flashCardList));
        studySets.add(new StudySet("Hello", R.drawable.images, "thanhhoa",flashCardList));
        studySets.add(new StudySet("Hello", R.drawable.images, "thanhhoa",flashCardList));
        studySets.add(new StudySet("Hello", R.drawable.images, "thanhhoa",flashCardList));
        return studySets;
    }
    private ArrayList<Folder> getListFolder(){

        ArrayList<Folder> folders = new ArrayList<>();
        folders.add(new Folder("Hello", 2, R.drawable.images, "thanhhoa"));
        folders.add(new Folder("Xin chao", 2, R.drawable.images, "hoa"));
        folders.add(new Folder("Hello", 2, R.drawable.images, "thanhhoa"));
        folders.add(new Folder("Hello", 2, R.drawable.images, "thanhhoa"));
        folders.add(new Folder("Hello", 2, R.drawable.images, "thanhhoa"));
        return folders;
    }
    private ArrayList<Class> getListClass() {
        ArrayList<Class> classes = new ArrayList<>();
        classes.add(new Class("Hello", 2, 3));
        classes.add(new Class("Helloaaa", 2, 3));
        classes.add(new Class("Hello", 2, 3));
        classes.add(new Class("Hello", 2, 3));
        classes.add(new Class("Hello", 2, 3));
        return classes;
    }
    private void showNotificationBottomSheetDialog(View view) {

        final Dialog dialog = new Dialog(view.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_notification);
        linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView notificationRecyclerView = (RecyclerView) dialog.findViewById(R.id.recyclerview_notification);
        notificationArrayList = new ArrayList<Notification>();

        int[] avatars = {R.drawable.ic_book, R.drawable.ic_wave, R.drawable.ic_ideas, R.drawable.ic_fire, R.drawable.ic_fire, R.drawable.ic_wave, R.drawable.ic_wave, R.drawable.ic_fire, R.drawable.ic_fire, R.drawable.ic_wave, R.drawable.ic_wave, R.drawable.ic_fire};
        String welcomeMessage = getString(R.string.content_notification);

        String[] titles = {welcomeMessage,welcomeMessage,welcomeMessage,welcomeMessage,welcomeMessage,welcomeMessage,welcomeMessage,welcomeMessage,welcomeMessage,welcomeMessage,welcomeMessage,welcomeMessage};
        for (int i = 0; i < avatars.length; i++) {
            Notification notification = new Notification(avatars[i], titles[i]);
            notificationArrayList.add(notification);
        }

        notificationAdapter = new NotificationAdapter(notificationArrayList);
        notificationRecyclerView.setLayoutManager(linearLayoutManager);
        notificationRecyclerView.setAdapter(notificationAdapter);

        ImageButton btnClose = dialog.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = com.google.android.material.R.style.Animation_Design_BottomSheetDialog;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }
    private ArrayList<StudySet> getDataFromFireBase(){
        ArrayList<StudySet> studySets = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("StudySets");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    StudySet studySet = dataSnapshot.getValue(StudySet.class);
                    studySets.add(studySet);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return studySets;

    }
}