package com.subskill.repository;

import com.subskill.dto.EditMicroSkillDto;
import com.subskill.models.MicroSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface MicroSkillRepository extends JpaRepository<MicroSkill,Long> {
    Optional<MicroSkill> findById(Long id);

    Optional<MicroSkill> findByName(String name);
    Optional<EditMicroSkillDto> findByEditMicroSkillName(String name);
    List<MicroSkill> findByViews(Long views);

    boolean existsByName(String microSkillName);
}
