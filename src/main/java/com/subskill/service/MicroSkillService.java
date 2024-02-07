package com.subskill.service;


import com.subskill.dto.MicroSkillDto;
import com.subskill.dto.ProductMicroSkillDto;
import com.subskill.models.MicroSkill;
import com.subskill.models.Technology;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface MicroSkillService  {

        MicroSkillDto addMicroskill(MicroSkillDto microSkillDto);
        ProductMicroSkillDto updateMicroskill(ProductMicroSkillDto editMicroSkillDto);
        void deleteMicroSkill(Long id);

        List<ProductMicroSkillDto> findAllMicroSkill();
        Page<MicroSkill> findMicroSkillByRatingWithPage(Pageable paging,String rating);
        Page<MicroSkill> findMicroSkillByNameWithPage(Pageable paging,String name);
        Page<MicroSkill> findMicroSkillByPage(Pageable paging);
        List<Double> findByRanking();
        long getViewsCount(long id);
        Optional<MicroSkill> findById(Long id);

        List<Technology> findByProfessionName(String name);
}
