package com.volkswagen.exercise.services;

import com.volkswagen.exercise.controllers.ShopController;
import com.volkswagen.exercise.entities.Catalog;
import com.volkswagen.exercise.models.ModelCatalog;
import com.volkswagen.exercise.repositories.CatalogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link CatalogService}.
 */
@ExtendWith(MockitoExtension.class)
public class CatalogServiceTest {
	@InjectMocks
	CatalogService catalogService;

	@Mock
	CatalogRepository catalogRepository;

	@Test
	public void getAllCatalogs_returnCatalogs_onHappyPath() {
		Catalog catalog = new Catalog();
		catalog.setModel(1);
		catalog.setModelName("model");
		catalog.setComponents(new HashSet<>());

		ModelCatalog modelCatalog = new ModelCatalog(1, "model", Collections.emptySet());

		when(catalogRepository.findAll()).thenReturn(Collections.singletonList(catalog));

		List<ModelCatalog> catalogList = catalogService.getAllCatalogs();

		assertEquals(1, catalogList.size());
		assertEquals(modelCatalog.getModel(), catalogList.get(0).getModel());
		assertEquals(modelCatalog.getModelName(), catalogList.get(0).getModelName());
		assertEquals(modelCatalog.getComponents(), catalogList.get(0).getComponents());
	}

	@Test
	public void getCatalogByModelId_returnsCatalog_IfModelIdMatches() {
		Catalog catalog = new Catalog();
		catalog.setModel(1);
		catalog.setModelName("model");
		catalog.setComponents(new HashSet<>());

		ModelCatalog expected = new ModelCatalog(1, "model", Collections.emptySet());

		when(catalogRepository.findById(anyInt())).thenReturn(Optional.of(catalog));

		ModelCatalog modelCatalog = catalogService.getCatalogByModelId(1);

		assertEquals(expected.getModel(), modelCatalog.getModel());
		assertEquals(expected.getModelName(), modelCatalog.getModelName());
		assertEquals(expected.getComponents(), modelCatalog.getComponents());
	}

	@Test
	public void getCatalogByModelId_returnsNull_ifNoCatalogFoundForId() {
		when(catalogRepository.findById(1)).thenReturn(Optional.ofNullable(null));

		ModelCatalog catalog = catalogService.getCatalogByModelId(1);

		assertNull(catalog);
	}
}
