package com.countries.countries;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Set;
import java.util.TreeSet;

import com.countries.model.PopulationDensity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Api {

    private static String baseUrl = "https://restcountries.com/v3.1/all";

    private static JsonNode get(String query) {
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

    public static Set<PopulationDensity> getPopulationDensity() {
        String query = "?fields=name,area,population";
        JsonNode jsonNode = get(query);

        Set<PopulationDensity> result = new TreeSet<>();
        for (JsonNode element : jsonNode) {
            String country = element.findValue("common").asText();
            double area = element.findValue("area").asDouble();
            area = area > 0 ? area : Double.POSITIVE_INFINITY;
            long population = element.findValue("population").asLong();

            double populationDensity = population / area;

            result.add(new PopulationDensity(country, populationDensity));
        }

        return result;
    }
}
