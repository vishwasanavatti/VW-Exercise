package com.volkswagen.exercise.repositories;

import com.volkswagen.exercise.entities.Component;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Provides a Repository for {@link Component} DB. The Spring data JPA provides built in functions to access DB.
 */
public interface ComponentRepository extends JpaRepository<Component, Integer> {
}
