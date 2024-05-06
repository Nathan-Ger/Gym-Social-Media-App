package com.example.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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





    // praj
    // when start button clicked
    @FXML
    private void startJourneyButton() {
        if (checkFields()) {
            showAlert(Alert.AlertType.INFORMATION, "Validation Success", "All fields are valid.");
        }
    }
    // gets inputs
    private boolean checkFields() {
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();
        String weight = weightTextField.getText();
        String height = heightTextField.getText();

        // validatse phone number, weight, & height
        try {
            // phone # should be an int and weight and height are doubles
            int parsedPhoneNumber = Integer.parseInt(phoneNumber);
            double parsedWeight = Double.parseDouble(weight);

            String[] heightParts = height.split("'");
            if (heightParts.length != 2) {                              // validates "5'11"
                throw new NumberFormatException("Invalid height format");
            }
            double parsedHeightFeet = Double.parseDouble(heightParts[0]); // for geight ( ' )
            double parsedHeightInches = Double.parseDouble(heightParts[1]);

            return true; // if fields are valid

        } catch (NumberFormatException e) {
            if (e.getMessage().equals("Invalid height format")) { //if height is ivalid
                showAlert(Alert.AlertType.ERROR, "Invalid Height Format", "Height should be in feet & inches format (5'10)");
            }
            else if (phoneNumber.matches("\\D+")) { // if # is invalid
                showAlert(Alert.AlertType.ERROR, "Invalid Phone Number", "Phone number should be a valid integer; 123");
            }
            else if (weight.matches("[^0-9]+")) { // if weight is invalid
                showAlert(Alert.AlertType.ERROR, "Invalid Weight", "Weight should be a valid number; 150, 150.5.");
            }
            else { // if another reason of being invalid
                showAlert(Alert.AlertType.ERROR, "Invalid Input", "Enter valid data; Ex: Praj, Shr, 5161231234, 153.1, 5'11 ");
            }
            return false;
        }
    }
    // alert method / gets called
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
