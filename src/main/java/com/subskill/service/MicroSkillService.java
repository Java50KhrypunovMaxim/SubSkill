package com.subskill.service;


import com.subskill.dto.MicroSkillDto;
import com.subskill.dto.ProductMicroSkillDto;
import com.subskill.models.MicroSkill;


import java.util.List;

public interface MicroSkillService  {

        MicroSkillDto addMicroskill(MicroSkillDto microSkillDto);
        ProductMicroSkillDto updateMicroskill(ProductMicroSkillDto editMicroSkillDto);
        void deleteMicroSkill(Long id);
        List<MicroSkill> findAllMicroSkill();
        List<Double> findByRanking();
        long getViewsCount(long id);
}
