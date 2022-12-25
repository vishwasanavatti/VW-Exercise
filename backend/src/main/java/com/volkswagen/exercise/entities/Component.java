package com.volkswagen.exercise.entities;

import com.volkswagen.exercise.models.ModelComponent;
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
	private Set<Catalog> catalogs;

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

	public Set<Catalog> getCatalogs() {
		return catalogs;
	}

	public void setCatalogs(Set<Catalog> catalogs) {
		this.catalogs = catalogs;
	}

	public ModelComponent toModelComponent(Component component) {
		return new ModelComponent(component.id, component.name, component.price, component.quantity);
	}
}
