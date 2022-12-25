package com.volkswagen.exercise.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Component {
	@Id
	@GeneratedValue
	private int id;

	private String name;

	private double price;

	private long quantity;

	@ManyToMany(mappedBy = "components")
	private Set<ModelCatalog> catalogs;

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

	public Set<ModelCatalog> getCatalogs() {
		return catalogs;
	}

	public void setCatalogs(Set<ModelCatalog> catalogs) {
		this.catalogs = catalogs;
	}

	public com.volkswagen.exercise.models.Component toModelComponent(Component component) {
		return new com.volkswagen.exercise.models.Component(component.id, component.name, component.price, component.quantity);
	}
}
