package com.example.memomate.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memomate.Activities.ClassDetailActivity;
import com.example.memomate.Models.Class;
import com.example.memomate.R;

import java.util.ArrayList;

public class ClassRecyclerViewAdapter extends RecyclerView.Adapter<ClassRecyclerViewAdapter.CustomHolder> {

    private ArrayList<Class> classes;
    private Context context;

    public ClassRecyclerViewAdapter(ArrayList<Class> classes) {
        this.classes = classes;
    }

    @NonNull
    @Override
    public CustomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class_tab_classes, parent, false);
        return new CustomHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull CustomHolder holder, int position) {
        Class aClass = classes.get(position);
        holder.txtName.setText(aClass.getTitle());
        holder.txtQuantity.setText(String.valueOf(aClass.getSet_quantity()) + " sets");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ClassDetailActivity.class);
                i.putExtra("getTitle", aClass.getTitle());
                i.putExtra("getSet_quantity", aClass.getSet_quantity());
                i.putExtra("getMember_quantity", aClass.getMember_quantity());
                i.putExtra("position", holder.getAdapterPosition());
                ((Activity)context).startActivityForResult(i, 12);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (classes != null) return classes.size();
        return 0;
    }

    class CustomHolder extends RecyclerView.ViewHolder{

        TextView txtName, txtQuantity;
        public CustomHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);
        }
    }
}
