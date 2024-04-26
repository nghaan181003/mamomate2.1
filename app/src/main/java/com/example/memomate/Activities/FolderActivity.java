package com.example.memomate.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.memomate.Adapters.StudySetRecyclerViewAdapter;
import com.example.memomate.Models.FlashCard;
import com.example.memomate.Models.StudySet;
import com.example.memomate.R;

import java.util.ArrayList;

public class FolderActivity extends AppCompatActivity {
    ImageButton btnReturn, btnAdd, btnMenu;
    RecyclerView rcvFolder;
    ArrayList<StudySet> studySets = new ArrayList<>();
    CardView cardAddSet;
    TextView txtTitleFolder, txtQuantity, txtAuthor;
    ImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);
        initView();
        createRecyclerView();
        populateDummyFlashcards();

    }

    private void initView()
    {
        btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FolderActivity.this, AddSetActivity.class);
//                i.putExtra("getTitle", folder.getTitle());
//                i.putExtra("getAvatar", folder.getAvatar());
//                i.putExtra("getUserName", folder.getAuthor());
//                i.putExtra("position", holder.getAdapterPosition());
                ((Activity)FolderActivity.this).startActivityForResult(i, 12);
            }
        });
        btnMenu = findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogMenu();
            }
        });
        rcvFolder = findViewById(R.id.rcvFolder);
        cardAddSet = findViewById(R.id.cardAddSet);
        cardAddSet.setVisibility(View.GONE);
        txtTitleFolder = findViewById(R.id.txtTitleFolder);  // Cai nay lay du lieu
        txtQuantity = findViewById(R.id.txtQuantity);
        txtAuthor = findViewById(R.id.txtAuthor);
        avatar = findViewById(R.id.avatar);
        txtQuantity.setText(String.valueOf(studySets.size()) + " sets");


        rcvFolder = findViewById(R.id.rcvFolder);
    }

    private void showDialogMenu() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_folder_menu);
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

        TextView btnEdit = dialog.findViewById(R.id.btnEdit);
        TextView btnAdd = dialog.findViewById(R.id.btnAdd);
        TextView btnDelete = dialog.findViewById(R.id.btnDelete);
        TextView btnCancel = dialog.findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FolderActivity.this, CreateFolderActivity.class);
                startActivity(i);
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // dô của th hòa
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // dô của th hòa
            }
        });
        dialog.show();
    }


    private void createRecyclerView()
    {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvFolder.setLayoutManager(linearLayoutManager);
        StudySetRecyclerViewAdapter studySetAdapter = new StudySetRecyclerViewAdapter(studySets);
        rcvFolder.setAdapter(studySetAdapter);

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
        ArrayList<StudySet> newStudySets = new ArrayList<>();

        Intent i = getIntent();
        if(i.getSerializableExtra("getStudySets") != null){
            newStudySets = (ArrayList<StudySet>) i.getSerializableExtra("getStudySets");
            for (int j = 0; j < newStudySets.size(); j++) {
                studySets.add(newStudySets.get(j));
            }
        }
        if(i.getStringExtra("getTitle") != null){
            txtTitleFolder.setText(i.getStringExtra("getTitle"));
            avatar.setImageResource(i.getIntExtra("getAvatar", 0));
            txtAuthor.setText(i.getStringExtra("getUserName"));
        }
        txtQuantity.setText(String.valueOf(studySets.size()) + " sets");


    }
}