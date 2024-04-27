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

            // Rest of your code...
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

    public static void HTTPRequest() {
        try {
            
            // Create an HttpClient
            HttpClient client = HttpClient.newHttpClient();
            
            // Create a JSON string with the login data
            String loginData = new JSONObject()
                .put("email", "nathanlgermain@gmail.com")
                .put("password", "Angrybirds4fun!!")
                .put("username", "nathanlgermain")
                .toString();
            
            // Create a HttpRequest for the login
            HttpRequest loginRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + port + "/credentials/signup"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(loginData))
                .build();
            
            // Send the login request and get the response
            try {
                HttpResponse<String> loginResponse = client.send(loginRequest, HttpResponse.BodyHandlers.ofString());
                // Get the response content
                String responseContent = loginResponse.body();
                System.out.println(responseContent);
            } catch (IOException | InterruptedException e) {
                System.out.println("Error while logging in: " + e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stopServer();
        }
    }

}