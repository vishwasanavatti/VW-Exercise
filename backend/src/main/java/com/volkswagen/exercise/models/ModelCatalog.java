package com.volkswagen.exercise.models;

import java.util.Set;

/**
 * Model created to transfer object between database and UI for {@link com.volkswagen.exercise.entities.Catalog}
 */
public class ModelCatalog {

	private int model;

	private String modelName;

	private Set<ModelComponent> components;

	public ModelCatalog(int model, String modelName, Set<ModelComponent> components) {
		this.model = model;
		this.modelName = modelName;
		this.components = components;
	}

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

	public Set<ModelComponent> getComponents() {
		return components;
	}

	public void setComponents(Set<ModelComponent> components) {
		this.components = components;
	}
}
