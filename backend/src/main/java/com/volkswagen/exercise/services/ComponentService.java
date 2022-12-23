package com.volkswagen.exercise.services;

import com.volkswagen.exercise.models.Catalog;
import com.volkswagen.exercise.models.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ComponentService {

	private static List<Component> components = new ArrayList<>();

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
}
