package com.volkswagen.exercise.controllers;

import com.volkswagen.exercise.models.ModelComponent;
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

/**
 * This is the main controller API which provides two endpoints to get components and place order
 */
@RestController
@RequestMapping("/shop")
public class ShopController {

	@Autowired
	ShopService shopService;

	/**
	 * The endpoint to get the compatible components list for the given model id
	 * returns Not_Found status if no component found for the given model Id
	 * @param modelId - model id of the car
	 * @return - list of components compatible for the model
	 */
	@RequestMapping("/stock/model/{modelId}")
	public List<ModelComponent> listComponents(@PathVariable int modelId) {
		try {
			return shopService.listComponentsForModelId(modelId);
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * The endpoint to update the components based on the placed order.
	 * returns BAD_REQUEST status with errors string if any errors found during update
	 * else returns OK status. (returns OK and not CREATED because it is updating existing component)
	 * @param components - components input to be updated
	 * @return - response entity with errors string
	 */
	@RequestMapping("/order")
	public ResponseEntity<?> orderComponents(@RequestBody List<ModelComponent> components) {
		try {
			List<String> errors = shopService.updateComponents(components);
			if (!errors.isEmpty()) {
				return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
			}
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
