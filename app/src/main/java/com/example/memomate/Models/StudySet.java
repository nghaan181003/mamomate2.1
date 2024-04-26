package com.example.memomate.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

public class StudySet implements Serializable{
    private String name;

    private int avatar;

    private String userName;

    private ArrayList<FlashCard> flashCardList;

    public StudySet() {
    }

    public StudySet(String name, int avatar, String userName, ArrayList<FlashCard> flashCardList) {
        this.name = name;
        this.avatar = avatar;
        this.userName = userName;
        this.flashCardList = flashCardList;
    }

    public int getQuantity()
    {
        return flashCardList.size();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<FlashCard> getFlashCardList() {
        return flashCardList;
    }

    public void setFlashCardList(ArrayList<FlashCard> flashCardList) {
        this.flashCardList = flashCardList;
    }
}
