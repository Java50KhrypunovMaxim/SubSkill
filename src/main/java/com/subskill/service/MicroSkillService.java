package com.subskill.service;


import com.subskill.dto.MicroSkillDto;
import com.subskill.dto.PageMicroSkillDto;
import com.subskill.dto.ProductMicroSkillDto;
import org.springframework.data.domain.PageRequest;


import java.util.List;

public interface MicroSkillService  {

        MicroSkillDto addMicroskill(MicroSkillDto microSkillDto);
        ProductMicroSkillDto updateMicroskill(ProductMicroSkillDto editMicroSkillDto);
        void deleteMicroSkill(Long id);
        List<ProductMicroSkillDto> findAllMicroSkill();
        List<Double> findByRanking();
        ProductMicroSkillDto getViewsCount(Long views);
        PageMicroSkillDto findAllPage(PageRequest pageRequest);
}
