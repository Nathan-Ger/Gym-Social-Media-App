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
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class SignUpPageController {

    // fx id for sign up page user, email & password
    @FXML
    private TextField userSignup;
    @FXML
    private TextField emailSignup;
    @FXML
    private PasswordField pwSignup;

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

    //todo
    //write data to the database, then let user go the datafetch scene,
    //if the user already exist, let them know.
    //if any error with sign user up, let them know.

    /**
     * sign up btn action
     * after pressing, gather the data then post to database.
     * then redirect user to the datafetch scene.
     * @param event
     */

    @FXML
    private void handleSignUpBtnAction(ActionEvent event) {
        /**
         * @author Prajwol Shrestha
         * Everything under this is for validation and opening the data fetch scene
         * gets user, email, password
         */
        String username = userSignup.getText();
        String email = emailSignup.getText();
        String password = pwSignup.getText();

        // alerts for if texts are not valid
        if (!validUsername(username)) {
            showAlert("Invalid Username", "Username must start with a capital letter and can only contain letters, numbers, period, and underscore and have no spaces");
        } else if (!validEmail(email)) {
            showAlert("Invalid Email", "Please enter a valid email address Ex: example@gmail.com ");
        } else if (password.length() < 8) {
            showAlert("Invalid Password", "Password must be at least 8 characters long, \ncontain at least one uppercase letter, one lowercase letter, \none digit, and one special character");
        } else if (!strongPassword(password)) {
            showAlert("Weak Password", "Password should contain at least one uppercase letter, one lowercase letter, one digit, and one special character");
        } else {
            try {

                // Load the DataFetch page FXML
                Parent dataFetchPageParent = FXMLLoader.load(getClass().getResource("/com/example/datafetch.fxml"));
                Scene dataFetchPageScene = new Scene(dataFetchPageParent);
                // Get the stage from the event that triggered the action
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                // Set the scene on the stage to switch to the DataFetch page
                window.setScene(dataFetchPageScene);
                window.show();
            } catch (Exception e) {
                e.printStackTrace();}
        }
    }

    /**
     * @author Prajwol Shrestha
     * @param username
     * @return
     * isvalidusername gets used in handleSignUpBtnAction
     * and more validation for email & password
     */
    private boolean validUsername(String username) {
        // this basiclly says is username field is empty or the first chatacter (0) is not uppercase then its invalid
        if (username.isEmpty() || !Character.isUpperCase(username.charAt(0))) {
            return false; // cehcks for uppercase
        }
        for (char c : username.toCharArray()) {
            // this checks for _ or .
            if (!Character.isLetterOrDigit(c) && c != '.' && c != '_') {
                return false;}
        }
        return true;
    }
    private boolean validEmail(String email) {      // checks for valid email
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");}
        // 1st bracket is allowing any letter and number, period, perecent,plus and hyphen
        // +@ is saying has to have @ and 2nd bracket is same as first but no percent or plus
        // +\\. is saying has a period and last bracket is saying any letters with min of 2 characters

    private boolean strongPassword(String password) {       // checks for valid password
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$");}
// in short makes sure it has AT LEAST 1 number, 1 lowercase, 1 uppercase, 1 of [@#$%^&+=!], no white spaces and at least 8 characters
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);  // alert methods / gets called
        alert.setContentText(message);
        alert.showAndWait();}
}