package com.example.memomate.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memomate.Activities.MemberLibraryActivity;
import com.example.memomate.Models.Member;
import com.example.memomate.R;

import java.util.ArrayList;


public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberViewHolder>
{
    Context context;
    ArrayList<Member> listMember;

    public MemberAdapter(Context context) {
        this.context = context;
    }
    public void setData(ArrayList<Member> list)
    {
        this.listMember = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class_members, parent, false);
        return new MemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder holder, int position) {
        Member member = listMember.get(position);
        if (member == null)
        {
            return;
        }
        holder.avatar.setImageResource(member.getAvatar());
        holder.userName.setText(member.getUserName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MemberLibraryActivity.class);
                i.putExtra("getUserName", member.getUserName());
                i.putExtra("getAvatar", member.getAvatar());
                i.putExtra("position", holder.getAdapterPosition());
                ((Activity)context).startActivityForResult(i, 12);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (listMember != null) {
            return listMember.size();
        }
        return 0;
    }

    public class MemberViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView userName;
        CardView cardView;
        public MemberViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            userName = itemView.findViewById(R.id.userName);
            cardView = itemView.findViewById(R.id.cardMember);
        }
    }
}