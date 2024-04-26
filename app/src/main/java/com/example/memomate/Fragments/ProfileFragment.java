package com.example.memomate.Fragments;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.memomate.Activities.IntroActivity;
import com.example.memomate.R;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;


public class ProfileFragment extends Fragment {

    Button btnChangePassword, btnLogout;
    EditText test;
    CardView cardUserName;
    TextView txtUserName1, txtUserName2;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getFormWidgets(view);
        addEvents(view);
    }

    public void getFormWidgets(View v)
    {
        btnChangePassword = v.findViewById(R.id.btnChangePassword);
        //test = v.findViewById(R.id.txttest);
        cardUserName = v.findViewById(R.id.cardUserName);
        txtUserName1 = v.findViewById(R.id.txtUserName1);
        txtUserName2 = v.findViewById(R.id.txtUserName2);
    }

    public void addEvents(View v)
    {
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyen sang Activity
            }
        });
        cardUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogMenu(v);
            }
        });
        txtUserName2.setText(txtUserName1.getText().toString());

        btnLogout = v.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                if (mGoogleSignInClient != null) {
                    mGoogleSignInClient.signOut(); // Đăng xuất khỏi Google nếu đang sử dụng đăng nhập Google
                }
                // Chuyển hướng người dùng đến màn hình đăng nhập
                Intent i = new Intent(getActivity(), IntroActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK); // Xóa mọi activity khác trên ngăn xếp
                startActivity(i);

            }
        });
    }
    private void showDialogMenu(View v)
    {
        final Dialog dialog = new Dialog(v.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_change_username);
        Window window = dialog.getWindow();
        if (window == null)
        {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setWindowAnimations(R.style.BottomDialogAnimation);

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAttributes);
        dialog.setCancelable(true);

        Button btnCancelDialog = dialog.findViewById(R.id.btnCancelDialog);
        Button btnSaveDialog = dialog.findViewById(R.id.btnSaveDialog);
        EditText edtUserName = dialog.findViewById(R.id.edtUserName);
        edtUserName.setText(txtUserName2.getText().toString());
        btnCancelDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSaveDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtUserName1.setText(edtUserName.getText().toString());
                txtUserName2.setText(edtUserName.getText().toString());

                dialog.dismiss();
            }
        });


        dialog.show();
    }


}