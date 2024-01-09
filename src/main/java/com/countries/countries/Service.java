package com.countries.countries;

import java.util.Set;
import java.util.TreeSet;

import com.countries.model.BordersAsia;
import com.countries.model.PopulationDensity;
import com.fasterxml.jackson.databind.JsonNode;

public class Service {

    private final Controller controller;

    public Service(Controller controller) {
        this.controller = controller;
    }

    public Set<PopulationDensity> getPopulationDensity() {
        String query = "all?fields=name,area,population";
        JsonNode jsonNode = controller.get(query);

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

    public BordersAsia getMostBoredersAsia() {
        String query = "region/asia?fields=name,borders";
        JsonNode jsonNode = controller.get(query);

        BordersAsia result = new BordersAsia("", -1);
        for (JsonNode element : jsonNode) {
            int borders = element.findValue("borders").size();
            if (borders > result.borders()) {
                String country = element.findValue("common").asText();
                result = new BordersAsia(country, borders);
            }
        }

        return result;
    }
}
