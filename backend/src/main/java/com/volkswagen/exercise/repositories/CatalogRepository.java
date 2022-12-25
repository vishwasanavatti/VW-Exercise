package com.volkswagen.exercise.repositories;

import com.volkswagen.exercise.entities.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<Catalog, Integer> {
}
