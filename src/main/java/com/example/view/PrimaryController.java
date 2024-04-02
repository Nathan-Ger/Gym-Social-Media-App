package com.example.view;

import com.example.model.MongoDBConnect;
import com.example.model.User;
import java.io.IOException;

import javafx.fxml.FXML;
import java.time.LocalDate;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        // Test User
        User u = new User("Nathanael", "Germain", "germnl",
                "germnl@farmingdale.edu", "(516)305-0291",
                LocalDate.of(2001, 7, 23),
                205, 195, 170, 72, "testlink", 3600, 7);

        MongoDBConnect.insertUser(u);
    }
}
