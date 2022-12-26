package com.volkswagen.exercise.services;

import com.volkswagen.exercise.entities.Component;
import com.volkswagen.exercise.models.ModelComponent;
import com.volkswagen.exercise.repositories.ComponentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link ComponentService}.
 */
@ExtendWith(MockitoExtension.class)
public class ComponentServiceTest {

	@InjectMocks
	ComponentService componentService;

	@Mock
	ComponentRepository componentRepository;

	@Test
	public void getAllComponents_returnComponents_onHappyPath() {
		Component component = createComponent();

		when(componentRepository.findAll()).thenReturn(Collections.singletonList(component));

		List<ModelComponent> components = componentService.getAllComponents();

		assertEquals(1, components.size());
		assertEquals(component.getId(), components.get(0).getId());
		assertEquals(component.getName(), components.get(0).getName());
		assertEquals(component.getPrice(), components.get(0).getPrice());
		assertEquals(component.getQuantity(), components.get(0).getQuantity());
	}

	@Test
	public void getComponentById_returnsComponent_IfModelIdMatches() {
		Component component = createComponent();

		ModelComponent expected = new ModelComponent(1, "component", 121, 11);

		when(componentRepository.findById(anyInt())).thenReturn(Optional.of(component));

		ModelComponent modelComponent = componentService.getComponentById(1);

		assertEquals(expected.getId(), modelComponent.getId());
		assertEquals(expected.getName(), modelComponent.getName());
		assertEquals(expected.getQuantity(), modelComponent.getQuantity());
		assertEquals(expected.getPrice(), modelComponent.getPrice());
	}

	@Test
	public void getComponentById_returnsNull_ifNoComponentFoundForId() {
		when(componentRepository.findById(anyInt())).thenReturn(Optional.ofNullable(null));

		ModelComponent component = componentService.getComponentById(1);

		assertNull(component);
	}

	@Test
	public void updateComponents_returnEmptyErrorList_onHappyPath() {
		List<ModelComponent> modelComponents = Collections.singletonList(createModelComponent());

		when(componentRepository.findById(anyInt())).thenReturn(Optional.of(createComponent()));

		List<String> errors = componentService.updateComponents(modelComponents);

		assertTrue(errors.isEmpty());
	}

	@Test
	public void updateComponents_returnErrors_ifComponentNotFound() {
		List<ModelComponent> modelComponents = Collections.singletonList(createModelComponent());

		when(componentRepository.findById(1)).thenReturn(Optional.ofNullable(null));

		List<String> errors = componentService.updateComponents(modelComponents);

		assertEquals(1, errors.size());
		assertEquals("component "+modelComponents.get(0).getName()+" not found", errors.get(0));
	}

	@Test
	public void updateComponents_returnErrors_ifComponentHasInsufficientQuantity() {
		List<ModelComponent> modelComponents = Collections.singletonList(createModelComponent());
		modelComponents.get(0).setQuantity(500);
		when(componentRepository.findById(anyInt())).thenReturn(Optional.of(createComponent()));

		List<String> errors = componentService.updateComponents(modelComponents);

		assertEquals(1, errors.size());
		assertEquals("Order cannot be placed for component "+modelComponents.get(0).getName()+" due to insufficient quantity", errors.get(0));
	}

	private Component createComponent() {
		Component component = new Component();
		component.setId(1);
		component.setName("component");
		component.setPrice(121);
		component.setQuantity(11);

		return component;
	}

	private ModelComponent createModelComponent() {
		return new ModelComponent(1, "model", 121, 11);
	}
}
