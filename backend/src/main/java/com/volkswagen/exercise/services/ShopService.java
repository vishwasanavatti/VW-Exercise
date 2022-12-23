package com.volkswagen.exercise.services;

import com.volkswagen.exercise.models.Catalog;
import com.volkswagen.exercise.models.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
			return null;
		}

		catalog.getComponentIds().forEach(id -> {
			Component component = componentService.getComponentById(id);
			if(component != null) {
				components.add(component);
			}
		});

		return components;
	}
}
