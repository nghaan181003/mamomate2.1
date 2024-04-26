package com.example.memomate.Models;

public class Folder {
    private String title;
    private int quantity;
    private int avatar;
    private String author;

    public Folder() {
    }

    public Folder(String title, int quantity, int avatar, String author) {
        this.title = title;
        this.quantity = quantity;
        this.avatar = avatar;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
