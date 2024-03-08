package com.subskill.service;

import com.subskill.dto.MicroSkillDto;
import com.subskill.enums.Level;
import com.subskill.enums.Tags;
import com.subskill.models.MicroSkill;
import com.subskill.models.Technology;
import com.subskill.repository.MicroSkillRepository;
import com.subskill.repository.TechnologyRepository;
import com.subskill.service.impl.MicroSkillServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Sql(scripts = {"classpath:data_for_the_database.sql"})
class SubSkillMicroSkillServiceTest {

    @Mock
    private MicroSkillRepository microSkillRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private TechnologyRepository technologyRepository;
    @Mock
    private WebApplicationContext context;

    private MockMvc mockMvc;
    @InjectMocks
    private MicroSkillServiceImplementation microSkillService;

//    @Test
//    void testFindLevelFromMicroSkill() {
//        Level level = Level.INTERMEDIATE;
//        List<MicroSkill> expectedMicroSkills = MyMicroSkill();
//
//        when(microSkillRepository.findByLevel(level)).thenReturn(expectedMicroSkills);
//
//        List<MicroSkillDto> result = microSkillService.findLevelFromMicroSkill(level);
//
//        assertNotNull(result);
//        assertEquals(expectedMicroSkills, result);
//        verify(microSkillRepository, times(1)).findByLevel(level);
//    }
//
//
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
//
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
//
//    private List<MicroSkill> MyMicroSkill() {
//        Technology javaTechnology = new Technology();
//        javaTechnology.setId(10L);
//        javaTechnology.setName("Java");
//
//        MicroSkill microSkill = new MicroSkill();
//        microSkill.setId(10L);
//        microSkill.setName("Python Fundamentals");
//        microSkill.setDescription("Fundamental concepts of Python programming");
//        microSkill.setLevel(Level.INTERMEDIATE);
//        microSkill.setViews(2000);
//        microSkill.setTechnology(javaTechnology);
//
//
//        Technology javaTechnology2 = new Technology();
//        javaTechnology2.setId(11L);
//        javaTechnology2.setName("Python");
//
//        MicroSkill microSkill2 = new MicroSkill();
//        microSkill2.setName("Web Development with React.js");
//        microSkill2.setDescription("Build modern web applications using React.js");
//        microSkill2.setLevel(Level.INTERMEDIATE);
//        microSkill2.setId(11L);
//        microSkill2.setViews(1800);
//        microSkill2.setTechnology(javaTechnology2);
//
//        Technology javaTechnology3 = new Technology();
//        javaTechnology3.setId(12L);
//        javaTechnology3.setName("React.js");
//
//        MicroSkill microSkill3 = new MicroSkill();
//        microSkill3.setName("Introduction to Data Science");
//        microSkill3.setDescription("Fundamental concepts of data science");
//        microSkill3.setLevel(Level.INTERMEDIATE);
//        microSkill3.setViews(1600);
//        microSkill3.setId(12L);
//        microSkill3.setTechnology(javaTechnology3);
//
//        return List.of(microSkill, microSkill2, microSkill3);
//    }
}
