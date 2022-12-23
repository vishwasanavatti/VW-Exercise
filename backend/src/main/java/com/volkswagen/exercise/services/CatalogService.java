package com.volkswagen.exercise.services;

import com.volkswagen.exercise.models.Catalog;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatalogService {

	private static List<Catalog> catalogs;

	static {
		Catalog catalog1 = new Catalog(1, "VW 1", Arrays.asList(1, 2));
		Catalog catalog2 = new Catalog(2, "VW 2", Arrays.asList(3, 4, 5));
		Catalog catalog3 = new Catalog(3, "VW 3", Arrays.asList(2, 4, 5));
		Catalog catalog4 = new Catalog(4, "VW 4", Arrays.asList(1, 2, 3, 4, 5));
		Catalog catalog5 = new Catalog(5, "VW 5", Arrays.asList(2, 4, 5));

		catalogs = new ArrayList<>(Arrays.asList(catalog1, catalog2, catalog3, catalog4, catalog5));
	}

	public List<Catalog> getAllCatalogs() {
		return catalogs;
	}

	public Catalog getCatalogByModelId(int modelId) {
		return catalogs.stream().filter(c -> c.getModel() == modelId).findFirst().orElse(null);
	}
}
