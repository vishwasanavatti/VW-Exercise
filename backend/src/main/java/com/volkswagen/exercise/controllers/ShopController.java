package com.volkswagen.exercise.controllers;

import com.volkswagen.exercise.models.Component;
import com.volkswagen.exercise.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/shop")
public class ShopController {

	@Autowired
	ShopService shopService;

	@RequestMapping("/stock/model/{modelId}")
	public List<Component> listComponents(@PathVariable int modelId) {
		try {
			return shopService.listComponentsForModelId(modelId);
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping("/order")
	public ResponseEntity<?> orderComponents(@RequestBody List<Component> components) {
		try {
			shopService.updateComponents(components);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (UnsupportedOperationException e) {
			return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
