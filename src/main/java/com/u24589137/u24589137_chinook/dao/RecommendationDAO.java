package com.u24589137.u24589137_chinook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.u24589137.u24589137_chinook.DBConnection;

public class RecommendationDAO {
    public static Map<String, Object> getCustomerSummary(int customerId) {
        Map<String, Object> map = new HashMap<>();

        String sql = """
            SELECT 
                SUM(i.Total) AS totalSpent,
                COUNT(i.InvoiceId) AS totalPurchases,
                MAX(i.InvoiceDate) AS lastPurchase
            FROM invoice i
            WHERE i.CustomerId = ?
        """;

        try (Connection conn = DBConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                map.put("totalSpent", rs.getDouble("totalSpent"));
                map.put("totalPurchases", rs.getInt("totalPurchases"));
                map.put("lastPurchase", rs.getString("lastPurchase"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }
    

    public static String getFavouriteGenre(int customerId) {
        String sql = """
            SELECT g.Name
            FROM invoiceline il
            JOIN track t ON il.TrackId = t.TrackId
            JOIN genre g ON t.GenreId = g.GenreId
            JOIN invoice i ON il.InvoiceId = i.InvoiceId
            WHERE i.CustomerId = ?
            GROUP BY g.Name
            ORDER BY COUNT(*) DESC
            LIMIT 1
        """;

        try (Connection conn = DBConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("Name");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "None";
    }

    public static List<String> getRecommendations(int customerId, String genre) {
        List<String> list = new ArrayList<>();

        String sql = """
            SELECT t.Name
            FROM track t
            JOIN genre g ON t.GenreId = g.GenreId
            WHERE g.Name = ?
            AND t.TrackId NOT IN (
                SELECT il.TrackId
                FROM invoiceline il
                JOIN invoice i ON il.InvoiceId = i.InvoiceId
                WHERE i.CustomerId = ?
            )
            LIMIT 10
        """;

        try (Connection conn = DBConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, genre);
            stmt.setInt(2, customerId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("Name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
