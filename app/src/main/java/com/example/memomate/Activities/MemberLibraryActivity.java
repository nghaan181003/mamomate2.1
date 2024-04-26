package com.example.memomate.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.memomate.Adapters.TabPagerAdapter;
import com.example.memomate.Fragments.TabFoldersFragment;
import com.example.memomate.Fragments.TabStudySetsFragment;
import com.example.memomate.R;
import com.google.android.material.tabs.TabLayout;

public class MemberLibraryActivity extends AppCompatActivity {
    private ImageButton btnBack;
    private ViewPager viewPager;
    public TabLayout tabLayout;
    private TabPagerAdapter tabPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_library);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.view_pager);

        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void setUpViewPager(ViewPager viewPager) {
        tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager());
        tabPagerAdapter.addFragment(new TabStudySetsFragment());
        tabPagerAdapter.addFragment(new TabFoldersFragment());

        viewPager.setAdapter(tabPagerAdapter);
    }
}