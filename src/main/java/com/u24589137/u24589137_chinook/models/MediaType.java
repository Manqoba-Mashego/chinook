package com.u24589137.u24589137_chinook.models;

public class MediaType {
    private int id;
    private String name;

    public MediaType(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId(){
        return id;
    }
    public String getTitle(){
        return name;
    }

    @Override
    public String toString() {
        return name; 
    }
}
