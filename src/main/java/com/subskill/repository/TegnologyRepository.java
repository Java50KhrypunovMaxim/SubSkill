package com.subskill.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.subskill.models.MicroSkill;
import com.subskill.models.Technology;

@Repository
public interface TegnologyRepository extends JpaRepository<Technology,Long> {
    Optional<Technology> findById(Long id);
}
