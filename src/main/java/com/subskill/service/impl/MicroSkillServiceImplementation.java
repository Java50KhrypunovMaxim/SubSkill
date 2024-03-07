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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@AllArgsConstructor
public class MicroSkillServiceImplementation implements MicroSkillService {
    private final MicroSkillRepository microSkillRepository;
    private final TechnologyRepository technologyRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    @CachePut(value = "microSkill", key = "#microSkillDto.name()")
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
    public List<MicroSkillDto> findAllMicroSkills() {
       List<MicroSkill>  allMicroSkill = microSkillRepository.findAll();

        List<MicroSkillDto> allMicroSkillDto = new ArrayList<>();

        for (MicroSkill microSkill : allMicroSkill) {
            allMicroSkillDto.add(modelMapper.map(microSkill, MicroSkillDto.class));
        }

        return allMicroSkillDto;
    }

    @Override
    @Transactional
    @CachePut(value = "microSkill", key = "#microSkillDto.name()", cacheManager = "objectCacheManager")
    public void updateMicroSkill(EditMicroSkillDto microSkillDto) {
        MicroSkill existingMicroSkill = microSkillRepository.findById(microSkillDto.id())
                .orElseThrow(MicroSkillNotFoundException::new);
        existingMicroSkill.setLastUpdateTime(LocalDateTime.now());
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(microSkillDto, existingMicroSkill);
        microSkillRepository.save(existingMicroSkill);
    }

    @Override
    @Transactional
    @CacheEvict(value = "microSkill", key = "#microSkillId", cacheManager = "objectCacheManager")
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
    @Cacheable(value = "microSkills", key = "#rating")
    public Page<MicroSkill> findMicroSkillByRatingWithPage(Pageable paging, Double rating) {
        Page<MicroSkill> microskillPage = microSkillRepository.findByRating(rating, paging);
        log.debug("find MicroSkills description by page rating: {}", paging);
        return microskillPage;
    }

    @Transactional(readOnly = true)
    @Override
    @Cacheable(value = "microSkills", key = "#name")
    public Page<MicroSkill> findMicroSkillByNameWithPage(Pageable paging, String name) {
        log.debug("find MicroSkills description by page name: {}", paging);
        return microSkillRepository.findByName(name, paging);
    }

    @Transactional(readOnly = true)
    @Override
    @Cacheable(value = "microSkills")
    public Page<MicroSkill> findMicroSkillByPage(Pageable paging) {
        Page<MicroSkill> microskillPage;
        microskillPage = microSkillRepository.findAll(paging);

        log.debug("MicroSkills description by page: {}", paging);
        return microskillPage;
    }

    @Transactional(readOnly = true)
    @Override
    @Cacheable(value = "microSkill", key = "#id", cacheManager = "objectCacheManager")
    public MicroSkill findMicroSkillPopularity(long id) {
        Optional<MicroSkill> microSkillByPopularity = microSkillRepository.findById(id);
        if (microSkillByPopularity.isPresent()) {
            MicroSkill microSkill = microSkillByPopularity.get();
            microSkill.calculatePopularity();
            return microSkill;
        } else {
            throw new MicroSkillNotFoundException();
        }
    }

    @Transactional(readOnly = true)
    @Override
    @Cacheable(value = "microSkill", key = "#microSkillId", cacheManager = "objectCacheManager")
    public MicroSkill findMicroSkill(long microSkillId) {
        log.debug("Get MicroSkill by id : {}", microSkillId);
        return microSkillRepository.findById(microSkillId).orElseThrow(MicroSkillNotFoundException::new);
    }

    @Override
    @Transactional
    @CachePut(value = "microSkill", key = "#microSkillId", cacheManager = "objectCacheManager")
    public void updatePriceMicroSkill(long microSkillId, Double price) {
        MicroSkill microSkill = microSkillRepository.findById(microSkillId)
                .orElseThrow(MicroSkillNotFoundException::new);
        microSkill.setPrice(price);
        microSkillRepository.save(microSkill);
        log.debug("Microskill {} has been changed price to {}", microSkillId, price);
    }


    @Cacheable(value = "microSkill", key = "#level", cacheManager = "objectCacheManager")
    @Transactional(readOnly = true)
    @Override
    public List<MicroSkillDto> findLevelFromMicroSkill(Level level) {
        log.debug("finding level {} of MicroSkill", level);
        List<MicroSkill> microSkillByLvl = microSkillRepository.findByLevel(level);
        List<MicroSkillDto> microSkillDtoList = new ArrayList<>();
        for (MicroSkill microSkill : microSkillByLvl) {
            microSkillDtoList.add(modelMapper.map(microSkill, MicroSkillDto.class));
        }
        return microSkillDtoList;
    }

    @Cacheable(value = "microSkill", key = "#tags", cacheManager = "objectCacheManager")
    @Transactional(readOnly = true)
    @Override
    public List<MicroSkillDto> findMicroSkillByTag(Tags tags) {
        log.debug("finding tags {} of MicroSkill", tags);
        List<MicroSkill> microSkillByTag = microSkillRepository.findByTags(tags);
        List<MicroSkillDto> microSkillDtoList = new ArrayList<>();
        for (MicroSkill microSkill : microSkillByTag) {
            microSkillDtoList.add(modelMapper.map(microSkill, MicroSkillDto.class));
        }
        return microSkillDtoList;
    }

    @Override
    public List<MicroSkill> findTechnology(String name) {
        return microSkillRepository.findByTechnologyName(name);
    }

    @Override
    public List<MicroSkillDto> sortByPopularityMicroSkill() {
        log.debug("sorting MicroSkill by popularity");
        List<MicroSkill> microSkills = microSkillRepository.findAll();

        for (MicroSkill microSkill : microSkills) {
            microSkill.calculatePopularity();
        }

        microSkills.sort(Comparator.comparing(MicroSkill::getPopularity));
        List<MicroSkillDto> microSkillDtoPopular = new ArrayList<>();

        for (MicroSkill microSkill : microSkills) {
            microSkillDtoPopular.add(modelMapper.map(microSkill, MicroSkillDto.class));
        }
        return microSkillDtoPopular;
    }


    @Transactional
    @Cacheable(value = "microSkill", cacheManager = "objectCacheManager")
    @Override
    public List<MicroSkillDto> getBestDealsByToday() {
        log.debug("finding best deals by today's creation date");
        LocalDate twentyFourHoursAgo = LocalDate.now().minusDays(1);
        List<MicroSkill> microSkills = microSkillRepository.findByCreationDateAfter(twentyFourHoursAgo);

        for (MicroSkill microSkill : microSkills) {
            microSkill.calculatePopularity();
        }

        microSkills.sort(Comparator.comparing(MicroSkill::getPopularity).reversed());
        List<MicroSkillDto> microSkillDtoBestDayDeals = new ArrayList<>();

        for (MicroSkill microSkill : microSkills) {
            microSkillDtoBestDayDeals.add(modelMapper.map(microSkill, MicroSkillDto.class));
        }
        return microSkillDtoBestDayDeals;
    }
}
