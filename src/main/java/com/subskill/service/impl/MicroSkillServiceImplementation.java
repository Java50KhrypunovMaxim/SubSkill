package com.subskill.service.impl;


import com.subskill.dto.EditMicroSkillDto;
import com.subskill.dto.MicroSkillDto;
import com.subskill.enums.Level;
import com.subskill.enums.Tags;
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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
@AllArgsConstructor
public class MicroSkillServiceImplementation implements MicroSkillService {
    private final MicroSkillRepository microSkillRepository;
    private final TechnologyRepository technologyRepository;
    private final ModelMapper modelMapper;
    @Override
    @Transactional
    public MicroSkillDto addMicroSkill(MicroSkillDto microSkillDto) {
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
    public List<MicroSkillDto> findAllMicroSkills() {
        List<MicroSkill> allMicroSkill = microSkillRepository.findAll();
        return allMicroSkill.stream()
                .map(MicroSkill::build)
                .toList();
    }


    @Override
    @Transactional
    public void updateMicroSkill(EditMicroSkillDto editMicroSkillDto, Long id) {
        MicroSkill existingMicroSkill = microSkillRepository.findById(id)
                .orElseThrow(MicroSkillNotFoundException::new);
        existingMicroSkill.setLastUpdateTime(LocalDateTime.now());
        existingMicroSkill.updateMicroSkillFromDto(existingMicroSkill, editMicroSkillDto);
        microSkillRepository.save(existingMicroSkill);
        microSkillRepository.save(existingMicroSkill);
    }

    @Override
    @Transactional
    public void deleteMicroSkill(Long microSkillId) {
        MicroSkill microSkill = microSkillRepository.findById(microSkillId)
                .orElseThrow(MicroSkillNotFoundException::new);
        microSkillRepository.delete(microSkill);
        log.debug("Microskill with ID {} has been deleted", microSkillId);
    }

    @Transactional(readOnly = true)
    @Override
    public long getViewsCount(long microSkillId) {
        MicroSkill microSkill = microSkillRepository.findById(microSkillId)
                .orElseThrow(MicroSkillNotFoundException::new);
        log.debug("All views {}", microSkillId);
        return microSkill.getViews();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<MicroSkillDto> findMicroSkillByRatingWithPage(Pageable paging, Double rating) {
        Page<MicroSkill> microSkillPage = microSkillRepository.findByRating(rating, paging);

        List<MicroSkillDto> microSkillDtos = microSkillPage
                .getContent()
                .stream()
                .map(MicroSkill::build)
                .collect(Collectors.toList());

        log.debug("find MicroSkills description by page rating: {}", paging);
        return new PageImpl<>(microSkillDtos, paging, microSkillPage.getTotalElements());
    }


    @Transactional(readOnly = true)
    @Override
    public Page<MicroSkillDto> findMicroSkillByNameWithPage(Pageable paging, String name) {
        Page<MicroSkill> microSkillPage = microSkillRepository.findByName(name, paging);

        List<MicroSkillDto> microSkillDtos = microSkillPage
                .getContent()
                .stream()
                .map(MicroSkill::build)
                .collect(Collectors.toList());

        log.debug("find MicroSkills description by page rating: {}", paging);
        return new PageImpl<>(microSkillDtos, paging, microSkillPage.getTotalElements());
    }

    @Transactional(readOnly = true)
    @Override
    public Page<MicroSkillDto> findMicroSkillByPage(Pageable paging) {
        Page<MicroSkill> microSkillPage = microSkillRepository.findAll(paging);

        List<MicroSkillDto> microSkillDtos = microSkillPage
                .getContent()
                .stream()
                .map(MicroSkill::build)
                .collect(Collectors.toList());

        log.debug("find MicroSkills description by page rating: {}", paging);
        return new PageImpl<>(microSkillDtos, paging, microSkillPage.getTotalElements());
    }

    @Transactional(readOnly = true)
    @Override
    public MicroSkillDto findMicroSkillPopularity(long id) {
        Optional<MicroSkill> microSkillByPopularity = microSkillRepository.findById(id);
        if (microSkillByPopularity.isPresent()) {
            MicroSkill microSkill = microSkillByPopularity.get();
            microSkill.calculatePopularity();
            return microSkill.build();
        } else {
            throw new MicroSkillNotFoundException();
        }
    }

    @Override
    public Page<MicroSkillDto> freshMicroSkills(Pageable paging) {
        return getMicroSkillDtos(paging, Comparator.comparing(MicroSkill::getCreationDate));
    }

    @Override
    public Page<MicroSkillDto> findMostPopularMicroSkills(Pageable paging) {
        return getMicroSkillDtos(paging, Comparator.comparing(MicroSkill::getPopularity));
    }

    @Override
    public Page<MicroSkillDto> mostVisitedMicroSkills(Pageable paging) {
        return getMicroSkillDtos(paging, Comparator.comparing(MicroSkill::getViews));
    }

    private Page<MicroSkillDto> getMicroSkillDtos(Pageable paging, Comparator<MicroSkill> comparing) {
        Page<MicroSkill> microSkillsPageForPopular = microSkillRepository.findAll(paging);
        List<MicroSkillDto> listOfPopularMicroSkillDto = microSkillsPageForPopular.getContent()
                .stream()
                .filter(microSkill -> microSkill.getPopularity() != null)
                .sorted(comparing.reversed())
                .map(MicroSkill::build)
                .toList();
        return new PageImpl<>(listOfPopularMicroSkillDto, paging, microSkillsPageForPopular.getTotalElements());
    }


    @Transactional(readOnly = true)
    @Override
    public MicroSkillDto findMicroSkillById(long microSkillId) {
        log.debug("Get MicroSkill by id : {}", microSkillId);
        Optional<MicroSkill> microSkillOptional = microSkillRepository.findById(microSkillId);
        microSkillOptional.ifPresent(microSkill -> {
            Integer views = microSkill.getViews();
            views++;
            microSkill.setViews(views);
            microSkillRepository.save(microSkill);
        });
        return microSkillOptional.map(MicroSkill::build).orElseThrow(MicroSkillNotFoundException::new);
    }

    @Override
    @Transactional
    public void updatePriceMicroSkill(long microSkillId, Double price) {
        MicroSkill microSkill = microSkillRepository.findById(microSkillId)
                .orElseThrow(MicroSkillNotFoundException::new);
        microSkill.setPrice(BigDecimal.valueOf(price));
        microSkillRepository.save(microSkill);
        log.debug("Microskill {} has been changed price to {}", microSkillId, price);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<MicroSkillDto> findLevelFromMicroSkill(Level level, Pageable paging) {
        Page<MicroSkill> microSkillPage = microSkillRepository.findByLevel(level, paging);
        List<MicroSkillDto> microSkillDtos = microSkillPage
                .getContent()
                .stream()
                .map(MicroSkill::build)
                .collect(Collectors.toList());

        log.debug("find MicroSkills description by page rating: {}", paging);
        return new PageImpl<>(microSkillDtos, paging, microSkillPage.getTotalElements());
    }

    @Transactional(readOnly = true)
    @Override
    public Page<MicroSkillDto> findMicroSkillByTag(Tags tags, Pageable paging) {
        Page<MicroSkill> microSkillPage = microSkillRepository.findByTags(tags, paging);
        List<MicroSkillDto> microSkillDtos = microSkillPage
                .getContent()
                .stream()
                .map(MicroSkill::build)
                .collect(Collectors.toList());

        log.debug("find MicroSkills description by page rating: {}", paging);
        return new PageImpl<>(microSkillDtos, paging, microSkillPage.getTotalElements());
    }

    @Transactional(readOnly = true)
    @Override
    public Page<MicroSkillDto> findTechnology(String name, Pageable paging) {
        Page<MicroSkill> microSkillPage = microSkillRepository.findByTechnologyName(name, paging);
        List<MicroSkillDto> microSkillDtos = microSkillPage
                .getContent()
                .stream()
                .map(MicroSkill::build)
                .toList();

        log.debug("find MicroSkills description by page rating: {}", paging);
        return new PageImpl<>(microSkillDtos, paging, microSkillPage.getTotalElements());
    }

    @Transactional
    @Override
    public Page<MicroSkillDto> getBestDealsByToday(Pageable paging) {
        log.debug("Finding best deals by today's creation date");

        LocalDate twentyFourHoursAgo = LocalDate.now().minusDays(1);

        List<MicroSkill> microSkills = microSkillRepository.findByCreationDateAfter(twentyFourHoursAgo);
        for (MicroSkill microSkill : microSkills) {
            microSkill.calculatePopularity();
        }

        microSkills.sort(Comparator.comparing(MicroSkill::getPopularity).reversed());
        List<MicroSkillDto> microSkillDtoBestDayDeals = microSkills.stream()
                .map(MicroSkill::build)
                .collect(Collectors.toList());

        int pageSize = paging.getPageSize();
        int currentPage = paging.getPageNumber();
        int startItem = currentPage * pageSize;

        List<MicroSkillDto> pagedMicroSkills;
        if (microSkillDtoBestDayDeals.size() < startItem) {
            pagedMicroSkills = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, microSkillDtoBestDayDeals.size());
            pagedMicroSkills = microSkillDtoBestDayDeals.subList(startItem, toIndex);
        }

        return new PageImpl<>(pagedMicroSkills, paging, microSkillDtoBestDayDeals.size());
    }
}
