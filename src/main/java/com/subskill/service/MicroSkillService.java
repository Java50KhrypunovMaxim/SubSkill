package com.subskill.service;


import com.subskill.dto.MicroSkillDto;
import com.subskill.dto.ProductMicroSkillDto;
import com.subskill.models.MicroSkill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MicroSkillService {

    MicroSkillDto addMicroskill(MicroSkillDto microSkillDto);

    ProductMicroSkillDto updateMicroskill(ProductMicroSkillDto editMicroSkillDto);

    void deleteMicroSkill(Long id);

    Page<MicroSkill> findMicroSkillByRatingWithPage(Pageable paging, String rating);

    Page<MicroSkill> findMicroSkillByNameWithPage(Pageable paging, String name);

    Page<MicroSkill> findMicroSkillByPage(Pageable paging);

    long getViewsCount(long id);
}
