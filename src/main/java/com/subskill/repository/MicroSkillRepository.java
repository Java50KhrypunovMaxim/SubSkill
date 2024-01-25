package com.subskill.repository;

import com.subskill.models.MicroSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MicroSkillRepository extends JpaRepository<MicroSkill,Long> {
    Optional<MicroSkill> findById(Long id);
    Optional<MicroSkill> findByMicroSkillName(String name);

    boolean existByMicroSkillName(String microSkillName);


}
