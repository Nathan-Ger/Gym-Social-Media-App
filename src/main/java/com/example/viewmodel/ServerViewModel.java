package com.example.viewmodel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

public class ServerViewModel {

    private static Process process = null;
    private static ProcessBuilder processBuilder = new ProcessBuilder("node", "nodejs/server.js");
    private static String port = "";
    private static volatile boolean serverRunning = false;

    public static void startServer() {

        try {
            process = processBuilder.start();
            serverRunning = true;
            final Process finalProcess = process;

            // Read and print the output in a separate thread
            new Thread(() -> {
                try (BufferedReader outputReader = new BufferedReader(new InputStreamReader(finalProcess.getInputStream()))) {
                    port = outputReader.readLine();
                    System.out.println(port);
                    String line;
                    while (serverRunning && (line = outputReader.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            // Read and print the error output in a separate thread
            new Thread(() -> {
                try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(finalProcess.getErrorStream()))) {
                    String line;
                    while (serverRunning && (line = errorReader.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void stopServer() {
        if (process != null) {
            serverRunning = false;
            port = "";
            process.destroy();
            process = null;
        }
    }

    public static void HTTPRequests() {

        // TODO: Create JSON Objects for all types of information we need to change.
        // Also create (inside the method) to actually pass the information to the postHTTPRequest method.

        /* Methods for users
         *
         * We need to be able to change all of the below
         * fName - String
         * lName - String
         * username - String
         * phoneNumber - String
         * startingWeight - double
         * currentWeight - double
         * goalWeight - double
         * height - double
         * profilePicture - String
         * totalTimeInGym - double
         * totalCaloriesBurned - double
         */

        /* Methods for Exercises
         *
         * We need to be able to change all of the below
         * username - String
         * caloriesBurned - double
         */

        /* Methods for Posts
         *
         * We need to be able to change all of the below
         * username - String
         * caption - String
         * likes - int
         */

        /* Methods for Locations
         *
         * We need to be able to change all of the below
         * averageRating - double
         * posts - Reviews[] <-- Ignore this one for now
         */

        /* Methods for Reviews
         *
         * We need to be able to change all of the below
         * username - String
         * rating - double
         * review - String
         */

        // Below is an example of a login data being made.

        try {

            // Create a JSON string with the login data
            String loginData = new JSONObject()
                    .put("email", "nathanlgermain@gmail.com")
                    .put("password", "Angrybirds4fun!!")
                    .put("username", "nathanlgermain")
                    .toString();

            postHTTPRequest(loginData, "/credentials/login");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Author: Gagan Sapkota
    // Methods to set user data fields
    public void setTotalTimeInGym(double totalTimeInGym) {
        String totalTimeInGymData = new JSONObject()
                .put("totalTimeInGym", totalTimeInGym)
                .toString();
        postHTTPRequest(totalTimeInGymData, "/users/updateTotalTimeInGym");
    }

    public void setProfilePicture(String profilePicture) {
        String profilePictureData = new JSONObject()
                .put("profilePicture", profilePicture)
                .toString();
        postHTTPRequest(profilePictureData, "/users/updateProfilePicture");
    }

    public void setFirstName(String fName) {
        String firstNameData = new JSONObject()
                .put("fName", fName)
                .toString();
        postHTTPRequest(firstNameData, "/users/updateFirstName");
    }

    public void setLastName(String lName) {
        String lastNameData = new JSONObject()
                .put("lName", lName)
                .toString();
        postHTTPRequest(lastNameData, "/users/updateLastName");
    }

    public void setUsername(String username) {
        String usernameData = new JSONObject()
                .put("username", username)
                .toString();
        postHTTPRequest(usernameData, "/users/updateUsername");
    }

    public void setPhoneNumber(String phoneNumber) {
        String phoneNumberData = new JSONObject()
                .put("phoneNumber", phoneNumber)
                .toString();
        postHTTPRequest(phoneNumberData, "/users/updatePhoneNumber");
    }

    public void setStartingWeight(double startingWeight) {
        String startingWeightData = new JSONObject()
                .put("startingWeight", startingWeight)
                .toString();
        postHTTPRequest(startingWeightData, "/users/updateStartingWeight");
    }

    public void setCurrentWeight(double currentWeight) {
        String currentWeightData = new JSONObject()
                .put("currentWeight", currentWeight)
                .toString();
        postHTTPRequest(currentWeightData, "/users/updateCurrentWeight");
    }

    public void setGoalWeight(double goalWeight) {
        String goalWeightData = new JSONObject()
                .put("goalWeight", goalWeight)
                .toString();
        postHTTPRequest(goalWeightData, "/users/updateGoalWeight");
    }

    public void setHeight(double height) {
        String heightData = new JSONObject()
                .put("height", height)
                .toString();
        postHTTPRequest(heightData, "/users/updateHeight");
    }

    public void setTotalCaloriesBurned(double caloriesBurned)
    {
        String totalCaloriesBurnedData = new JSONObject()
                .put("caloriesBurned", caloriesBurned)
                .toString();
        postHTTPRequest(totalCaloriesBurnedData, "/users/updateCaloriesBurned");
    }

    // Additional method to update any field for the user
    public void updateFieldUser(String field, String newValue) {
        String updateFieldData = new JSONObject()
                .put("field", field)
                .put("newValue", newValue)
                .toString();
        postHTTPRequest(updateFieldData, "/users/updateField");
    }

    // data is the JSON data we create. endpoint is the endpoint we want to send the data to.
    // endpoint should always start with a forward slash. then the file name, which should be the object you are sending to, then the method.
    // For example, if we are sending a login request, the endpoint should be "/credentials/login"
    // For changing a users name, it should be "/users/changeName".
    public static void postHTTPRequest(String data, String endpoint) {

        if (!serverRunning) {
            System.out.println("Server is not running.");
            return;
        }

        try {

            // Create an HttpClient
            HttpClient client = HttpClient.newHttpClient();

            // Create a HttpRequest for the login
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:" + port + endpoint))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(data))
                    .build();

            // Send the request and get the response.
            try {
                HttpResponse<String> loginResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
                // Get the response content
                String responseContent = loginResponse.body();
                System.out.println(responseContent); // TODO: For now we just print the response. Let Nathan Change this.
            } catch (IOException | InterruptedException e) {
                System.out.println("Error while logging in: " + e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeServer() {
        serverRunning = false;
        stopServer();
    }

}