package com.example.memomate.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import com.google.android.material.card.MaterialCardView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memomate.Models.StudySet;
import com.example.memomate.R;

import java.util.ArrayList;

public class StudySetSelectAdapter extends RecyclerView.Adapter<StudySetSelectAdapter.CustomHolder> {
    Context context;
    private ArrayList<StudySet> studySets;
    private ArrayList<StudySet> selectedStudySets = new ArrayList<>();
    public StudySetSelectAdapter(ArrayList<StudySet> studySets) {
        this.studySets = studySets;
    }

    @NonNull
    @Override
    public StudySetSelectAdapter.CustomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_study_set_tab_study_set, parent, false);
        return new StudySetSelectAdapter.CustomHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudySetSelectAdapter.CustomHolder holder, int position) {
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
                if(!selectedStudySets.contains(studySet))
                {
                    selectedStudySets.add(studySet);
                    holder.cardStudySet.setStrokeWidth(3);
                    holder.cardStudySet.setStrokeColor(ContextCompat.getColor(context, R.color.blue));
                }
                else {
                    holder.cardStudySet.setStrokeWidth(0);
                    selectedStudySets.remove(studySet);
                }
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
    public ArrayList<StudySet> getSelectedStudySets(){
        return selectedStudySets;
    }

    class CustomHolder extends RecyclerView.ViewHolder{
        ImageView avatar;
        TextView txtUserName, txtTitle, txtTerm;
        MaterialCardView cardStudySet;
        public CustomHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            txtUserName = itemView.findViewById(R.id.txtUserName);
            txtTerm = itemView.findViewById(R.id.txtTerm);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            cardStudySet = itemView.findViewById(R.id.cardStudySet);
        }

    }
}
