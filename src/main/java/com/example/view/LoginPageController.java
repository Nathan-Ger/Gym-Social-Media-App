package com.example.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class LoginPageController {

    // fx id for login page text boxes
    @FXML
    private TextField userLogin;
    @FXML
    private TextField pwLogin;


    @FXML
    private ImageView fitnessPhoto; // Ensure this matches the FX ID in your FXML file.

    public void initialize() {
        Image image = new Image(getClass().getResourceAsStream("/com/example/images/health-sports.738x1024.png"));
        fitnessPhoto.setImage(image);
    }

    @FXML
    private void handleSignInBtnAction(ActionEvent event) {
        try {
            // Load the sign-up page FXML
            Parent signUpPageParent = FXMLLoader.load(getClass().getResource("/com/example/signUpPage.fxml"));
            Scene signUpPageScene = new Scene(signUpPageParent);

            // Get the stage from the event that triggered the action
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the scene on the stage to switch to the sign-up page
            window.setScene(signUpPageScene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}