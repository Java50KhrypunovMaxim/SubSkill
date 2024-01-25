package com.subskill.service;

import com.subskill.dto.MicroSkillDto;


import java.util.List;

public interface MicroSkillService  {

        MicroSkillDto addMicroskill(MicroSkillDto microSkillDto);
        MicroSkillDto updateMicroskill(MicroSkillDto microSkillDto);
        void deleteMicroSkill(Long id);

        List<Double> findByRanking();
        long getViewsCount(long id);
}
