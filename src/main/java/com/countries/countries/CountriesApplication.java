package com.countries.countries;

import java.util.Set;

import com.countries.model.PopulationDensity;

public class CountriesApplication {

	public static void main(String[] args) {
		populationDensity();
	}

	private static void populationDensity() {
		Set<PopulationDensity> populationDensity = Api.getPopulationDensity();

		int index = 1;
		for (PopulationDensity element : populationDensity) {
			System.out.println(index + ": " + element.country() + " " + element.populationDensity());
			index++;
		}
	}
}
