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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.beans.binding.Bindings;

/**
 *
 * @author manqo
 */
public class EmployeesController {
    @FXML private TableView<Employee> table;
    @FXML private TextField searchField;

    @FXML private TableColumn<Employee, String> firstNameCol;
    @FXML private TableColumn<Employee, String> lastNameCol;
    @FXML private TableColumn<Employee, String> titleCol;
    @FXML private TableColumn<Employee, String> cityCol;
    @FXML private TableColumn<Employee, String> countryCol;
    @FXML private TableColumn<Employee, String> phoneCol;
    @FXML private TableColumn<Employee, String> emailCol;
    @FXML private TableColumn<Employee, String> supervisorCol;
    @FXML private TableColumn<Employee, Boolean> isActiveCol;

    @FXML
    public void initialize() {
        
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        supervisorCol.setCellValueFactory(new PropertyValueFactory<>("supervisor"));
        isActiveCol.setCellValueFactory(cellData ->
            new javafx.beans.property.SimpleBooleanProperty(true)
        );

        isActiveCol.setCellFactory(col -> new javafx.scene.control.TableCell<>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item ? "True" : "False");
                }
            }
        });

        var data = FXCollections.observableArrayList(EmployeeDAO.getEmployees());
        FilteredList<Employee> filteredData = new FilteredList<>(data, p -> true);

        searchField.textProperty().addListener((obs, oldVal, newVal) -> {
        filteredData.setPredicate(emp -> {
            if (newVal == null || newVal.isEmpty()) return true;

            String keyword = newVal.toLowerCase();
            if (emp.getFirstName().toLowerCase().contains(keyword)) return true;
            if (emp.getLastName().toLowerCase().contains(keyword)) return true;
            if (emp.getCity().toLowerCase().contains(keyword)) return true;
            return false;
        });
    });

        SortedList<Employee> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        table.setItems(sortedData);

        table.setFixedCellSize(25);
        table.prefHeightProperty().bind(
            table.fixedCellSizeProperty().multiply(
                javafx.beans.binding.Bindings.size(table.getItems()).add(1)
            )
        );
    }
}
