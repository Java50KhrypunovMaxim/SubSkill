package com.subskill.repository;

import com.subskill.models.MicroSkill;
import com.subskill.models.SaveMicroskill;
import com.subskill.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SaveMicroskillRepository extends JpaRepository<SaveMicroskill, Long>  {
	Optional <SaveMicroskill> findById(Long id);
    boolean existsByUserAndMicroSkills(User user, MicroSkill microSkill);
    Optional <SaveMicroskill> findByUserAndMicroSkills(User user, MicroSkill microSkill);
    List<SaveMicroskill> findByUser(User user);
}
