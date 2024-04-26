package com.example.memomate.Models;

public class User {
    private int avatar;
    private String userName;
    private String passWord;

    public User() {
    }

    public User(int avatar, String userName, String passWord) {
        this.avatar = avatar;
        this.userName = userName;
        this.passWord = passWord;
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

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
