package com.subskill.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.subskill.models.Technology;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long> {
    Optional<Technology> findById(Long id);
    // TODO: Add a method to find a technology by name and findAll
}
