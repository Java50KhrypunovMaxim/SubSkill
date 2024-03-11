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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;


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
        String level = "INTERMEDIATE";
        PageRequest pageable = PageRequest.of(0, 5);
        Page<MicroSkillDto> result = microSkillService.findLevelFromMicroSkill(Level.INTERMEDIATE, pageable);

        assertNotNull(result);
        assertEquals("0",0, result.getNumber());
        assertEquals("5",5, result.getSize());
        assertEquals("2",2, result.getTotalPages());
        assertEquals("8",8, result.getTotalElements());

        List<MicroSkillDto> microSkills = result.getContent();
        assertNotNull(microSkills);
        assertEquals("5",5, microSkills.size());
        MicroSkillDto microSkillDto = microSkills.get(0);
        assertNotNull(microSkillDto);
        assertEquals("Python Fundamentals", microSkillDto.name(),"Python Fundamentals");
        assertEquals( level,microSkillDto.level(),"INTERMEDIATE");
    }

    @Test
    public void testGetAllMicroSkills() {
        String  name = "Python Fundamentals";

        List<MicroSkillDto> myTestMicroSkills = microSkillService.findAllMicroSkills();

        List<String> myTestMicroSkillNames = myTestMicroSkills.stream().map(MicroSkillDto::name).toList();

        assertEquals(name, myTestMicroSkillNames.get(0), "Python Fundamentals");
    }

//    @Test
//    void testFindTechnology() {
//        String technologyName = "Java";
//        List<MicroSkill> expectedMicroSkills = MyMicroSkill();
//        MicroSkill firstMicroSkill = expectedMicroSkills.get(0);
//
//        List<MicroSkill> testMicroSkill = new ArrayList<>();
//        testMicroSkill.add(firstMicroSkill);
//        Technology technology = firstMicroSkill.getTechnology();
//
//        Mockito.lenient().when(technologyRepository.findById(10L)).thenReturn(Optional.of(technology));
//
//
//        Mockito.lenient().when(microSkillRepository.findByTechnologyName(technology.getName())).thenReturn(testMicroSkill);
//
//        List<MicroSkill> actualMicroSkills = microSkillService.findTechnology(technologyName);
//
//        assertNotNull(actualMicroSkills, "Not Null");
//        assertFalse(actualMicroSkills.isEmpty(), "At least 1 microskill");
//
//
//    }
//
//    @Test
//    void testFindMicroSkillByTag() {
//        Tags tag = Tags.DEVELOPMENT;
//        List<MicroSkill> expectedMicroSkills = MyMicroSkill();
//
//        when(microSkillRepository.findByTags(tag)).thenReturn(expectedMicroSkills);
//        List<MicroSkillDto> actualMicroSkills = microSkillService.findMicroSkillByTag(tag);
//
//        assertNotNull(actualMicroSkills);
//        assertEquals(expectedMicroSkills.size(), actualMicroSkills.size());
//        assertEquals(expectedMicroSkills.get(0), actualMicroSkills.get(0));
//        verify(microSkillRepository, times(1)).findByTags(tag);
//    }

//    @Test
//    void testGetBestDealsByToday() {
//        List<MicroSkill> expectedMicroSkills = MyMicroSkill();
//
//        when(microSkillRepository.findByCreationDateAfter(any())).thenReturn(expectedMicroSkills);
//        List<MicroSkillDto> actualMicroSkills = microSkillService.getBestDealsByToday();
//
//        assertNotNull(actualMicroSkills, "Not Null");
//        assertFalse(actualMicroSkills.isEmpty(), "At least 1 microskill");
//        verify(microSkillRepository, times(1)).findByCreationDateAfter(any());
//    }

    private List<MicroSkill> MyMicroSkill() {
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
