package com.countries.model;

public record PopulationDensity(String country, Double populationDensity) implements Comparable<PopulationDensity> {

    @Override
    public int compareTo(PopulationDensity o) {
        int result = o.populationDensity.compareTo(populationDensity);
        if (result == 0) {
            return country.compareTo(o.country);
        }
        return result;
    }

}
