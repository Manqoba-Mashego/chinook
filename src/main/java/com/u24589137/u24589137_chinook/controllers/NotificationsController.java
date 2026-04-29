package com.u24589137.u24589137_chinook.controllers;

import com.u24589137.u24589137_chinook.dao.CustomerDAO;
import com.u24589137.u24589137_chinook.models.Customer;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NotificationsController {
    @FXML private TableView<Customer> customerTable;

    @FXML private TableColumn<Customer, String> firstCol;
    @FXML private TableColumn<Customer, String> lastCol;
    @FXML private TableColumn<Customer, String> emailCol;
    @FXML private TableColumn<Customer, String> phoneCol;
    @FXML private TableColumn<Customer, String> countryCol;
    @FXML private TableColumn<Customer, String> addressCol;
    @FXML private TableColumn<Customer, String> cityCol;

    @FXML
    public void initialize() {
        firstCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
        refreshTable();
    }

    private void refreshTable() {
        customerTable.setItems(
            FXCollections.observableArrayList(
                CustomerDAO.getCustomers()
            )
        );
    }

    @FXML
    private void addCustomer() {
        Stage stage = new Stage();
        stage.setTitle("Add New Customer");
        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First Name");

        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone");

        TextField addressField = new TextField();
        addressField.setPromptText("Address");
        
        TextField cityField = new TextField();
        cityField.setPromptText("City");

        TextField countryField = new TextField();
        countryField.setPromptText("Country");

        TextField companyField = new TextField();
        companyField.setPromptText("Company (optional)");

        TextField stateField = new TextField();
        stateField.setPromptText("State (optional)");

        TextField postalCodeField = new TextField();
        postalCodeField.setPromptText("Postal Code");

        TextField faxField = new TextField();
        faxField.setPromptText("Fax (optional)");

        VBox layout = new VBox(10,
            firstNameField, 
            lastNameField,
            emailField, 
            phoneField,
            addressField,
            cityField,
            countryField,
            companyField,
            stateField,
            postalCodeField,
            faxField
        );

        Button saveBtn = new Button("Add");
        saveBtn.setOnAction(e -> {
            if (firstNameField.getText().isEmpty() || 
            lastNameField.getText().isEmpty() || 
            emailField.getText().isEmpty() ||
            phoneField.getText().isEmpty() ||
            addressField.getText().isEmpty() ||
            cityField.getText().isEmpty() ||
            postalCodeField.getText().isEmpty() ||
            countryField.getText().isEmpty()){
                showError("All required fields must be filled.");
                return;
            }

            CustomerDAO.insertCustomer(
                firstNameField.getText(), 
                lastNameField.getText(), 
                emailField.getText(), 
                phoneField.getText(), 
                addressField.getText(),
                cityField.getText(),
                countryField.getText(),
                companyField.getText(),
                stateField.getText(),
                postalCodeField.getText(),
                faxField.getText()
            );
            refreshTable();
            stage.close();
        });
        layout.getChildren().add(saveBtn);
        Scene scene = new Scene(layout, 300, 400);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void editCustomer() {
        Customer selected = customerTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showError("Please select a customer to edit.");
            return;
        }

        Stage stage = new Stage();
        stage.setTitle("Edit Customer");

        TextField firstField =   new TextField(selected.getFirstName());
        TextField lastField = new TextField(selected.getLastName());
        TextField emailField = new TextField(selected.getEmail());
        TextField phoneField = new TextField(selected.getPhone());
        TextField countryField = new TextField(selected.getCountry());

        firstField.setPromptText("First Name");
        lastField.setPromptText("Last Name");
        emailField.setPromptText("Email");
        phoneField.setPromptText("Phone");
        countryField.setPromptText("Country");

        Button saveBtn = new Button("Update");

        saveBtn.setOnAction(e -> {

            if (firstField.getText().isEmpty() ||
                lastField.getText().isEmpty() ||
                emailField.getText().isEmpty() ||
                phoneField.getText().isEmpty() ||
                countryField.getText().isEmpty()) {

                showError("All fields must be filled.");
                return;
            }

            CustomerDAO.updateCustomer(
                selected.getId(),
                firstField.getText(),
                lastField.getText(),
                emailField.getText(),
                phoneField.getText(),
                countryField.getText()
            );

            refreshTable();
            stage.close();
        });

        VBox layout = new VBox(10,
            new Label("First Name"), firstField,
            new Label("Last Name"), lastField,
            new Label("Email"), emailField,
            new Label("Phone"), phoneField,
            new Label("Country"), countryField,
            saveBtn
        );

        Scene scene = new Scene(layout, 300, 350);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void deleteCustomer() {
        Customer selected = customerTable.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        CustomerDAO.deleteCustomer(selected.getId());
        refreshTable();
    }

    private void showError(String message) {
        Stage errorStage = new Stage();
        VBox layout = new VBox(10);

        Label label = new Label(message);
        Button okBtn = new Button("OK");

        okBtn.setOnAction(e -> errorStage.close());

        layout.getChildren().addAll(label, okBtn);

        Scene scene = new Scene(layout, 250, 100);
        errorStage.setScene(scene);
        errorStage.setTitle("Error");
        errorStage.show();
    }
}
