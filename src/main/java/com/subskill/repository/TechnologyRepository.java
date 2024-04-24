package com.subskill.repository;

import com.subskill.models.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long> {
    Optional<Technology> findById(Long id);

    List<Technology> findAll();

    Optional<Technology> findByName(String name);

    List <Technology> findByProfessionName(String name);
}
