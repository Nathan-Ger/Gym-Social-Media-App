package com.example.view;

import javafx.fxml.FXML;
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

    //make sure phone num, weight and height only takes a integer and double, if so cacth it and display to the user


}