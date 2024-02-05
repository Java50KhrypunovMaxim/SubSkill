package com.subskill.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<String> getAllTechnology() {
        List<Technology> technology = technologyRepository.findAll();
        List<String> technologyNames = technology.stream()
                .map(Technology::getName)
                .collect(Collectors.toList());
        log.debug("All technology {}", technologyNames);
        return technologyNames;
    }

    @Override
    public Technology getByName(String name) {
        Technology technology = technologyRepository.findByName(name).
        		orElseThrow(TechnologyNotFoundException::new);
        return technology;
    }

    @Override
    public Technology getByID(long ID) {
        Technology technology = technologyRepository.findById(ID)
                .orElseThrow(TechnologyNotFoundException::new);
        return technology;
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
