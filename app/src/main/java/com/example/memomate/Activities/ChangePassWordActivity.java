package com.example.memomate.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.memomate.R;

public class ChangePassWordActivity extends AppCompatActivity {

    ImageButton btnReturn;
    TextView btnSave, notification;
    EditText edtNewPass, edtConfirmPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass_word);

        getFormWidgets();
        addEvents();
    }

    public void getFormWidgets()
    {
        btnReturn = findViewById(R.id.btnReturn);
        btnSave = findViewById(R.id.btnSave);
        edtNewPass = findViewById(R.id.edtNewPass);
        edtConfirmPass = findViewById(R.id.edtConfirmPass);
        notification = findViewById(R.id.notification);
    }
    public void addEvents()
    {
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnSave.setVisibility(View.GONE);
        notification.setVisibility(View.GONE);
        edtNewPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                hideBtnSave();

            }
        });
        edtConfirmPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                hideBtnSave();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtNewPass.getText().toString().equals(edtConfirmPass.getText().toString()))
                {
                    Intent i = getIntent();
                    String newPass = edtConfirmPass.getText().toString();
                    i.putExtra("newPass", newPass);
                    setResult(65, i);
                    finish();
                }
                else
                {
                    notification.setVisibility(View.VISIBLE);
                    edtNewPass.setText("");
                    edtConfirmPass.setText("");
                    edtNewPass.requestFocus();
                }
            }
        });

    }
    public void hideBtnSave()
    {
        if (edtNewPass.getText().toString().trim().length() == 0 || edtConfirmPass.getText().toString().trim().length() == 0)
        {
            btnSave.setVisibility(View.GONE);
        }
        else btnSave.setVisibility(View.VISIBLE);
    }
}