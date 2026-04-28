/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.u24589137.u24589137_chinook.controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author manqo
 */
public class MainController {
    @FXML
    private BorderPane root;

    @FXML
    public void showEmployees() {
        try {
            Parent view = FXMLLoader.load(getClass().getResource("/com/u24589137/chinook/employees.fxml"));
            root.setCenter(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
