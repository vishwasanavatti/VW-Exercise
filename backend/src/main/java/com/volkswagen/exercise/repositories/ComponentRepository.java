package com.volkswagen.exercise.repositories;

import com.volkswagen.exercise.entities.Component;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComponentRepository extends JpaRepository<Component, Integer> {
}
