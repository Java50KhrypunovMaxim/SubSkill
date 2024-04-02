package com.subskill.service.impl;

import com.subskill.dto.ProfessionDto;
import com.subskill.models.Profession;
import com.subskill.repository.ProfessionRepository;
import com.subskill.service.ProfessionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ProfessionServiceImpl implements ProfessionService {

    private final ProfessionRepository professionRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ProfessionDto> findAllProfession() {
        List<Profession> professions = professionRepository.findAll();
        return professions.stream()
                .map(Profession::build)
                .toList();
    }
}
