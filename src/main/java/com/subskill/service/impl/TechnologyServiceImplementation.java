package com.subskill.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.subskill.service.TechnologyService;
import org.springframework.stereotype.Service;

import com.subskill.dto.TechnologyDto;
import com.subskill.exception.TechnologyNotFoundException;
import com.subskill.models.Technology;
import com.subskill.repository.TechnologyRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class TechnologyServiceImplementation implements TechnologyService {

    private final TechnologyRepository technologyRepository;

    @Transactional(readOnly = true)
    @Override
    public List<TechnologyDto> getAllTechnology() {
        log.debug("All technologies");
        return technologyRepository.findAll().stream().map(Technology::build)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public Technology getByName(String name) {
        return technologyRepository.findByName(name).
                orElseThrow(TechnologyNotFoundException::new);
    }

    @Transactional(readOnly = true)
    @Override
    public Technology getByID(long technologyId) {
        return technologyRepository.findById(technologyId)
                .orElseThrow(TechnologyNotFoundException::new);
    }

    @Transactional(readOnly = true)
    @Override
    public List<TechnologyDto> getByProfessionName(String name) {
        List<Technology> technology = technologyRepository.findByProfessionName(name);
        log.debug("All technology {} for profession", name);
        return technology.stream().map(Technology::build)
                .collect(Collectors.toList());
    }


}
