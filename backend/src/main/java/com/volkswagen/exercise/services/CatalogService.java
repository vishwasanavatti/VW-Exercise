package com.volkswagen.exercise.services;

import com.volkswagen.exercise.entities.Catalog;
import com.volkswagen.exercise.models.ModelCatalog;
import com.volkswagen.exercise.repositories.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatalogService {

	@Autowired
	private CatalogRepository catalogRepository;

	// static data was created initially to create backend skeleton and then data moved to database
//	private static List<Catalog> catalogs;
//
//	static {
//		Component component1 = new Component(1, "component 1", 10.00, 15);
//		Component component2 = new Component(2, "component 2", 12.00, 25);
//		Component component3 = new Component(3, "component 3", 124.00, 59);
//		Component component4 = new Component(4, "component 4", 100.00, 80);
//		Component component5 = new Component(5, "component 5", 23.00, 112);
//
//		Catalog catalog1 = new Catalog(1, "VW 1", new HashSet<>(Arrays.asList(component1, component2)));
//		Catalog catalog2 = new Catalog(2, "VW 2", new HashSet<>(Arrays.asList(component3, component4, component5)));
//		Catalog catalog3 = new Catalog(3, "VW 3", new HashSet<>(Arrays.asList(component2, component4, component5)));
//		Catalog catalog4 = new Catalog(4, "VW 4", new HashSet<>(Arrays.asList(component1, component2, component3, component4, component5)));
//		Catalog catalog5 = new Catalog(5, "VW 5", new HashSet<>(Arrays.asList(component2, component4, component5)));
//
//		catalogs = new ArrayList<>(Arrays.asList(catalog1, catalog2, catalog3, catalog4, catalog5));
//	}

	public List<ModelCatalog> getAllCatalogs() {
		List<Catalog> catalogs = catalogRepository.findAll();

		return catalogs.stream().map(c -> c.toModelCatalog(c)).collect(Collectors.toList());
	}

	public ModelCatalog getCatalogByModelId(int modelId) {
		Catalog catalog = catalogRepository.findById(modelId).orElse(null);
		if(catalog == null) return null;

		return catalog.toModelCatalog(catalog);
	}
}
