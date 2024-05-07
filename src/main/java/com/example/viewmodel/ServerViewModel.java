package com.example.viewmodel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

public class ServerViewModel
{

    private static Process process = null;
    private static ProcessBuilder processBuilder = new ProcessBuilder("node", "nodejs/server.js");
    private static String port = "";
    private static volatile boolean serverRunning = false;

    public static void startServer()
    {
        
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


//Author: Gagan
    // Methods to set user data fields
    public void setFirstName(JSONObject userData, String fName) {
        userData.put("fName", fName);
    }

    public void setLastName(JSONObject userData, String lName) {
        userData.put("lName", lName);
    }

    public void setUsername(JSONObject userData, String username) {
        userData.put("username", username);
    }

    public void setPhoneNumber(JSONObject userData, String phoneNumber) {
        userData.put("phoneNumber", phoneNumber);
    }

    public void setStartingWeight(JSONObject userData, double startingWeight) {
        userData.put("startingWeight", startingWeight);
    }

    public void setCurrentWeight(JSONObject userData, double currentWeight) {
        userData.put("currentWeight", currentWeight);
    }

    public void setGoalWeight(JSONObject userData, double goalWeight) {
        userData.put("goalWeight", goalWeight);
    }

    public void setHeight(JSONObject userData, double height) {
        userData.put("height", height);
    }

    public void setProfilePicture(JSONObject userData, String profilePicture) {
        userData.put("profilePicture", profilePicture);
    }

    public void setTotalTimeInGym(JSONObject userData, double totalTimeInGym) {
        userData.put("totalTimeInGym", totalTimeInGym);
    }

    public void setTotalCaloriesBurned(JSONObject userData, double totalCaloriesBurned) {
        userData.put("totalCaloriesBurned", totalCaloriesBurned);
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

        //Author: Gagan
        try {


            // Create JSON object for exercise data
            JSONObject exerciseData = new JSONObject()
                    .put("username", "johndoe")
                    .put("caloriesBurned", 150.0);

            // Create JSON object for post data
            JSONObject postData = new JSONObject()
                    .put("username", "johndoe")
                    .put("caption", "Having a great workout session!")
                    .put("likes", 10);

            // Create JSON object for location data
            JSONObject locationData = new JSONObject()
                    .put("averageRating", 4.5);

            // Create JSON object for review data
            JSONObject reviewData = new JSONObject()
                    .put("username", "johndoe")
                    .put("rating", 4.0)
                    .put("review", "Great experience!");


        // Below is an example of a login data being made.


            
            // Create a JSON string with the login data
            String loginData = new JSONObject()
                .put("email", "nathanlgermain@gmail.com")
                .put("password", "Angrybirds4fun!!")
                .put("username", "nathanlgermain")
                .toString();



            postHTTPRequest(exerciseData.toString(), "/exercises/add");
            postHTTPRequest(postData.toString(), "/posts/create");
            postHTTPRequest(locationData.toString(), "/locations/add");
            postHTTPRequest(reviewData.toString(), "/reviews/add");


            postHTTPRequest(loginData, "/credentials/login");


        } catch (Exception e) {
            e.printStackTrace();
        }

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