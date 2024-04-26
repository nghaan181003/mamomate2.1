package com.example.memomate.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.memomate.Adapters.FolderRecyclerViewAdapter;
import com.example.memomate.Adapters.MemberAdapter;
import com.example.memomate.Models.Folder;
import com.example.memomate.Models.Member;
import com.example.memomate.R;

import java.util.ArrayList;

public class ClassDetailActivity extends AppCompatActivity {
    RecyclerView rcvMember, rcvFolder;
    MemberAdapter memberAdapter;
    FolderRecyclerViewAdapter folderAdapter;
    ArrayList<Member> listMember = new ArrayList<>();
    ArrayList<Folder> listFolder = new ArrayList<>();
    ImageButton btnReturn, btnShare, btnMenu;
    CardView layoutAddSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);

        loadTab();
        getFormWidgets();
        addEvents();
    }

    public void getFormWidgets()
    {
        btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnShare = findViewById(R.id.btnShare);
        btnMenu = findViewById(R.id.btnMenu);
        layoutAddSet = findViewById(R.id.layoutAddSet);

        rcvMember = findViewById(R.id.rcvMember);
        memberAdapter = new MemberAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvMember.setLayoutManager(linearLayoutManager);
        memberAdapter.setData(getListMember());
        rcvMember.setAdapter(memberAdapter);

        rcvFolder = findViewById(R.id.rcvClassFolder);
        folderAdapter = new FolderRecyclerViewAdapter(this);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvFolder.setLayoutManager(linearLayoutManager1);
        folderAdapter.setData(getListFolder());
        rcvFolder.setAdapter(folderAdapter);

    }
    public void loadTab()
    {
        final TabHost tab = (TabHost) findViewById(R.id.tabHost);
        tab.setup();
        TabHost.TabSpec spec;
        spec = tab.newTabSpec("t1");
        spec.setContent(R.id.class_sets);
        spec.setIndicator("Sets");

        tab.addTab(spec);
        spec = tab.newTabSpec("t2");
        spec.setContent(R.id.class_members);
        spec.setIndicator("Members");
        tab.addTab(spec);
        tab.setCurrentTab(0);
    }
    public void addEvents()
    {
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogMenu();
            }
        });
        layoutAddSet.setVisibility(View.GONE);
    }
    private void showDialogMenu()
    {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_class_menu);
        Window window = dialog.getWindow();
        if (window == null)
        {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setWindowAnimations(R.style.BottomDialogAnimation);

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.BOTTOM;
        window.setAttributes(windowAttributes);
        dialog.setCancelable(true);

        TextView btnInvite = dialog.findViewById(R.id.btnInvite);
        TextView btnAddFolder = dialog.findViewById(R.id.btnAddFolder);
        TextView btnDrop = dialog.findViewById(R.id.btnDrop);
        TextView btnCancel = dialog.findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Link moi
            }
        });
        dialog.show();
    }
    private ArrayList<Member> getListMember()
    {
        listMember.add(new Member("Ngoc Han",(R.drawable.han)));
        listMember.add(new Member("Ngoc Han",(R.drawable.han)));
        listMember.add(new Member("Ngoc Han",(R.drawable.han)));
        listMember.add(new Member("Ngoc Han",(R.drawable.han)));
        listMember.add(new Member("Ngoc Han",(R.drawable.han)));

        return listMember;
    }
    private ArrayList<Folder> getListFolder()
    {
        ArrayList<Folder> data = new ArrayList<>();
        data.add(new Folder("Hello", 2, R.drawable.images, "thanhhoa"));
        data.add(new Folder("Hello", 2, R.drawable.images, "thanhhoa"));
        data.add(new Folder("Hello", 2, R.drawable.images, "thanhhoa"));
        data.add(new Folder("Hello", 2, R.drawable.images, "thanhhoa"));
        data.add(new Folder("Hello", 2, R.drawable.images, "thanhhoa"));
        return data;
    }
}