package com.subskill.service.impl;

import com.subskill.dto.EditMicroSkillDto;
import com.subskill.dto.MicroSkillDto;
import com.subskill.dto.PageMicroSkillDto;
import com.subskill.exception.IllegalMicroSkillStateException;
import com.subskill.exception.MicroSkillNotFoundException;
import com.subskill.exception.TechnologyNotFoundException;
import com.subskill.models.MicroSkill;
import com.subskill.models.Technology;
import com.subskill.repository.MicroSkillRepository;
import com.subskill.repository.TechnologyRepository;
import com.subskill.service.MicroSkillService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
@AllArgsConstructor
public class MicroSkillServiceImplementation implements MicroSkillService {
    private final MicroSkillRepository microSkillRepository;
    private final TechnologyRepository technologyRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public MicroSkillDto addMicroskill(MicroSkillDto microSkillDto) {
        if (microSkillRepository.existsByName(microSkillDto.name())) {
            throw new IllegalMicroSkillStateException();
        }
        Technology technology = technologyRepository.findById(microSkillDto.technologyId())
                .orElseThrow(TechnologyNotFoundException::new);

        MicroSkill newMicroSkill = MicroSkill.of(microSkillDto);
        newMicroSkill.setTechnology(technology);
        microSkillRepository.save(newMicroSkill);
        log.debug("MicroSkill card {} has been saved", microSkillDto);
        return microSkillDto;
    }

    @Override
    @Transactional
    public MicroSkill updateMicroSkill(EditMicroSkillDto microSkillDto) {
        MicroSkill existingMicroSkill = microSkillRepository.findByName(microSkillDto.name())
                .orElseThrow(MicroSkillNotFoundException::new);
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(microSkillDto, existingMicroSkill);
        return microSkillRepository.save(existingMicroSkill);
    }


    @Override
    @Transactional
    public void deleteMicroSkill(Long id) {
        MicroSkill microSkill = microSkillRepository.findById(id).orElseThrow(MicroSkillNotFoundException::new);
        microSkillRepository.delete(microSkill);
        log.debug("Microskill with ID {} has been deleted", id);
    }

    @Override
    public long getViewsCount(long id) {
        MicroSkill microSkill = microSkillRepository.findById(id).orElseThrow(MicroSkillNotFoundException::new);
        log.debug("All views {}", id);
        return microSkill.getViews();
    }

    @Override
    public Page<PageMicroSkillDto> findMicroSkillByRatingWithPage(Pageable paging, double rating) {
        Page<PageMicroSkillDto> pageMicroSkillDto = microSkillRepository.findByRating(rating, paging);
        log.debug("find MicroSkills description by page rating: {}", paging);
        return pageMicroSkillDto;
    }
    @Override
    public Page<PageMicroSkillDto> findMicroSkillByNameWithPage(Pageable paging, String name) {
        log.debug("find MicroSkills description by page name: {}", paging);
        return microSkillRepository.findByName(name, paging);
    }

    @Override
    public Page<PageMicroSkillDto> findMicroSkillByPage(Pageable paging) {
        Page<PageMicroSkillDto> microskillPage = microSkillRepository.findAllByPage(paging);

        log.debug("MicroSkills description by page: {}", paging);
        return microskillPage;
    }

}
