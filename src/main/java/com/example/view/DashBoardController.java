package com.example.view;
import javafx.animation.FillTransition;
import javafx.animation.Interpolator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
public class DashBoardController {
//here are the navbar btns


    @FXML
    private void handleReviewButtonAction(ActionEvent event) {
        try {
            // Load the sign-up page FXML
            Parent map = FXMLLoader.load(getClass().getResource("/com/example/map.fxml"));
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
