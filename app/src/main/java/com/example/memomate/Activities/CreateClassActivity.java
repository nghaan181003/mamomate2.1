package com.example.memomate.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.memomate.R;

public class CreateClassActivity extends AppCompatActivity {
    TextView btnSave;
    ImageButton btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);
        getFormWidgets();
        addEvents();
    }
    public void  addEvents()
    {
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    public void getFormWidgets()
    {
        btnClose = (ImageButton) findViewById(R.id.btnClose);
        btnSave = (TextView) findViewById(R.id.btnSave);
    }
}