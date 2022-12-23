package com.volkswagen.exercise.services;

import com.volkswagen.exercise.models.Catalog;
import com.volkswagen.exercise.models.Component;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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

		catalog.getComponentIds().forEach(id -> {
			Component component = componentService.getComponentById(id);
			if(component != null && component.getQuantity() > 0) {
				components.add(component);
			}
		});

		return components;
	}

	public void updateComponents(List<Component> components) {
		componentService.updateComponents(components);
	}
}
