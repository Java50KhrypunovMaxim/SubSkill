package com.subskill.repository;

import com.subskill.enums.Level;
import com.subskill.enums.Tags;
import com.subskill.models.MicroSkill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MicroSkillRepository extends JpaRepository<MicroSkill, Long> {
    Optional<MicroSkill> findById(Long id);

    Page<MicroSkill> findByLevel(Level level, Pageable pageable);

    Page<MicroSkill> findByTags(Tags tags, Pageable pageable);

    Page<MicroSkill> findByTechnologyName(String name, Pageable pageable);

    Page<MicroSkill> findAll(Pageable pageable);

    List<MicroSkill> findAll();

    Page<MicroSkill> findByName(String name, Pageable pageable);

    Page<MicroSkill> findByRating(Double rating, Pageable pageable);

    List<MicroSkill> findByCreationDateAfter(LocalDate date);

    List<MicroSkill> findByViews(Long views);

    boolean existsByName(String microSkillName);
}
