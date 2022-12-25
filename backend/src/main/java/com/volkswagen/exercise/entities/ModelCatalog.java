package com.volkswagen.exercise.entities;

import com.volkswagen.exercise.models.Catalog;
import jakarta.persistence.*;

import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class ModelCatalog {

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

	public Catalog toCatalog(ModelCatalog modelCatalog) {
		Set<com.volkswagen.exercise.models.Component> modelComponents = null;
		if(modelCatalog.getComponents() != null) {
			modelComponents = modelCatalog.getComponents().stream().map(component -> component.toModelComponent(component)).collect(Collectors.toSet());
		}

		return new Catalog(modelCatalog.getModel(), modelCatalog.getModelName(), modelComponents);
	}
}
