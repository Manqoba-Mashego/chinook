package com.u24589137.u24589137_chinook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.u24589137.u24589137_chinook.DBConnection;
import com.u24589137.u24589137_chinook.models.Customer;

public class CustomerDAO {
    public static int getNextCustomerId() {
        String sql = "SELECT MAX(CustomerId) + 1 AS nextId FROM customer";
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
    

    public static List<Customer> getCustomers() {
        List<Customer> list = new ArrayList<>();

        String sql = "SELECT CustomerId, FirstName, LastName, Email, Phone,City, Address, Country FROM customer";

        try (Connection conn = DBConnection.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Customer(
                    rs.getInt("CustomerId"),
                    rs.getString("FirstName"),
                    rs.getString("LastName"),
                    rs.getString("Email"),
                    rs.getString("Phone"),
                    rs.getString("Address"),
                    rs.getString("City"),
                    rs.getString("Country")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void insertCustomer(String first, String last, String email, String phone, String address, String city, String country, String company, String state, String postalCode, String fax) {
        String sql = """
            INSERT INTO customer (CustomerId, FirstName, LastName, Email, Phone, Address, City, Country, Company, State, PostalCode, Fax)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, getNextCustomerId());
            stmt.setString(2, first);
            stmt.setString(3, last);
            stmt.setString(4, email);
            stmt.setString(5, phone);
            stmt.setString(6, address);
            stmt.setString(7, city);
            stmt.setString(8, country);
            stmt.setString(9, company);
            stmt.setString(10, state);
            stmt.setString(11, postalCode);
            stmt.setString(12, fax);

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateCustomer(int id, String first, String last, String email, String phone, String country) {
        String sql = """
            UPDATE customer
            SET FirstName=?, LastName=?, Email=?, Phone=?, Country=?
            WHERE CustomerId=?
        """;

        try (Connection conn = DBConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, first);
            stmt.setString(2, last);
            stmt.setString(3, email);
            stmt.setString(4, phone);
            stmt.setString(5, country);
            stmt.setInt(6, id);

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteCustomer(int id) {
        String sql = "DELETE FROM customer WHERE CustomerId=?";

        try (Connection conn = DBConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
