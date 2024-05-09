package com.example.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class DataFetchController {
/*    Here are the fx:id values you can use in your controller:
            id: firstNameTextField - for the first name input field.
            id: lastNameTextField  - for the last name input field.
            id: phoneNumberTextField  - for the phone number input field.
            id: weightTextField - for the weight input field.
            id: heightTextField - for the height input field.
            id: startJourneyButton - for the button to start the journey.*/
    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextField weightTextField;

    @FXML
    private TextField heightTextField;

    @FXML
    private Button startJourneyButton;


    @FXML
    private ImageView searchingPhoto; // Ensure this matches the FX ID in your FXML file.

    public void initialize() {
        Image image = new Image(getClass().getResourceAsStream("/com/example/images/searching-looking.1024x822.png"));

        searchingPhoto.setImage(image);
    }

    //todo
    //after signup only, this page will load up
    //we fetch it only once
    //write to database from the textfields

    //make sure first name and last name are just strings and make sure phone num takes an integer and height takes an integer, weight only takes a double, if so cacth it and display to the user

    @FXML
    private void handleDashBtnAction() {
        if (checkFields()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/dashboard.fxml"));
                Parent dashboardParent = loader.load();
                Scene dashboardScene = new Scene(dashboardParent);

                Stage window = (Stage) startJourneyButton.getScene().getWindow();
                window.setScene(dashboardScene);
                window.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @author: Prajwol Shrestha
     * Validation
     * @return
     */
    private boolean checkFields() {
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();
        String weight = weightTextField.getText();
        String height = heightTextField.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty() || weight.isEmpty() || height.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Empty Fields", "Please fill in all fields.");
            return false;
        }

        try {
            if (phoneNumber.length() != 7 && phoneNumber.length() != 10) {
                showAlert(Alert.AlertType.ERROR, "Invalid Phone Number", "Phone number should be a valid 7 or 10 digit integer.");
                return false;       // Validate phone number
            }
            Long.parseLong(phoneNumber); // alerts if not a number

            // validates weight
            Double.parseDouble(weight); // alerts if not a number

            // validates height
            String[] heightParts = height.split("'");
            if (heightParts.length != 2) {
                showAlert(Alert.AlertType.ERROR, "Invalid Height Format", "Height should be in feet & inches format (5'10).");
                return false;
            }
            Double.parseDouble(heightParts[0]); // alerts if not a number
            Double.parseDouble(heightParts[1]); // alerts if not a number

            return true; //  fields are valid
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Enter valid data; Ex: Praj, Shr, 5161231234, 153.1, 5'11.");
            return false; // at least one field is invalid
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);     // alerts method
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}