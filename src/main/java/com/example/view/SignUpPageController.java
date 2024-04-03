package com.example.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SignUpPageController {
    @FXML
    private ImageView fitnessPhoto1; // Ensure this matches the FX ID in your FXML file.

    public void initialize() {
        Image image = new Image(getClass().getResourceAsStream("/com/example/images/yoga-relax.871x1024.png"));
        fitnessPhoto1.setImage(image);
    }

    @FXML
    private void handleBackToLogInBtnAction(ActionEvent event) {
        try {
            // Load the login page FXML
            Parent loginPageParent = FXMLLoader.load(getClass().getResource("/com/example/loginPage.fxml"));
            Scene loginPageScene = new Scene(loginPageParent);

            // Get the stage from the event that triggered the action
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            // Set the scene on the stage to switch to the login page
            window.setScene(loginPageScene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
