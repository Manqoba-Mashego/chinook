/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.u24589137.u24589137_chinook.dao;

import com.u24589137.u24589137_chinook.DBConnection;
import com.u24589137.u24589137_chinook.models.Employee;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author manqo
 */
public class EmployeeDAO {
    public static List<Employee> getEmployees() {
        List<Employee> list = new ArrayList<>();

        try (Connection conn = DBConnection.connect()) {

            String sql = """
                         SELECT e.EmployeeID, e.LastName, e.FirstName, e.Title, e.City, e.Country, e.Phone,e.Email, CONCAT(s.LastName, " ", s.FirstName) AS Supervisor
                         FROM Employee e LEFT JOIN Employee s ON e.ReportsTo = s.EmployeeID
                         """; 
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                list.add(new Employee(
                        rs.getInt("EmployeeID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Title"),
                        rs.getString("City"),
                        rs.getString("Country"),
                        rs.getString("Phone"),
                        rs.getString("Supervisor")
                )); 
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
