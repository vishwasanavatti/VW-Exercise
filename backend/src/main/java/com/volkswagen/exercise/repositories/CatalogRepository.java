package com.volkswagen.exercise.repositories;

import com.volkswagen.exercise.entities.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Provides a Repository for {@link Catalog} DB. The Spring data JPA provides built in functions to access DB.
 */
public interface CatalogRepository extends JpaRepository<Catalog, Integer> {
}
