package com.example.view;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LoginPageController {
    @FXML
    private ImageView fitnessPhoto; // Ensure this matches the FX ID in your FXML file.

    public void initialize() {
        Image image = new Image(getClass().getResourceAsStream("/com/example/images/health-sports.738x1024.png"));
        fitnessPhoto.setImage(image);
    }
}
