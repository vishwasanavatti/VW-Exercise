package com.volkswagen.exercise.services;

import com.volkswagen.exercise.models.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ComponentService {

	private static final Logger logger = Logger.getLogger(ComponentService.class.getName());
	private static List<Component> components = new ArrayList<>();
	private List<String> errors = new ArrayList<>();

	static {
		Component component1 = new Component(1, "component 1", 10.00, 15);
		Component component2 = new Component(2, "component 2", 12.00, 25);
		Component component3 = new Component(3, "component 3", 124.00, 59);
		Component component4 = new Component(4, "component 4", 100.00, 80);
		Component component5 = new Component(5, "component 5", 23.00, 112);

		components = new ArrayList<>(Arrays.asList(component1, component2, component3, component4, component5));
	}

	public List<Component> getAllComponents() {
		return components;
	}

	public Component getComponentById(int id) {
		return components.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
	}

	public void updateComponent(Component component) {
		Component existingComponent = getComponentById(component.getId());
		if (existingComponent != null) {
			existingComponent.setQuantity(existingComponent.getQuantity() - component.getQuantity());
		}
	}

	public void updateComponents(List<Component> components) {
		components.forEach(this::validateComponentQuantity);

		if(!errors.isEmpty()) {
			throw new UnsupportedOperationException(errors.toString());
		}

		components.forEach(this::updateComponent);
	}

	private void validateComponentQuantity(Component component) {
		Component existingComponent = getComponentById(component.getId());
		if (existingComponent == null) {
			logger.warning("component is not found for id : "+component.getId());
			errors.add("component "+component.getName()+" not found");
		} else {
			if (existingComponent.getQuantity() < component.getQuantity()) {
				errors.add("Order cannot be placed for component "+component.getName()+" due to insufficient quantity");
			}
		}
	}

}
