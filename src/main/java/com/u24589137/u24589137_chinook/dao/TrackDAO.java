/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.u24589137.u24589137_chinook.dao;

import com.u24589137.u24589137_chinook.DBConnection;
import com.u24589137.u24589137_chinook.models.Album;
import com.u24589137.u24589137_chinook.models.Genre;
import com.u24589137.u24589137_chinook.models.MediaType;
import com.u24589137.u24589137_chinook.models.Track;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author manqo
 */
public class TrackDAO {
    public static int getNextTrackId() {
    String sql = "SELECT MAX(TrackId) + 1 AS nextId FROM track";
    try (Connection conn = DBConnection.connect();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        if (rs.next()) {
            return rs.getInt("nextId");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 1;
}
    public static List<Track> getTracks(){
        List<Track> list = new ArrayList<>();
        try (Connection conn = DBConnection.connect()){
            String sql = """
                        SELECT t.TrackId, t.Name, a.Title as Album, artist.Name as Artist, m.Name as MediaType, g.Name as Genre, t.Composer, t.Milliseconds, t.Bytes, t.UnitPrice
                        FROM track t
                        JOIN album a ON a.AlbumId = t.AlbumId
                        JOIN artist ON a.ArtistId = artist.ArtistId
                        JOIN mediatype m ON t.MediaTypeId = m.MediaTypeId
                        JOIN genre g ON g.GenreId = t.GenreId
                        """;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                list.add(new Track(
                    rs.getInt("TrackID"), 
                    rs.getString("Name"), 
                    rs.getString("Album"), 
                    rs.getString("MediaType"), 
                    rs.getString("Genre"), 
                    rs.getString("Artist"),
                    rs.getString("Composer"), 
                    rs.getInt("Milliseconds"), 
                    rs.getInt("Bytes"),
                    rs.getFloat("UnitPrice")
                ));
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }

    public static List<Album> getAlbums(){
        List<Album> list = new ArrayList<>();
        try (Connection conn = DBConnection.connect()){
            String sql = """
                        SELECT AlbumId, Title FROM album
                        """;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                list.add(new Album(
                    rs.getInt("AlbumId"), 
                    rs.getString("Title")
                ));
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }

    public static List<Genre> getGenres(){
        List<Genre> list = new ArrayList<>();
        try (Connection conn = DBConnection.connect()){
            String sql = """
                        SELECT GenreId, Name FROM genre
                        """;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                list.add(new Genre(
                    rs.getInt("GenreId"), 
                    rs.getString("Name")
                ));
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }
    public static List<MediaType> getMediaTypes(){
        List<MediaType> list = new ArrayList<>();
        try (Connection conn = DBConnection.connect()){
            String sql = """
                        SELECT MediaTypeId, Name FROM mediatype
                        """;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                list.add(new MediaType(
                    rs.getInt("MediaTypeId"), 
                    rs.getString("Name")
                ));
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }

    public static void insertTrack(String name, int albumId, int genreId, int mediaTypeId, String composer, int milliseconds, int bytes, double unitPrice){
    
        String sql = """    
                INSERT INTO track (TrackId, Name, AlbumId, MediaTypeId, GenreId, Composer, Milliseconds, Bytes, UnitPrice)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
        try (Connection conn = DBConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, getNextTrackId());
            stmt.setString(2, name);
            stmt.setInt(3, albumId);
            stmt.setInt(4, mediaTypeId);
            stmt.setInt(5, genreId);
            stmt.setString(6, composer);
            stmt.setInt(7, milliseconds);
            stmt.setInt(8, bytes);
            stmt.setDouble(9, unitPrice);

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
