package com.subskill.service;

import com.subskill.dto.EditMicroSkillDto;
import com.subskill.dto.EditMicroSkillMapper;
import com.subskill.dto.MicroSkillDto;
import com.subskill.exception.IllegalMicroSkillStateException;
import com.subskill.exception.MicroSkillNotFoundException;
import com.subskill.models.MicroSkill;
import com.subskill.repository.MicroSkillRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MicroSkillServiceImplementation  implements MicroSkillService{
    private final MicroSkillRepository microSkillRepository;
    private final EditMicroSkillMapper editMicroSkillMapper;

    @Autowired
    public MicroSkillServiceImplementation(MicroSkillRepository microSkillRepository, EditMicroSkillMapper editMicroSkillMapper) {
        this.microSkillRepository = microSkillRepository;
        this.editMicroSkillMapper = editMicroSkillMapper;
    }
    @Override
    public MicroSkillDto addMicroskill(MicroSkillDto microSkillDto) {
        if (microSkillRepository.existsByName(microSkillDto.microSkillname())) {
            throw new IllegalMicroSkillStateException();
        }
      MicroSkill newMicroSkill = MicroSkill.of(microSkillDto);
        microSkillRepository.save(newMicroSkill);
        log.debug("MicroSkill card {} has been saved", microSkillDto);
        return microSkillDto;
    }

    @Override
    public EditMicroSkillDto updateMicroskill(EditMicroSkillDto editMicroSkillDto) {
        MicroSkill existingMicroSkill = microSkillRepository.findByName(editMicroSkillDto.microSkillname())
                .orElseThrow(MicroSkillNotFoundException::new);
        MicroSkill updatedMicroSkill = editMicroSkillMapper.microSkillToEditDto(existingMicroSkill, editMicroSkillDto);
        microSkillRepository.save(updatedMicroSkill);
        EditMicroSkillDto updatedMicroSkillDto = editMicroSkillMapper.microSkillToDto(updatedMicroSkill);
        log.debug("MicroSkill {} has been updated", editMicroSkillDto);
        return updatedMicroSkillDto;
    }



    @Override
    public void deleteMicroSkill(Long id) {
        MicroSkill microSkill = microSkillRepository.findById(id).orElseThrow(MicroSkillNotFoundException::new);
        microSkillRepository.delete(microSkill);
        log.debug("Microskill with ID {} has been deleted", id);
    }

    @Override
    public List<MicroSkill> findAllMicroSkill() {
        return microSkillRepository.findAll();
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
        return microSkill.getViews();
    }


}
