package com.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Login {

    private static final String API_URL = "http://localhost:4001/user/f";
    private static String response;

    public static void loginUser(String email, String password) {
        fetchUserData(); 
        if (response == null || response.isEmpty()) {
            System.out.println("No data available.");
            return;
        }

        JsonElement jsonElement = JsonParser.parseString(response);
        if (jsonElement.isJsonArray()) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            boolean loginSuccessful = false;
            for (JsonElement element : jsonArray) {
                JsonObject user = element.getAsJsonObject();
                if (user.get("email").getAsString().equals(email) &&
                    user.get("password").getAsString().equals(password)) {
                    System.out.println("Login successful for user: " + email);
                    System.out.println("Your data:");
                    System.err.println("---------------------------" + "\n");
                    DisplayUser.displayUserData(); 
                    System.err.println("---------------------------" + "\n");
                    loginSuccessful = true;
                    break;
                }
            }
            if (!loginSuccessful) {
                System.out.println("Login failed for user: " + email);
            }
        } else {
            System.out.println("Failed to parse response as JSON array.");
        }
    }

    private static void fetchUserData() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Login.response = response.body();
        } catch (Exception e) {
            System.err.println("Failed to fetch user data: " + e.getMessage());
            Login.response = null;
        }
    }
}
