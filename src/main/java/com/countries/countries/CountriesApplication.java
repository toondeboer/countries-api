package com.countries.countries;

import java.util.Set;

import com.countries.model.BordersAsia;
import com.countries.model.PopulationDensity;

public class CountriesApplication {

	public static void main(String[] args) {
		Service service = new Service(new Controller());
		populationDensity(service);
		mostBoredersAsia(service);
	}

	private static void populationDensity(Service service) {
		Set<PopulationDensity> populationDensity = service.getPopulationDensity();

		System.out.println("\nCountries sorted on population density:");
		int index = 1;
		for (PopulationDensity element : populationDensity) {
			System.out.println(index + ": " + element.country() + " " + element.populationDensity());
			index++;
		}
	}

	private static void mostBoredersAsia(Service service) {
		BordersAsia bordersAsia = service.getMostBoredersAsia();
		System.out.println(
				"\nAsian country with most borders: " + bordersAsia.country() + " (" + bordersAsia.borders() + ")");
	}
}
