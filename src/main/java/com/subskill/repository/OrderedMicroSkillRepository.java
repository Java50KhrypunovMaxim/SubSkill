package com.subskill.repository;

import com.subskill.models.OrderedMicroskill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderedMicroSkillRepository extends JpaRepository<OrderedMicroskill, Long> {
    @Query("SELECT om.user.id FROM OrderedMicroskill om WHERE om.orderedMicroSkillId  = :orderedMicroskillId")
    Long findUserIdByOrderedMicroskillId(@Param("orderedMicroskillId") Long orderedMicroskillId);


}