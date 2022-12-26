package com.volkswagen.exercise.models;

/**
 * Model created to transfer object between database and UI for {@link com.volkswagen.exercise.entities.Component}
 */
public class ModelComponent {

	private int id;

	private String name;

	private double price;

	private long quantity;

	public ModelComponent() {
	}

	public ModelComponent(int id, String name, double price, long quantity) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
}
