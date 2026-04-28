package com.u24589137.u24589137_chinook.models;

public class Album {
    private int id;
    private String title;

    public Album(int id, String title){
        this.id = id;
        this.title = title;
    }

    public int getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }

    @Override
    public String toString() {
        return title; 
    }
}
