package com.example.memomate.Models;
public class Member {
    private String userName;
    private int avatar;

    public Member(String userName, int avatar) {
        this.userName = userName;
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
    public Member() {
    }
}
