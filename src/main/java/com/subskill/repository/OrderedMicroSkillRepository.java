package com.subskill.repository;

import com.subskill.models.OrderedMicroskill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedMicroSkillRepository  extends JpaRepository<OrderedMicroskill, Long> {
}
