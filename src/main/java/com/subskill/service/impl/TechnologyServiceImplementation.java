package com.subskill.service.impl;

import java.util.List;
import java.util.Optional;

import com.subskill.service.TechnologyService;
import org.springframework.stereotype.Service;

import com.subskill.exception.TechnologyNotFoundException;
import com.subskill.models.Technology;
import com.subskill.repository.TechnologyRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class TechnologyServiceImplementation implements TechnologyService {

    private final TechnologyRepository technologyRepository;

    @Override
    public List<Technology> getAllTechnology() {
        log.debug("All technologies");
        return technologyRepository.findAll();
    }

    @Override
    public Technology getByName(String name) {
        return technologyRepository.findByName(name).
        		orElseThrow(TechnologyNotFoundException::new);
    }

    @Override
    public Technology getByID(long ID) {
        return technologyRepository.findById(ID)
                .orElseThrow(TechnologyNotFoundException::new);
    }

    @Override
    public List<Technology> getByProfessionName(String name) {
        List<Technology> technology = technologyRepository.findByProfessionName(name);
        log.debug("All technology {} for profession", name);
        return Optional.of(technology)
                .filter(list -> !list.isEmpty())
                .orElseThrow(TechnologyNotFoundException::new);
    }

}
