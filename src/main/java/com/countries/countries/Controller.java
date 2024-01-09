package com.countries.countries;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Controller {

    private String baseUrl = "https://restcountries.com/v3.1/";

    public JsonNode get(String query) {
        JsonNode jsonNode = null;

        try {
            URI uri = new URI(baseUrl + query);
            URL url = uri.toURL();

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                ObjectMapper objectMapper = new ObjectMapper();
                jsonNode = objectMapper.readTree(response.toString());
            } else {
                System.out.println("Error in fetching data");
            }
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonNode;
    }

}
