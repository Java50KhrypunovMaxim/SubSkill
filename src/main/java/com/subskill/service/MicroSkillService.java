package com.subskill.service;

import com.subskill.dto.MicroSkillDto;
import com.subskill.models.Article;
import com.subskill.models.MicroSkill;

import java.util.List;

public interface MicroSkillService  {

        MicroSkillDto addMicroskill(MicroSkillDto microSkillDto);
        MicroSkillDto updateMicroskill(MicroSkillDto microSkillDto);
        void deleteMicroSkill(Long id);

        List<Double> findByRanking();
}
