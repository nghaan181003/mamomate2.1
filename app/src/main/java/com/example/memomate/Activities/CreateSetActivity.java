package com.example.memomate.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.memomate.Adapters.CardAdapter;
import com.example.memomate.Models.FlashCard;
import com.example.memomate.R;

import java.util.ArrayList;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class CreateSetActivity extends AppCompatActivity {
    RecyclerView rcvCard;
    CardAdapter cardAdapter;
    ImageButton btnAdd, btnSetting, btnDone, btnReturn;
    TextView btnDescription;
    CardView cardDescription, flashCard;
    ArrayList<FlashCard> listCard = new ArrayList<>();
    NestedScrollView scrollView;
    ImageView btnDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_set);
        getFormWidgets();
        addEvents();


    }
    public void getFormWidgets()
    {
        rcvCard = findViewById(R.id.rcv_card);
        btnAdd=findViewById(R.id.btnAdd);
        btnSetting=findViewById(R.id.btnSetting);
        btnDone= findViewById(R.id.btnDone);
        btnReturn= findViewById(R.id.btnReturn);
        btnDescription= findViewById(R.id.btnDescription);
        cardDescription= findViewById(R.id.cardDescription);
        //scrollView= findViewById(R.id.scrollView);
        cardAdapter = new CardAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvCard.setLayoutManager(linearLayoutManager);

        cardAdapter.setData(getListCard());
        rcvCard.setAdapter(cardAdapter);
    }
    public void addEvents()
    {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardAdapter.addCard();
                rcvCard.scrollToPosition(listCard.size() - 1);
                Scroll();
            }
        });
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreateSetActivity.this, SettingActivity.class);
                startActivity(i);
            }
        });
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        cardDescription.setVisibility(View.GONE);
        btnDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDescription.setVisibility(View.GONE);
                cardDescription.setVisibility(View.VISIBLE);
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                listCard.remove(position);
                cardAdapter.notifyDataSetChanged();
            }
            @Override
            public void onChildDraw (Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive){
                new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addBackgroundColor(ContextCompat.getColor(CreateSetActivity.this, R.color.my_background))
                        .addActionIcon(R.drawable.delete_white)
                        .create()
                        .decorate();
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        });
        itemTouchHelper.attachToRecyclerView(rcvCard);
    }


    private ArrayList<FlashCard> getListCard() {
        listCard.add(new FlashCard("Hello", "Hi"));
        return listCard;
    }

    private void Scroll()
    {
        final View layout_create_set = findViewById(R.id.layout_create_set);
        layout_create_set.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                layout_create_set.getWindowVisibleDisplayFrame(r);
                int height = layout_create_set.getRootView().getHeight() - r.height();
                if (height > 0.25*layout_create_set.getRootView().getHeight()) {
                    if (listCard.size() > 0 ) {
                        rcvCard.scrollToPosition(listCard.size() - 1);
                        layout_create_set.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            }
        });
    }





}