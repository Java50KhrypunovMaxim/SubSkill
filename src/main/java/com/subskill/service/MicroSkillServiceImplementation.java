package com.subskill.service;


import com.subskill.dto.EditMicroSkillMapper;
import com.subskill.dto.MicroSkillDto;
import com.subskill.dto.ProductMicroSkillDto;
import com.subskill.exception.IllegalMicroSkillStateException;
import com.subskill.exception.MicroSkillNotFoundException;
import com.subskill.models.MicroSkill;
import com.subskill.models.Technology;
import com.subskill.repository.MicroSkillRepository;
import com.subskill.repository.TechnologyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class MicroSkillServiceImplementation implements MicroSkillService {
    private final MicroSkillRepository microSkillRepository;
    private final EditMicroSkillMapper editMicroSkillMapper;
    private final TechnologyRepository technologyRepository;

    @Override
    public MicroSkill addMicroskill(MicroSkillDto microSkillDto) {
        if (microSkillRepository.existsByName(microSkillDto.microSkillName())) {
            throw new IllegalMicroSkillStateException();
        }
        MicroSkill newMicroSkill = editMicroSkillMapper.microSkillDtoToMicroSkill(microSkillDto);
        microSkillRepository.save(newMicroSkill);
        log.debug("MicroSkill card {} has been saved", microSkillDto);
        return newMicroSkill;
    }

    @Override
    public ProductMicroSkillDto updateMicroskill(ProductMicroSkillDto productMicroSkillDto) {
        MicroSkill existingMicroSkill = microSkillRepository.findByName(productMicroSkillDto.microSkillName())
                .orElseThrow(MicroSkillNotFoundException::new);
        editMicroSkillMapper.updateMicroSkillFromDto(productMicroSkillDto, existingMicroSkill);
        MicroSkill updatedMicroSkill = microSkillRepository.save(existingMicroSkill);
        ProductMicroSkillDto updatedMicroSkillDto = editMicroSkillMapper.microSkillToDto(updatedMicroSkill);
        log.debug("MicroSkill {} has been updated", updatedMicroSkillDto);
        return updatedMicroSkillDto;
    }


    @Override
    public void deleteMicroSkill(Long id) {
        MicroSkill microSkill = microSkillRepository.findById(id).orElseThrow(MicroSkillNotFoundException::new);
        microSkillRepository.delete(microSkill);
        log.debug("Microskill with ID {} has been deleted", id);
    }

    public List<ProductMicroSkillDto> findAllMicroSkill() {
        List<MicroSkill> microSkills = microSkillRepository.findAll();
        List<ProductMicroSkillDto> productMicroSkillDtos = microSkills.stream()
                .map(editMicroSkillMapper::microSkillToDto)
                .collect(Collectors.toList());
        log.debug("All microskills: {}", productMicroSkillDtos);
        return productMicroSkillDtos;
    }

    @Override
    public List<Double> findByRanking() {
        List<MicroSkill> microSkills = microSkillRepository.findAll();
        List<Double> rating = microSkills.stream()
                .map(MicroSkill::getRating)
                .collect(Collectors.toList());
        log.debug("All rating {}", rating);
        return rating;
    }

    @Override
    public long getViewsCount(long id) {
        MicroSkill microSkill = microSkillRepository.findById(id).orElseThrow(MicroSkillNotFoundException::new);
        log.debug("All views {}", id);
        return microSkill.getViews();

    }

    @Override
    public Optional<MicroSkill> findById(Long id) {
        return microSkillRepository.findById(id)
                .map(skill -> {
                    skill.setViews(skill.getViews() + 1);
                    microSkillRepository.save(skill);
                    return skill;
                });
    }


    @Override
    public List<Technology> findByProfessionName(String name) {
        log.debug("MicroSkills technology by name : {}",name);
        return technologyRepository.findByProfessionName(name);
    }

    @Override
    public Page<MicroSkill> findMicroSkillByRatingWithPage(Pageable paging, String rating) {
        Page<MicroSkill> microskillPage;
        microskillPage = microSkillRepository.findByRating(rating, paging);

        log.debug("find MicroSkills description by page rating: {}", paging);
        return microskillPage;
    }
    @Override
    public Page<MicroSkill> findMicroSkillByNameWithPage(Pageable paging, String name) {
        log.debug("find MicroSkills description by page name: {}", paging);
        return microSkillRepository.findByName(name, paging);
    }

    @Override
    public Page<MicroSkill> findMicroSkillByPage(Pageable paging) {
        Page<MicroSkill> microskillPage;
        microskillPage = microSkillRepository.findAll(paging);

        log.debug("MicroSkills description by page: {}", paging);
        return microskillPage;
    }

}
