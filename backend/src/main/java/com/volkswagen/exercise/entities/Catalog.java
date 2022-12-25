package com.volkswagen.exercise.entities;

import com.volkswagen.exercise.models.ModelCatalog;
import com.volkswagen.exercise.models.ModelComponent;
import jakarta.persistence.*;

import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Catalog {

	@Id
	@GeneratedValue
	private int model;

	private String modelName;

	@ManyToMany
	@JoinTable(name="model_components",
			joinColumns = { @JoinColumn(name = "model_id") },
			inverseJoinColumns = { @JoinColumn(name = "component_id") })
	private Set<Component> components;

	public int getModel() {
		return model;
	}

	public void setModel(int model) {
		this.model = model;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public Set<Component> getComponents() {
		return components;
	}

	public void setComponents(Set<Component> components) {
		this.components = components;
	}

	public ModelCatalog toModelCatalog(Catalog catalog) {
		Set<ModelComponent> modelComponents = null;
		if(catalog.getComponents() != null) {
			modelComponents = catalog.getComponents().stream().map(component -> component.toModelComponent(component)).collect(Collectors.toSet());
		}

		return new ModelCatalog(catalog.getModel(), catalog.getModelName(), modelComponents);
	}
}
