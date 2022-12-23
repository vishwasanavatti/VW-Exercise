package com.volkswagen.exercise.controllers;

import com.volkswagen.exercise.models.Component;
import com.volkswagen.exercise.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {

	@Autowired
	ShopService shopService;

	@RequestMapping("/stock/model/{modelId}")
	public List<Component> listComponents(@PathVariable int modelId) {
		List<Component> components = shopService.listComponentsForModelId(modelId);
		if (components == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		return components;
	}
}
