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
    /**
     * @author Prajwol Shrestha
     * button clicked
     */    @FXML
    private void handleDashBtnAction(ActionEvent event) {
        if (checkFields()) {
            showAlert(Alert.AlertType.INFORMATION, "Validation Success", "All fields are valid.");
        }
    }

/**
 * @author Prajwol Shrestha
 * gets inputs
 */
    private boolean checkFields() {
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();
        String weight = weightTextField.getText();
        String height = heightTextField.getText();

/**
 *  @author: Prajwol Shrestha
 *  the validation if else to either move on or alert
 */
        if (firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty() || weight.isEmpty() || height.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Empty Fields", "Please fill in all fields.");
            return false;                                                   // for if any field is empty
        }
        try {
            // Validate phone number
            if (phoneNumber.length() != 7 && phoneNumber.length() != 10) { // if phone # is not 7 or 10
                showAlert(Alert.AlertType.ERROR, "Invalid Phone Number", "Phone number should be a valid 7 or 10 digit integer.");
                return false;
            }
            Long.parseLong(phoneNumber);    // alert if not a number
            Double.parseDouble(weight);     // alert exception if not a number

            String[] heightParts = height.split("'");   // height must be a 1 number or split by comma (5'11)
            if (heightParts.length != 2) {
                showAlert(Alert.AlertType.ERROR, "Invalid Height Format", "Height should be in feet & inches format (5'10).");
                return false;
            }
            Double.parseDouble(heightParts[0]);     // alerts if not a number
            Double.parseDouble(heightParts[1]);     // alerts if not a number

            return true;    // if all fields are valid
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Enter valid data; Ex: Praj, Shr, 5161231234, 153.1, 5'11.");
            return false;   // if even one field is invalid
        }
    }

    /**
     * @author: Prajwol Shrestha
     * alerts that get called
     * @param alertType
     * @param title
     * @param message
     */
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}