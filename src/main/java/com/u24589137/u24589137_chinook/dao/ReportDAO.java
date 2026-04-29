package com.u24589137.u24589137_chinook.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.u24589137.u24589137_chinook.DBConnection;
import com.u24589137.u24589137_chinook.models.GenreReport;

public class ReportDAO {
    public static List<GenreReport> getGenreRevenueReport() {
        List<GenreReport> list = new ArrayList<>();

        String sql = """
            SELECT g.Name AS Genre,
                SUM(il.UnitPrice * il.Quantity) AS TotalRevenue
            FROM invoiceline il
            JOIN track t ON il.TrackId = t.TrackId
            JOIN genre g ON t.GenreId = g.GenreId
            GROUP BY g.Name
            ORDER BY TotalRevenue DESC
        """;

        try (Connection conn = DBConnection.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new GenreReport(
                    rs.getString("Genre"),
                    rs.getDouble("TotalRevenue")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
