package com.subskill.service;


import com.subskill.dto.EditMicroSkillDto;
import com.subskill.dto.MicroSkillDto;
import com.subskill.enums.Level;
import com.subskill.enums.Tags;
import com.subskill.models.MicroSkill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MicroSkillService {

    MicroSkillDto addMicroskill(MicroSkillDto microSkillDto);

    void updateMicroSkill(EditMicroSkillDto microSkillDto);
    
    void updatePriceMicroSkill(long microSkill_id, Double price);

    void deleteMicroSkill(Long microSkill_id);
    List<MicroSkill>  findLevelFromMicroSkill(Level level);
     List<MicroSkill> findMicroSkillByTag(Tags tags);

    List<MicroSkill> findTechnology(String name);
    Page<MicroSkill> findMicroSkillByRatingWithPage(Pageable paging, Double rating);

    Page<MicroSkill> findMicroSkillByNameWithPage(Pageable paging, String name);

    Page<MicroSkill> findMicroSkillByPage(Pageable paging);

    long getViewsCount(long microskillId);
    MicroSkill findMicroSkillPopularity(long microskillId);
    MicroSkill findMicroSkill(long microskillId);
    List<MicroSkill> getBestDealsByToday( );
}
