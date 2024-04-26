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
import androidx.viewpager.widget.ViewPager;

import com.example.memomate.Activities.CreateFolderActivity;
import com.example.memomate.Activities.CreateSetActivity;
import com.example.memomate.Adapters.TabPagerAdapter;
import com.example.memomate.R;
import com.google.android.material.tabs.TabLayout;

public class LibraryFragment extends Fragment {
    private ViewPager viewPager;
    public TabLayout tabLayout;
    private TabPagerAdapter tabPagerAdapter;
    private ImageButton btnCreate;
    private Intent i;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.view_pager);

        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == 1){
                        i = new Intent(view.getContext(), CreateFolderActivity.class);
                    }else {
                        i = new Intent(view.getContext(), CreateSetActivity.class);
                    }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        btnCreate = view.findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });
    }

    private void setUpViewPager(ViewPager viewPager) {
        tabPagerAdapter = new TabPagerAdapter(getChildFragmentManager());
        tabPagerAdapter.addFragment(new TabStudySetsFragment());
        tabPagerAdapter.addFragment(new TabFoldersFragment());

        viewPager.setAdapter(tabPagerAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.view_pager);

        return view;
    }
    public void switchToTab(int position){
//        if (tabLayout != null && tabLayout.getTabCount() > position) {
//            TabLayout.Tab tab = tabLayout.getTabAt(position);
//            if (tab != null) {
//                tab.select();
//            }
//        }
        tabPagerAdapter.getItem(2);

    }
}