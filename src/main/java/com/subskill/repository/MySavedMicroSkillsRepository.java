package com.subskill.repository;

import com.subskill.models.MySavedMicroSkills;
import com.subskill.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MySavedMicroSkillsRepository extends JpaRepository<MySavedMicroSkills, Long> {
    List<MySavedMicroSkills> findByUser(User user);
}
