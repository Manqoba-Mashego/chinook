package com.u24589137.u24589137_chinook.controllers;

import com.u24589137.u24589137_chinook.dao.CustomerDAO;
import com.u24589137.u24589137_chinook.dao.RecommendationDAO;
import com.u24589137.u24589137_chinook.models.Customer;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class RecommendationsController {
    @FXML private ComboBox<Customer> customerBox;
    @FXML private Label totalSpentLabel;
    @FXML private Label totalPurchasesLabel;
    @FXML private Label lastPurchaseLabel;
    @FXML private Label favouriteGenreLabel;
    @FXML private TableView<String> recommendationTable;

    @FXML
    public void initialize() {
        customerBox.setItems(
            FXCollections.observableArrayList(CustomerDAO.getCustomers())
        );

        customerBox.valueProperty().addListener((obs, oldVal, newVal) -> {
        if (newVal != null) {
            onCustomerSelected();
        }
    });
    }


    @FXML
    public void onCustomerSelected() {
        Customer selected = customerBox.getValue();
        if (selected == null) return;

        int id = selected.getId();

        var summary = RecommendationDAO.getCustomerSummary(id);
        totalSpentLabel.setText("Total: " + summary.get("totalSpent"));
        totalPurchasesLabel.setText("Purchases: " + summary.get("totalPurchases"));
        lastPurchaseLabel.setText("Last: " + summary.get("lastPurchase"));

        String genre = RecommendationDAO.getFavouriteGenre(id);
        favouriteGenreLabel.setText("Favourite: " + genre);

        recommendationTable.setItems(
            FXCollections.observableArrayList(
                RecommendationDAO.getRecommendations(id, genre)
            )
        );
    }
}
