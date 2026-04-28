/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.u24589137.u24589137_chinook.controllers;

import com.u24589137.u24589137_chinook.dao.EmployeeDAO;
import com.u24589137.u24589137_chinook.models.Employee;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author manqo
 */
public class EmployeesController {
    @FXML private TableView<Employee> table;

    @FXML private TableColumn<Employee, String> firstNameCol;
    @FXML private TableColumn<Employee, String> lastNameCol;
    @FXML private TableColumn<Employee, String> titleCol;
    @FXML private TableColumn<Employee, String> cityCol;
    @FXML private TableColumn<Employee, String> countryCol;
    @FXML private TableColumn<Employee, String> phoneCol;
    @FXML private TableColumn<Employee, String> supervisorCol;

    @FXML
    public void initialize() {
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        supervisorCol.setCellValueFactory(new PropertyValueFactory<>("supervisor"));
        table.setItems(
            FXCollections.observableArrayList(
                EmployeeDAO.getEmployees()
            )
        );
    }
}
