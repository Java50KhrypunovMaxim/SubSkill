package com.subskill.service;


import com.subskill.dto.EditMicroSkillDto;
import com.subskill.dto.MicroSkillDto;
import com.subskill.dto.PageMicroSkillDto;
import com.subskill.models.MicroSkill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MicroSkillService {

    MicroSkillDto addMicroskill(MicroSkillDto microSkillDto);

    MicroSkill updateMicroSkill(EditMicroSkillDto microSkillDto);

    void deleteMicroSkill(Long id);

    Page<PageMicroSkillDto> findMicroSkillByRatingWithPage(Pageable paging, double rating);

    Page<PageMicroSkillDto> findMicroSkillByNameWithPage(Pageable paging, String name);

    Page<PageMicroSkillDto> findMicroSkillByPage(Pageable paging);

    long getViewsCount(long id);
}
