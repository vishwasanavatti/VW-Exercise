package com.volkswagen.exercise.repositories;

import com.volkswagen.exercise.entities.ModelCatalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<ModelCatalog, Integer> {
}
