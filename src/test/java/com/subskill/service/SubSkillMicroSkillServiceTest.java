package com.subskill.service;

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



//    @BeforeEach
//    public void setup() {
//        Mockito.reset(microSkillRepository);
//        this.mockMvc = MockMvcBuilders
//                .webAppContextSetup(context)
//                .apply(springSecurity())
//                .build();
//    }
//
//    @BeforeEach
//    public void authenticateUser() {
//        String username = "testuser";
//        String password = "12345";
//        String encodedPassword = passwordEncoder.encode(password);
//
//        mockMvc.perform(formLogin("/login").user(username).password(password))
//                .andExpect(authenticated());
//    }


    @Test
    void testFindLevelFromMicroSkill() {
        Level level = Level.INTERMEDIATE;
        List<MicroSkill> expectedMicroSkills = MyMicroSkill();

        when(microSkillRepository.findByLevel(level)).thenReturn(expectedMicroSkills);

        List<MicroSkill> result = microSkillService.findLevelFromMicroSkill(level);

        assertNotNull(result);
        assertEquals(expectedMicroSkills, result);
        verify(microSkillRepository, times(1)).findByLevel(level);
    }


    @Test
    void testFindTechnology() {
        String technologyName = "Java";
        List<MicroSkill> expectedMicroSkills = MyMicroSkill();
        MicroSkill firstMicroSkill = expectedMicroSkills.get(0);

        List<MicroSkill> testMicroSkill = new ArrayList<>();
        testMicroSkill.add(firstMicroSkill);
        Technology technology = firstMicroSkill.getTechnology();
        technology.setName("Java");

        Mockito.lenient().when(technologyRepository.findById(1L)).thenReturn(Optional.of(technology));


        Mockito.lenient().when(microSkillRepository.findByTechnologyName(technology.getName())).thenReturn(testMicroSkill);

        List<MicroSkill> actualMicroSkills = microSkillService.findTechnology(technologyName);

        assertNotNull(actualMicroSkills, "Not Null");
        assertFalse(actualMicroSkills.isEmpty(), "At least 1 microskill");


    }

    @Test
    void testFindMicroSkillByTag() {
        Tags tag = Tags.DEVELOPMENT;
        List<MicroSkill> expectedMicroSkills = MyMicroSkill();

        when(microSkillRepository.findByTags(tag)).thenReturn(expectedMicroSkills);

        List<MicroSkill> actualMicroSkills = microSkillService.findMicroSkillByTag(tag);

        assertNotNull(actualMicroSkills);
        assertEquals(expectedMicroSkills.size(), actualMicroSkills.size());
        assertEquals(expectedMicroSkills.get(0), actualMicroSkills.get(0));
        verify(microSkillRepository, times(1)).findByTags(tag);
    }

    @Test
    void testGetBestDealsByToday() {
        List<MicroSkill> expectedMicroSkills = MyMicroSkill();

        when(microSkillRepository.findByCreationDateAfter(any())).thenReturn(expectedMicroSkills);

        List<MicroSkill> actualMicroSkills = microSkillService.getBestDealsByToday();

        assertNotNull(actualMicroSkills, "Not Null");
        assertFalse(actualMicroSkills.isEmpty(), "At least 1 microskill");
        verify(microSkillRepository, times(1)).findByCreationDateAfter(any());
    }

    private List<MicroSkill> MyMicroSkill() {
        Technology javaTechnology = new Technology();
        javaTechnology.setId(1L);
        javaTechnology.setName("Java");

        MicroSkill microSkill = new MicroSkill();
        microSkill.setName("Java Programming");
        microSkill.setDescription("Learn Java programming language");
        microSkill.setLevel(Level.INTERMEDIATE);
        microSkill.setViews(100);
        microSkill.setTechnology(javaTechnology);


        Technology javaTechnology2 = new Technology();
        javaTechnology2.setId(2L);
        javaTechnology2.setName("Java");

        MicroSkill microSkill2 = new MicroSkill();
        microSkill2.setName("Python Programming");
        microSkill2.setDescription("Learn Python programming language");
        microSkill2.setLevel(Level.INTERMEDIATE);
        microSkill2.setViews(120);
        microSkill2.setTechnology(javaTechnology2);

        Technology javaTechnology3 = new Technology();
        javaTechnology3.setId(3L);
        javaTechnology3.setName("React.js");

        MicroSkill microSkill3 = new MicroSkill();
        microSkill3.setName("React.js");
        microSkill3.setDescription("Building user interfaces with React");
        microSkill3.setLevel(Level.INTERMEDIATE);
        microSkill3.setViews(150);
        microSkill3.setTechnology(javaTechnology3);

        return List.of(microSkill, microSkill2, microSkill3);
    }
}
