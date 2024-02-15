package com.subskill.repository;

import com.subskill.models.MicroSkill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MicroSkillRepository extends JpaRepository<MicroSkill,Long> {
    Optional<MicroSkill> findById(Long id);
    Page<MicroSkill> findAll(Pageable pageable);
    Page<MicroSkill> findByName(String name, Pageable pageable);
    Page<MicroSkill> findByRating(String rating, Pageable pageable);
    Optional<MicroSkill> findByName(String name);
    List<MicroSkill> findByViews(Long views);

    boolean existsByName(String microSkillName);
}
