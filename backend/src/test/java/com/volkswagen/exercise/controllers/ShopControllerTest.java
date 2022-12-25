package com.volkswagen.exercise.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.volkswagen.exercise.models.ModelComponent;
import com.volkswagen.exercise.services.ShopService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShopController.class)
public class ShopControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ShopService service;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void listComponents_returnsNotFound_ifNoModelExistForModelId() throws Exception {
		when(service.listComponentsForModelId(anyInt())).thenThrow(new NoSuchElementException());

		mvc.perform(get("/shop/stock/model/{modelId}", 1))
				.andExpect(status().isNotFound());
	}

	@Test
	public void listComponents_returnsComponents_ifModelExistForModelId() throws Exception {

		ModelComponent component = new ModelComponent(1, "model", 12, 100);
		List<ModelComponent> modelComponents = Collections.singletonList(component);
		when(service.listComponentsForModelId(anyInt())).thenReturn(modelComponents);

		mvc.perform(get("/shop/stock/model/{modelId}", 1))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].name", is(component.getName())));
	}

	@Test
	public void orderComponents_returnsOk_ifOrderPlacedSuccessfully() throws Exception {
		ModelComponent component = new ModelComponent(1, "model", 12, 100);
		List<ModelComponent> modelComponents = Collections.singletonList(component);

		when(service.updateComponents(modelComponents)).thenReturn(new ArrayList<>());

		mvc.perform(post("/shop/order").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(modelComponents)))
				.andExpect(status().isOk());
	}

	@Test
	public void orderComponents_returnsBadRequest_ifOrderPlacedHasErrors() throws Exception {
		ModelComponent component = new ModelComponent(1, "model", 12, 100);
		List<ModelComponent> modelComponents = Collections.singletonList(component);

		when(service.updateComponents(anyList())).thenReturn(Collections.singletonList("Error"));

		mvc.perform(post("/shop/order").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(modelComponents)))
				.andExpect(status().isBadRequest());
	}
}
