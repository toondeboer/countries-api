package com.countries.model;

public record BordersAsia(String country, Integer borders) implements Comparable<BordersAsia> {

    @Override
    public int compareTo(BordersAsia o) {
        int result = o.borders.compareTo(borders);
        if (result == 0) {
            return country.compareTo(o.country);
        }
        return result;
    }

}
