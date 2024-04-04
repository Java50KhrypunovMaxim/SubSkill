package com.subskill.service;


import com.subskill.dto.EditMicroSkillDto;
import com.subskill.dto.MicroSkillDto;
import com.subskill.enums.Level;
import com.subskill.enums.Tags;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MicroSkillService {

    MicroSkillDto addMicroSkill(MicroSkillDto microSkillDto);


    List<MicroSkillDto> findAllMicroSkills();

    void updateMicroSkill(EditMicroSkillDto editMicroSkillDto, Long id);

    void updatePriceMicroSkill(long microSkill_id, Double price);

    void deleteMicroSkill(Long microSkill_id);

    Page<MicroSkillDto> findLevelFromMicroSkill(Level level, Pageable paging);

    Page<MicroSkillDto> findMicroSkillByTag(Tags tags, Pageable paging);

    Page<MicroSkillDto> findTechnology(String name, Pageable paging);

    Page<MicroSkillDto> findMicroSkillByRatingWithPage(Pageable paging, Double rating);

    Page<MicroSkillDto> findMicroSkillByNameWithPage(Pageable paging, String name);

    Page<MicroSkillDto> findMicroSkillByPage(Pageable paging);

    long getViewsCount(long microskillId);

    MicroSkillDto findMicroSkillPopularity(long microskillId);

    Page<MicroSkillDto> freshMicroSkills(Pageable paging);

    Page<MicroSkillDto> mostVisitedMicroSkills(Pageable paging);

    Page<MicroSkillDto> findMostPopularMicroSkills(Pageable paging);

    MicroSkillDto findMicroSkillById(long microskillId);

    Page<MicroSkillDto> getBestDealsByToday(Pageable paging);
}
