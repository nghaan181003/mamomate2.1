package com.example.memomate.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memomate.Models.FlashCard;
import com.example.memomate.R;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder>{
    Context context;
    ArrayList<FlashCard> listCard;

    public CardAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<FlashCard> list) {
        this.listCard = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_create_set, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        FlashCard card = listCard.get(position);
        if (card == null) {
            return;
        }
        holder.edtTerm.setText(card.getTerm());
        holder.edtDifinition.setText(card.getDefinition());
        if (position == listCard.size() - 1) {
            holder.edtTerm.requestFocus();
        }
    }

    @Override
    public int getItemCount() {
        if (listCard != null) {
            return listCard.size();
        }
        return 0;
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        EditText edtTerm, edtDifinition;
        ImageView swipe_right;
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            edtTerm = itemView.findViewById(R.id.edtTerm);
            edtDifinition = itemView.findViewById(R.id.edtDefinition);
            //swipe = itemView.findViewById(R.id.swipe_layout);
            //swipe_right = itemView.findViewById(R.id.swipe_right);

        }
    }
    public void addCard() {
        listCard.add(new FlashCard());
        notifyItemInserted(listCard.size());
    }

}

