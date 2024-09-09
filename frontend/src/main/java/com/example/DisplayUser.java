package com.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class DisplayUser {
    private static final String API_URL = "http://localhost:4001/user/f";
    
    public static void displayUserData() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(DisplayUser::parseResponse)
                .join();
    }

    private static void parseResponse(String responseBody) {
        try {
            JSONArray jsonArray = new JSONArray(responseBody);
            System.out.println("User List:");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject user = jsonArray.getJSONObject(i);
                System.out.println("ID: " + user.getInt("id"));
                System.out.println("Email: " + user.getString("email"));
                System.out.println("Name: " + user.getString("name"));
                System.out.println("Created At: " + user.getString("createdAt"));
                System.out.println("-----------");
            }
        } catch (Exception e) {
            System.err.println("Failed to parse response: " + e.getMessage());
        }
    }
}
