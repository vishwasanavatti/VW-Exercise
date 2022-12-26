package com.volkswagen.exercise.entities;

import com.volkswagen.exercise.models.ModelComponent;
import jakarta.persistence.*;

import java.util.Set;

/**
 * Entity class created for the DB persistence of Component.
 * Has {@link Component#id}, {@link Component#name}, {@link Component#price} and {@link Component#quantity} attributes
 * and contains many to many relation with {@link Catalog}. Each component can be compatible with multiple
 * {@link Catalog}  (model) and different model can have same component which is compatible so we have many to many relation.
 */
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
