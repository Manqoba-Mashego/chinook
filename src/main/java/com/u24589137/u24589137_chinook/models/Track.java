/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.u24589137.u24589137_chinook.models;

/**
 *
 * @author manqo
 */
public class Track {
    private int id;
    private String name;
    private String album;
    private String mediaType;
    private String genre;
    private String artist;
    private String composer;
    private int milliseconds;
    private int bytes;
    private float unitPrice;


    public Track(int id, String name, String album, String mediaType, String genre, String artist, String composer, int milliseconds, int bytes, float unitPrice){
        this.id = id;
        this.name = name;
        this.album = album; 
        this.mediaType = mediaType;
        this.genre = genre;
        this.artist = artist;
        this.composer = composer;
        this.milliseconds = milliseconds;
        this.bytes = bytes;
        this.unitPrice = unitPrice;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }
    public String getAlbum(){
        return album;
    }
    public String getMediaType(){
        return mediaType;
    }
    public String getGenre(){
        return genre;
    }
    public String getArtist(){
        return artist;
    }
    public String getComposer(){
        return composer;
    }
    public int getMilliseconds(){
        return milliseconds;
    }
    public int getBytes(){
        return bytes;
    }
    public float getUnitPrice(){
        return unitPrice;
    }
}

