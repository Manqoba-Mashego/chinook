/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.u24589137.u24589137_chinook.controllers;

import com.u24589137.u24589137_chinook.dao.TrackDAO;
import com.u24589137.u24589137_chinook.models.Track;
import com.u24589137.u24589137_chinook.models.Album;
import com.u24589137.u24589137_chinook.models.Genre;
import com.u24589137.u24589137_chinook.models.MediaType;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author manqo
 */
public class TracksController {
    @FXML private TableView<Track> tracksTable;

    @FXML private TableColumn<Track, String> trackCol;
    @FXML private TableColumn<Track, String> albumCol;
    @FXML private TableColumn<Track, String> artistCol;
    @FXML private TableColumn<Track, String> genreCol;
    @FXML private TableColumn<Track, String> mediaTypeCol;
    @FXML private TableColumn<Track, String> composerCol;
    @FXML private TableColumn<Track, String> millisecondsCol;
    @FXML private TableColumn<Track, String> bytesCol;
    @FXML private TableColumn<Track, String> unitPriceCol;

    @FXML 
    public void initialize(){
        trackCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        albumCol.setCellValueFactory(new PropertyValueFactory<>("album"));
        artistCol.setCellValueFactory(new PropertyValueFactory<>("artist"));
        genreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
        mediaTypeCol.setCellValueFactory(new PropertyValueFactory<>("mediaType"));
        composerCol.setCellValueFactory(new PropertyValueFactory<>("composer"));
        millisecondsCol.setCellValueFactory(new PropertyValueFactory<>("milliseconds"));
        bytesCol.setCellValueFactory(new PropertyValueFactory<>("bytes"));
        unitPriceCol.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tracksTable.setItems(
            FXCollections.observableArrayList(
                TrackDAO.getTracks()
            )
        );
    }
    @FXML
    private void openAddTrackDialog() {
        Stage stage = new Stage();
        stage.setTitle("Add New Track");

        TextField nameField = new TextField();
        nameField.setPromptText("Track Name");

        TextField composerField = new TextField();
        composerField.setPromptText("Composer");

        TextField msField = new TextField();
        msField.setPromptText("Milliseconds");

        TextField bytesField = new TextField();
        bytesField.setPromptText("Bytes");

        TextField priceField = new TextField();
        priceField.setPromptText("Price");

        ComboBox<Album> albumBox = new ComboBox<>();
        albumBox.setItems(FXCollections.observableArrayList(TrackDAO.getAlbums()));

        ComboBox<Genre> genreBox = new ComboBox<>();
        genreBox.setItems(FXCollections.observableArrayList(TrackDAO.getGenres()));

        ComboBox<MediaType> mediaBox = new ComboBox<>();
        mediaBox.setItems(FXCollections.observableArrayList(TrackDAO.getMediaTypes()));
        Label albumLabel = new Label("Select Album");
        Label genreLabel = new Label("Select Genre");
        Label mediaLabel = new Label("Select Media Type");
        VBox layout = new VBox(10,
            nameField,
            albumLabel,
            albumBox,
            genreLabel,
            genreBox,
            mediaLabel,
            mediaBox,
            composerField,
            msField,
            bytesField,
            priceField
        );

        Button saveBtn = new Button("Save");

        
        saveBtn.setOnAction(e -> {
            
            if (nameField.getText().isEmpty() ||
                composerField.getText().isEmpty() ||
                msField.getText().isEmpty() ||
                bytesField.getText().isEmpty() ||
                priceField.getText().isEmpty() ||
                albumBox.getValue() == null ||
                genreBox.getValue() == null ||
                mediaBox.getValue() == null) {

                showError("All fields must be filled.");
                return;
            }
            try {
                int milliseconds = Integer.parseInt(msField.getText());
                int bytes = Integer.parseInt(bytesField.getText());
                double price = Double.parseDouble(priceField.getText());

                if (milliseconds <= 0 || bytes <= 0 || price < 0) {
                    showError("Values must be positive.");
                    return;
                }

                TrackDAO.insertTrack(
                    nameField.getText(),
                    albumBox.getValue().getId(),
                    genreBox.getValue().getId(),
                    mediaBox.getValue().getId(),
                    composerField.getText(),
                    milliseconds,
                    bytes,
                    price
                );

                refreshTable();
                stage.close();

            } catch (NumberFormatException ex) {
                showError("Milliseconds, Bytes, and Price must be numbers.");
            }
        });
        layout.getChildren().add(saveBtn);

        Scene scene = new Scene(layout, 300, 400);
        scene.getStylesheets().add(
            getClass().getResource("/css/style.css").toExternalForm()
        );
        stage.setScene(scene);
        stage.show();
    }
    private void refreshTable() {
        tracksTable.setItems(
            FXCollections.observableArrayList(
                TrackDAO.getTracks()
            )
        );
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
