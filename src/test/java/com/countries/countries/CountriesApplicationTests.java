package com.countries.countries;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.countries.model.BordersAsia;
import com.countries.model.PopulationDensity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

class CountriesApplicationTests {

	@Test
	void populationDensityTest() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("[{\"common\":\"Netherlands\",\"area\":10,\"population\":100}");
		sb.append(",{\"common\":\"Belgium\",\"area\":30,\"population\":600}");
		sb.append(",{\"common\":\"Germany\",\"area\":20,\"population\":800}");
		sb.append(",{\"common\":\"Spain\",\"area\":50,\"population\":200}");
		sb.append(",{\"common\":\"Italy\",\"area\":40,\"population\":400}");
		sb.append(",{\"common\":\"Portugal\",\"area\":0,\"population\":100}");
		sb.append(",{\"common\":\"France\",\"area\":10,\"population\":0}");
		sb.append(",{\"common\":\"Argentina\",\"area\":0,\"population\":200}]");

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(sb.toString());
		Controller mockController = mock(Controller.class);
		when(mockController.get("all?fields=name,area,population")).thenReturn(jsonNode);
		Service service = new Service(mockController);

		List<PopulationDensity> populationDensity = new ArrayList<>();
		populationDensity.add(new PopulationDensity("Germany", 40d));
		populationDensity.add(new PopulationDensity("Belgium", 20d));
		populationDensity.add(new PopulationDensity("Italy", 10d));
		populationDensity.add(new PopulationDensity("Netherlands", 10d));
		populationDensity.add(new PopulationDensity("Spain", 4d));
		populationDensity.add(new PopulationDensity("Argentina", 0d));
		populationDensity.add(new PopulationDensity("France", 0d));
		populationDensity.add(new PopulationDensity("Portugal", 0d));

		assertEquals(populationDensity, new ArrayList<>(service.getPopulationDensity()));
	}

	@Test
	void mostBoredersAsiaTest() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("[{\"common\":\"China\",\"borders\":[\"border-1\",\"border-2\",\"border-3\"]}");
		sb.append(",{\"common\":\"Vietnam\",\"borders\":[\"border-1\"]}");
		sb.append(",{\"common\":\"Japan\",\"borders\":[\"border-1\",\"border-2\",\"border-3\",\"border-4\"]}]");
		sb.append(",{\"common\":\"Indonesia\",\"borders\":[\"border-2\"]}");
		sb.append(",{\"common\":\"Thailand\",\"borders\":[\"border-1\",\"border-3\"]}");

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(sb.toString());
		Controller mockController = mock(Controller.class);
		when(mockController.get("region/asia?fields=name,borders")).thenReturn(jsonNode);
		Service service = new Service(mockController);

		BordersAsia bordersAsia = new BordersAsia("Japan", 4);

		assertEquals(bordersAsia, service.getMostBoredersAsia());
	}

}
