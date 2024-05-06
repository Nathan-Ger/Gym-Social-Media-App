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
    /**
     * back to login page  btn action
     * after pressing,
     * then redirect user to the loginPage  scene.
     * @param event
     */
    @FXML
    private void handleBackToLogInBtnAction(ActionEvent event) {
        try {
            // Load the login page FXML
            Parent loginPageParent = FXMLLoader.load(getClass().getResource("/com/example/loginPage.fxml"));
            Scene loginPageScene = new Scene(loginPageParent);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(loginPageScene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //todo - handle special cases!
    //(first name and last name)
    //all caps are not allowed
    //first letter uppercase for the user name
    //handle the mail format (example@gmail.com) @, gmail, .com need it.
    //password to short
    //password not strong enough (need extra char !@# , 1233 for a strong password.


    /**
     * sign up btn action
     * after pressing, gather the data then post to database.
     * then redirect user to the datafetch scene.
     * @param event
     */
    @FXML
    private void handleSignUpBtnAction(ActionEvent event) {
        try {
            //todo
            //write data to the database, then let user go the datafetch scene,
            //if the user already exist, let them know.
            //if any error with sign user up, let them know.


            Parent dataFetchPageParent = FXMLLoader.load(getClass().getResource("/com/example/datafetch.fxml"));
            Scene dataFetchPageScene = new Scene(dataFetchPageParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(dataFetchPageScene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
