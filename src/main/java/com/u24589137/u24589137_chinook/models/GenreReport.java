package com.u24589137.u24589137_chinook.models;

public class GenreReport {
    private String genre;
    private double totalRevenue;

    public GenreReport(String genre, double totalRevenue) {
        this.genre = genre;
        this.totalRevenue = totalRevenue;
    }

    public String getGenre() {
        return genre;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    } 
}
