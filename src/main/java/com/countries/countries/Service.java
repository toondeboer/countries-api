package com.countries.countries;

import java.util.Set;
import java.util.TreeSet;

import com.countries.model.BordersAsia;
import com.countries.model.PopulationDensity;
import com.fasterxml.jackson.databind.JsonNode;

public class Service {

    public static Set<PopulationDensity> getPopulationDensity() {
        String query = "all?fields=name,area,population";
        JsonNode jsonNode = Controller.get(query);

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

    public static BordersAsia getMostBoredersAsia() {
        String query = "region/asia?fields=name,borders,continents";
        JsonNode jsonNode = Controller.get(query);

        BordersAsia result = new BordersAsia("", -1l);
        for (JsonNode element : jsonNode) {
            long borders = element.findValue("borders").size();
            if (borders > result.borders()) {
                String country = element.findValue("common").asText();
                result = new BordersAsia(country, borders);
            }
        }

        return result;
    }
}
