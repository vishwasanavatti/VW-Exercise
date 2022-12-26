package com.volkswagen.exercise.services;

import com.volkswagen.exercise.models.ModelCatalog;
import com.volkswagen.exercise.models.ModelComponent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link ShopService}.
 */
@ExtendWith(MockitoExtension.class)
public class ShopServiceTest {

	@InjectMocks
	private ShopService shopService;

	@Mock
	CatalogService catalogService;

	@Mock
	ComponentService componentService;

	@Test
	public void listComponentsForModelId_returnComponents_OnHappyPath() {
		ModelComponent component = new ModelComponent(1, "comp", 10 ,10);
		ModelCatalog modelCatalog = new ModelCatalog(1, "model", Collections.singleton(component));

		when(catalogService.getCatalogByModelId(1)).thenReturn(modelCatalog);

		List<ModelComponent> modelComponents = shopService.listComponentsForModelId(1);
		assertEquals(modelComponents, Collections.singletonList(component));
	}

	@Test
	public void listComponentsForModelId_throwsException_ifModelNotFound() {
		when(catalogService.getCatalogByModelId(anyInt())).thenReturn(null);

		assertThrows(NoSuchElementException.class, () -> shopService.listComponentsForModelId(1));
	}

	@Test
	public void updateComponents_returnsEmptyString_IfUpdateSuccessful() {
		when(componentService.updateComponents(anyList())).thenReturn(new ArrayList<>());

		assertEquals(shopService.updateComponents(anyList()), new ArrayList<>());
	}
}
