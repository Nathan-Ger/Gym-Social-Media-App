package com.example.view;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class MapController{
    @FXML
    private LineChart<String, Number> weeklyProgressChart;
    public WebView mapWebView;

    public void initialize() {


        WebEngine webEngine = mapWebView.getEngine();
        webEngine.setJavaScriptEnabled(true); // Ensure JavaScript is enabled
        webEngine.load(getClass().getResource("/map.html").toExternalForm());


    }

    //here are the navbar btns

    @FXML
    private void handleDashButtonAction(ActionEvent event) {
        try {
            // Load the sign-up page FXML
            Parent map = FXMLLoader.load(getClass().getResource("/com/example/dashboard.fxml"));
            Scene mapView = new Scene(map);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(mapView);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//this is for the edit btn
    @FXML
    private void handleEditButtonAction(ActionEvent event) {
        try {
            // Load the sign-up page FXML
            Parent profile = FXMLLoader.load(getClass().getResource("/com/example/profile.fxml"));
            Scene profileView = new Scene(profile);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(profileView);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleTrackingButtonAction(ActionEvent event) {
        try {
            // Load the sign-up page FXML
            Parent map = FXMLLoader.load(getClass().getResource("/com/example/tracking.fxml"));
            Scene mapView = new Scene(map);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(mapView);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

