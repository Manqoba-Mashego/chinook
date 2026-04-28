/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.u24589137.u24589137_chinook;

/**
 *
 * @author manqo
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection connect() throws Exception {
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3308/u24589137_chinook",
            "root",
            "Pika1***"
        );
    }
}