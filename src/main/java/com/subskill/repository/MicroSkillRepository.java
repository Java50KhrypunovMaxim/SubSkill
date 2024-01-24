package com.subskill.repository;

import com.subskill.models.MicroSkill;
import com.subskill.models.Technology;
import com.subskill.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MicroSkillRepository extends JpaRepository<MicroSkill,Long> {
    Optional<MicroSkill> findById(Long id);

    Optional<MicroSkill> findByTechnology(Technology technology);
}
