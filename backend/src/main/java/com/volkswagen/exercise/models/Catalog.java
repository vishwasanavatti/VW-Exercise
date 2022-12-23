package com.volkswagen.exercise.models;

import java.util.List;

public class Catalog {

	private int model;

	private String modelName;

	private List<Integer> componentIds;

	public Catalog(int model, String modelName, List<Integer> componentIds) {
		this.model = model;
		this.modelName = modelName;
		this.componentIds = componentIds;
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

	public List<Integer> getComponentIds() {
		return componentIds;
	}

	public void setComponentIds(List<Integer> componentIds) {
		this.componentIds = componentIds;
	}
}
