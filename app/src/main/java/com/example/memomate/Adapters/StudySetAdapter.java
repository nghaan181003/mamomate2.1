package com.example.memomate.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memomate.Activities.StudySetDetailActivity;
import com.example.memomate.Models.StudySet;
import com.example.memomate.R;

import java.util.ArrayList;

public class StudySetAdapter extends RecyclerView.Adapter<StudySetAdapter.FlashCardViewHolder>{
    Context context;
    ArrayList<StudySet> studySets;

    public StudySetAdapter(ArrayList<StudySet> studySetArrayList) {
        this.studySets = studySetArrayList;
    }

    @NonNull
    @Override
    public FlashCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_studyset, parent, false);
        return new FlashCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlashCardViewHolder holder, int position) {
        StudySet studySet = studySets.get(position);
        if (studySet == null)
        {
            return;
        }
        holder.avatar.setImageResource(studySet.getAvatar());
        holder.txtUserName.setText(studySet.getUserName());
        holder.txtTitle.setText(studySet.getName());
        holder.txtTerm.setText(String.valueOf(studySet.getQuantity()) + " terms");
        holder.cardStudySet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, StudySetDetailActivity.class);
                Bundle b = new Bundle();
                //StudySet S = new StudySet("Toan", R.drawable.han, "ngochandethuong", flashCards1);
                b.putSerializable("S", studySet);
                i.putExtras(b);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (studySets != null) {
            return studySets.size();
        }
        return 0;
    }

    public class FlashCardViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView txtUserName, txtTitle, txtTerm;
        CardView cardStudySet;
        public FlashCardViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            txtUserName = itemView.findViewById(R.id.txtUserName);
            txtTerm = itemView.findViewById(R.id.txtTerm);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            cardStudySet = itemView.findViewById(R.id.cardStudySet);
        }
    }
}

