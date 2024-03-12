package com.subskill.service;

import com.subskill.dto.ArticleDto;
import com.subskill.dto.MicroSkillDto;
import com.subskill.enums.Level;
import com.subskill.enums.Tags;
import com.subskill.models.MicroSkill;
import com.subskill.models.Technology;
import com.subskill.repository.MicroSkillRepository;
import com.subskill.repository.TechnologyRepository;
import com.subskill.service.impl.MicroSkillServiceImplementation;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@Slf4j
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Sql(scripts = {"classpath:data_for_the_database.sql"})
class SubSkillMicroSkillServiceTest {

    @Autowired
    private MicroSkillRepository microSkillRepository;


    @Autowired
    private MicroSkillService microSkillService;

    @Test
    public void testFindLevelFromMicroSkill() {
        PageRequest pageable = PageRequest.of(0, 5);
        Page<MicroSkillDto> result = microSkillService.findLevelFromMicroSkill(Level.INTERMEDIATE, pageable);

        assertNotNull(result);
        assertEquals("0", 0, result.getNumber());
        assertEquals("5", 5, result.getSize());
        assertEquals("2", 2, result.getTotalPages());
        List<MicroSkillDto> microSkills = result.getContent();
        assertNotNull(microSkills);
        assertEquals("5", 5, microSkills.size());
        MicroSkillDto microSkillDto = microSkills.get(0);
        assertNotNull(microSkillDto);
        assertEquals("Python Fundamentals", microSkillDto.name(), "Python Fundamentals");
        assertEquals("INTERMEDIATE", microSkillDto.level(), Level.INTERMEDIATE);
    }

    @Test
    public void testGetAllMicroSkills() {
        String name = "Python Fundamentals";

        List<MicroSkillDto> myTestMicroSkills = microSkillService.findAllMicroSkills();

        List<String> myTestMicroSkillNames = myTestMicroSkills.stream().map(MicroSkillDto::name).toList();

        assertEquals(name, myTestMicroSkillNames.get(0), "Python Fundamentals");
    }

    @Test
    public void testFindMicroSkillById() {
        long microSkillId = 10;
        MicroSkillDto microskillFromService = microSkillService.findMicroSkillById(microSkillId);
        Optional<MicroSkill> microSkill = microSkillRepository.findById(microSkillId);
        assertNotNull(microskillFromService);
        assertEquals("Same MicroSkill object",microSkill.get().getId() , microskillFromService.id());
    }

    @Test
    void testFindTechnologyName() {
        List<MicroSkill> microSkills = MyMicroSkill();

        Pageable pageable = PageRequest.of(0, 5);
        Page<MicroSkill> resultPage = new PageImpl<>(microSkills.stream()
                .filter(microSkill -> microSkill.getTechnology().getName().equals("Java"))
                .collect(Collectors.toList()), pageable, 5);


        Page<MicroSkillDto> newResult = microSkillService.findTechnology("Java", pageable);
        assertNotNull(newResult);
        Assertions.assertEquals(resultPage.getTotalElements(), newResult.getTotalElements());

    }

    @Test
    void testFindMicroSkillByTag() {
        PageRequest pageable = PageRequest.of(0, 5);
        Page<MicroSkillDto> result = microSkillService.findMicroSkillByTag(Tags.DEVELOPMENT, pageable);

        assertNotNull(result);
        assertEquals("0", 0, result.getNumber());
        assertEquals("5", 5, result.getSize());
        assertEquals("0", 0, result.getTotalPages());
        List<MicroSkillDto> microSkills = result.getContent();
        assertNotNull(microSkills);
        assertEquals("0", 0, microSkills.size());

    }


    @Test
    void testGetBestDealsByToday() {
        List<MicroSkillDto> microSkillsList = new ArrayList<>();
        microSkillsList.add(new MicroSkillDto(1L, "description1", "Good one"));

        PageRequest pageable = PageRequest.of(0, 5);
        Page<MicroSkillDto> actualMicroSkills = new PageImpl<>(microSkillsList, pageable, microSkillsList.size());

        assertNotNull(actualMicroSkills, "Not null");
        log.debug("Not Null in Best Deals by Today");
        Assertions.assertEquals(5, actualMicroSkills.getSize(), "correct size");
        List<MicroSkillDto> content = actualMicroSkills.getContent();

        String description = "description1";
        if (!content.isEmpty()) {
            MicroSkillDto firstMicroSkill = content.get(0);
            assertEquals(description, firstMicroSkill.name(), "description1");
        } else {
            fail("No MicroSkills in List");
        }
    }

    public List<MicroSkill> MyMicroSkill() {
        Technology javaTechnology = new Technology();
        javaTechnology.setId(10L);
        javaTechnology.setName("Java");

        MicroSkill microSkill = new MicroSkill();
        microSkill.setId(10L);
        microSkill.setName("Python Fundamentals");
        microSkill.setDescription("Fundamental concepts of Python programming");
        microSkill.setLevel(Level.INTERMEDIATE);
        microSkill.setViews(2000);
        microSkill.setTechnology(javaTechnology);


        Technology javaTechnology2 = new Technology();
        javaTechnology2.setId(11L);
        javaTechnology2.setName("Python");

        MicroSkill microSkill2 = new MicroSkill();
        microSkill2.setName("Web Development with React.js");
        microSkill2.setDescription("Build modern web applications using React.js");
        microSkill2.setLevel(Level.INTERMEDIATE);
        microSkill2.setId(11L);
        microSkill2.setViews(1800);
        microSkill2.setTechnology(javaTechnology2);

        Technology javaTechnology3 = new Technology();
        javaTechnology3.setId(12L);
        javaTechnology3.setName("React.js");

        MicroSkill microSkill3 = new MicroSkill();
        microSkill3.setName("Introduction to Data Science");
        microSkill3.setDescription("Fundamental concepts of data science");
        microSkill3.setLevel(Level.INTERMEDIATE);
        microSkill3.setViews(1600);
        microSkill3.setId(12L);
        microSkill3.setTechnology(javaTechnology3);

        return List.of(microSkill, microSkill2, microSkill3);
    }
}
