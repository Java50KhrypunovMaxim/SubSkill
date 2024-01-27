package com.subskill.service;

import com.subskill.dto.EditMicroSkillDto;
import com.subskill.dto.MicroSkillDto;
import com.subskill.models.MicroSkill;


import java.util.List;

public interface MicroSkillService  {

        MicroSkillDto addMicroskill(MicroSkillDto microSkillDto);
        EditMicroSkillDto updateMicroskill(EditMicroSkillDto editMicroSkillDto);
        void deleteMicroSkill(Long id);
        List<MicroSkill> findAllMicroSkill();
        List<Double> findByRanking();
        long getViewsCount(long id);
}
