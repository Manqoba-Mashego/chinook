package com.u24589137.u24589137_chinook.controllers;

import com.u24589137.u24589137_chinook.dao.ReportDAO;
import com.u24589137.u24589137_chinook.models.GenreReport;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ReportController {
    @FXML private TableView<GenreReport> reportTable;
    @FXML private TableColumn<GenreReport, String> genreCol;
    @FXML private TableColumn<GenreReport, Double> revenueCol;

    @FXML
    public void initialize() {
        genreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
        revenueCol.setCellValueFactory(new PropertyValueFactory<>("totalRevenue"));

        loadReport();
    }

    public void loadReport() {
        reportTable.setItems(
            FXCollections.observableArrayList(
                ReportDAO.getGenreRevenueReport()
            )
        );
    }

}
