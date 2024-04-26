package com.example.memomate.Models;

public class Class {
    private String title;
    private int set_quantity;
    private int member_quantity;

    public Class() {
    }

    public Class(String title, int set_quantity) {
        this.title = title;
        this.set_quantity = set_quantity;
    }

    public Class(String title, int set_quantity, int member_quantity) {
        this.title = title;
        this.set_quantity = set_quantity;
        this.member_quantity = member_quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSet_quantity() {
        return set_quantity;
    }

    public void setSet_quantity(int set_quantity) {
        this.set_quantity = set_quantity;
    }

    public int getMember_quantity() {
        return member_quantity;
    }

    public void setMember_quantity(int member_quantity) {
        this.member_quantity = member_quantity;
    }
}
