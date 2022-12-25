package com.volkswagen.exercise.services;

import com.volkswagen.exercise.models.Catalog;
import com.volkswagen.exercise.models.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ShopService {

	private ComponentService componentService;
	private CatalogService catalogService;

	public ShopService(ComponentService componentService, CatalogService catalogService) {
		this.componentService = componentService;
		this.catalogService = catalogService;
	}

	public List<Component> listComponentsForModelId(int modelId) {
		List<Component> components = new ArrayList<>();

		Catalog catalog = catalogService.getCatalogByModelId(modelId);

		if (catalog == null) {
			throw new NoSuchElementException("catalog with modelId "+modelId+" is not found.");
		}

		components = catalog.getComponents().stream().filter(component -> component.getQuantity() > 0).collect(Collectors.toList());

		return components;
	}

	public List<String> updateComponents(List<Component> components) {
		return componentService.updateComponents(components);
	}
}
