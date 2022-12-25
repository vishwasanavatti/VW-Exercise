package com.volkswagen.exercise.models;

import java.util.List;
import java.util.Set;

public class Catalog {

	private int model;

	private String modelName;

	private Set<Component> components;

	public Catalog(int model, String modelName, Set<Component> components) {
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

	public Set<Component> getComponents() {
		return components;
	}

	public void setComponents(Set<Component> components) {
		this.components = components;
	}
}
