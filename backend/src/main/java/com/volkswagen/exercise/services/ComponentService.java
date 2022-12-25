package com.volkswagen.exercise.services;

import com.volkswagen.exercise.entities.Component;
import com.volkswagen.exercise.models.ModelComponent;
import com.volkswagen.exercise.repositories.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class ComponentService {

	@Autowired
	private ComponentRepository componentRepository;

	private static final Logger logger = Logger.getLogger(ComponentService.class.getName());
	private List<String> errors = new ArrayList<>();

	// static data was created initially to create backend skeleton and then data moved to database
//	private static List<Component> components = new ArrayList<>();

//	static {
//		Component component1 = new Component(1, "component 1", 10.00, 15);
//		Component component2 = new Component(2, "component 2", 12.00, 25);
//		Component component3 = new Component(3, "component 3", 124.00, 59);
//		Component component4 = new Component(4, "component 4", 100.00, 80);
//		Component component5 = new Component(5, "component 5", 23.00, 112);
//
//		components = new ArrayList<>(Arrays.asList(component1, component2, component3, component4, component5));
//	}

	public List<ModelComponent> getAllComponents() {
		List<Component> components = componentRepository.findAll();

		return components.stream().map(c -> c.toModelComponent(c)).collect(Collectors.toList());
	}

	public ModelComponent getComponentById(int id) {
		Component component = componentRepository.findById(id).orElse(null);
		if(component == null) return null;
		return component.toModelComponent(component);
	}

	public void updateComponent(ModelComponent component) {
		ModelComponent existingComponent = getComponentById(component.getId());
		if (existingComponent == null) {
			logger.warning("Component with id : "+component.getId()+" could not updated");
			return;
		}

		existingComponent.setQuantity(existingComponent.getQuantity() - component.getQuantity());

		Component saveComponent = new Component();
		saveComponent.setId(existingComponent.getId());
		saveComponent.setName(existingComponent.getName());
		saveComponent.setPrice(existingComponent.getPrice());
		saveComponent.setQuantity(existingComponent.getQuantity());

		componentRepository.save(saveComponent);
	}

	public List<String> updateComponents(List<ModelComponent> components) {
		errors = new ArrayList<>();
		components.forEach(this::validateComponentQuantity);

		if (errors.isEmpty()) {
			components.forEach(this::updateComponent);
		}

		return errors;
	}

	private void validateComponentQuantity(ModelComponent component) {
		ModelComponent existingComponent = getComponentById(component.getId());
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
