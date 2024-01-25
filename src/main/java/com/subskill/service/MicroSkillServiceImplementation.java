package com.subskill.service;

import com.subskill.dto.MicroSkillDto;
import com.subskill.exception.IllegalMicroSkillStateException;
import com.subskill.exception.MicroSkillNotFoundException;
import com.subskill.models.MicroSkill;
import com.subskill.repository.MicroSkillRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MicroSkillServiceImplementation  implements MicroSkillService{
    MicroSkillRepository microSkillRepository;




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
    public MicroSkillDto updateMicroskill(MicroSkillDto microSkillDto) {
        MicroSkill microSkill = microSkillRepository.findByName(microSkillDto.microSkillname()).orElseThrow(MicroSkillNotFoundException::new);
        microSkill.setName(microSkillDto.microSkillname());
        microSkill.setPhoto(microSkillDto.microSkillphoto());
        microSkill.setRating(microSkillDto.microSkillrating());
        microSkillRepository.save(microSkill);
        log.debug("MicroSkill {} has been updated", microSkillDto);
        return microSkillDto;
    }



    @Override
    public void deleteMicroSkill(Long id) {
        MicroSkill microSkill = microSkillRepository.findById(id).orElseThrow(MicroSkillNotFoundException::new);
        microSkillRepository.delete(microSkill);
        log.debug("Microskill with ID {} has been deleted", id);
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
