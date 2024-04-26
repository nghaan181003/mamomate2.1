package com.example.memomate.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memomate.Activities.FolderActivity;
import com.example.memomate.Models.Folder;
import com.example.memomate.R;

import java.util.ArrayList;

public class FolderRecyclerViewAdapter extends RecyclerView.Adapter<FolderRecyclerViewAdapter.FolderViewHolder> {
    Context context;
    ArrayList<Folder> listFolder;
    public FolderRecyclerViewAdapter(Context context)
    {
        this.context = context;
    }

    public FolderRecyclerViewAdapter(ArrayList<Folder> folders) {
        this.listFolder = folders;
    }

    public void setData(ArrayList<Folder> list) {
        this.listFolder = list;
        notifyDataSetChanged();
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("MyAdapter", "onActivityResult");
    }
    @NonNull
    @Override
    public FolderRecyclerViewAdapter.FolderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_folder_tab_folder, parent, false);
        return new FolderRecyclerViewAdapter.FolderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FolderViewHolder holder, int position) {
        Folder folder = listFolder.get(position);
        if (folder == null)
        {
            return;
        }
        holder.avatar.setImageResource(folder.getAvatar());
        holder.userName.setText(folder.getAuthor());
        holder.txtTitleFolder.setText(folder.getTitle());
        holder.term.setText(String.valueOf(folder.getQuantity()) + " sets");

        holder.cardFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, FolderActivity.class);
                i.putExtra("getTitle", folder.getTitle());
                i.putExtra("getAvatar", folder.getAvatar());
                i.putExtra("getUserName", folder.getAuthor());
                i.putExtra("position", holder.getAdapterPosition());
                ((Activity)context).startActivityForResult(i, 12);
                //context.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        if (listFolder!=null)
        {
            return listFolder.size();
        }
        return 0;
    }

    public class FolderViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView txtTitleFolder, term, userName;
        CardView cardFolder;
        public FolderViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            txtTitleFolder = itemView.findViewById(R.id.txtTitleFolder);
            term = itemView.findViewById(R.id.term);
            userName = itemView.findViewById(R.id.userName);
            cardFolder = itemView.findViewById(R.id.cardFolder);
        }
    }
}
