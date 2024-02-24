package com.subskill.repository;

import com.subskill.dto.MicroSkillDto;
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

    Optional<MicroSkillDto> findByLevel(Level level);

    Optional<MicroSkillDto> findByTags(Tags tags);

    Page<MicroSkill> findAll(Pageable pageable);

    Page<MicroSkill> findByName(String name, Pageable pageable);

    Page<MicroSkill> findByRating(Double rating, Pageable pageable);

    List<MicroSkill> findByCreationDateAfter(LocalDate date);
    List<MicroSkill> findByViews(Long views);

    boolean existsByName(String microSkillName);
}
