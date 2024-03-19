package com.subskill.controller;

import com.subskill.SubSkillApplication;
import com.subskill.config.ObjectMapperConfig;
import com.subskill.enums.Level;
import com.subskill.enums.Tags;
import com.subskill.models.MicroSkill;
import com.subskill.models.Technology;
import com.subskill.service.MicroSkillService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import java.lang.reflect.Constructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
@SpringBootTest
@Import(ObjectMapperConfig.class)
@SpringJUnitWebConfig(classes = {SubSkillApplication.class})
@AutoConfigureMockMvc(addFilters = false)
public class SubSkillMicroSkillControllerTest {


    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    Technology technology;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        technology = new Technology();
        technology.setId(10L);

    }



    private final String DISPLAY_NAME = "This is test : ";

    private final MicroSkill MICROSKILL1 = MicroSkill.builder()
            .id(10L)
            .name("Python Fundamentals")
            .photo("python.jpg")
            .creationDate(LocalDate.parse("2022-02-01"))
            .description("Fundamental concepts of Python programming")
            .learningTime("3 weeks")
            .tags(List.of(Tags.BUSINESS, Tags.DESIGN))
            .level(Level.INTERMEDIATE)
            .rating(4.2)
            .popularity(100.0)
            .views(2000)
            .price(29.99)
            .lessonCount(12)
            .aboutSkill("Learn the fundamental concepts of Python programming language")
            .lastUpdateTime(LocalDateTime.parse("2022-02-28T09:30:00"))
            .technology(technology)
            .build();
    private final MicroSkill MICROSKILL2 = MicroSkill.builder()
            .id(26L)
            .name("Advanced Flutter Techniques")
            .photo("advanced-flutter.jpg")
            .creationDate(LocalDate.parse("2022-07-15"))
            .description("Advanced development techniques in Flutter framework")
            .learningTime("5 weeks")
            .tags(List.of(Tags.DEVELOPMENT, Tags.IT_SOFTWARE))
            .level(Level.ADVANCED)
            .rating(4.9)
            .popularity(160.0)
            .views(2300)
            .price(54.99)
            .lessonCount(15)
            .aboutSkill("Explore advanced Flutter techniques for cross-platform app development")
            .lastUpdateTime(LocalDateTime.parse("2022-08-10T14:45:00"))
            .build();
    @Test
    @Order(1)
    @DisplayName("MicroSkillController is marked as @RestController")
    void microSkillRestControllerAnnotation() {
        RestController restController = MicroSkillController.class.getAnnotation(RestController.class);

        assertNotNull(restController);
    }

    @Test
    @Order(2)
    @DisplayName(DISPLAY_NAME + "MicroSkillController is specified in @RequestMapping")
    void microSkillRestControllerRequestMapping() {
        RequestMapping requestMapping = MicroSkillController.class.getAnnotation(RequestMapping.class);

        assertNotNull(requestMapping);
        assertThat(requestMapping.value().length).isEqualTo(1);
        assertThat(requestMapping.value()).contains("api/v1/microskill");
    }

    @Test
    @Order(3)
    @DisplayName(DISPLAY_NAME + "MicroSkillController is injected using MicroSkillService constructor")
    void microSkillControllerInjection() {
        Constructor<?> constructor = MicroSkillController.class.getConstructors()[0];
        assertThat(constructor.getParameterTypes()).contains(MicroSkillService.class);
    }

    @Test
    @Order(4)
    @DisplayName(DISPLAY_NAME + "findAll")
    void testFindAll() throws Exception {

        mockMvc.perform(get("/api/v1/microskill/all").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(12))
                .andExpect(jsonPath("$.[*].name").value(hasItems(MICROSKILL1.getName(), MICROSKILL2.getName())));
    }

    @Test
    @Order(5)
    @DisplayName(DISPLAY_NAME + "findMicroSkillById")
    void testFindMicroSkillById() throws Exception {

        mockMvc.perform(get(String.format("/api/v1/microskill/find-by-id/%d", MICROSKILL1.getId()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(MICROSKILL1.getId()))
                .andExpect(jsonPath("$.description").value(MICROSKILL1.getDescription()))
                .andExpect(jsonPath("$.level").value("INTERMEDIATE"))
                .andExpect(jsonPath("$.name").value(MICROSKILL1.getName()));
    }

    @Test
    @Order(6)
    @DisplayName(DISPLAY_NAME + "getNewMicroSkills")
    void testGetNewMicroSkills() throws Exception {

        mockMvc.perform(get("/api/v1/microskill/new")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(4))
                .andExpect(jsonPath("$.content[0].name").value("Firebase Integration with Flutter"))
                .andExpect(jsonPath("$.content[0].level").value("INTERMEDIATE"))
                .andExpect(jsonPath("$.content[1].name").value(MICROSKILL2.getName()))
                .andExpect(jsonPath("$.content[1].level").value("ADVANCED"));
    }

    @Test
    @Order(7)
    @DisplayName(DISPLAY_NAME + "getByPopularity")
    void testGetByPopularity() throws Exception {

        mockMvc.perform(get("/api/v1/microskill/10/popularity")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(MICROSKILL1.getId()))
                .andExpect(jsonPath("$.description").value(MICROSKILL1.getDescription()))
                .andExpect(jsonPath("$.level").value("INTERMEDIATE"))
                .andExpect(jsonPath("$.name").value(MICROSKILL1.getName()));
    }

    @Test
    @Order(8)
    @DisplayName(DISPLAY_NAME + "getRevisedMicroSkills")
    void testGetRevisedMicroSkills() throws Exception {


        mockMvc.perform(get("/api/v1/microskill/mostViews")
                        .param("page", String.valueOf(0))
                        .param("size", String.valueOf(4))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(4))
                .andExpect(jsonPath("$.content[0].name").value("Advanced Flutter Techniques"))
                .andExpect(jsonPath("$.content[0].level").value("ADVANCED"))
                .andExpect(jsonPath("$.content[1].name").value("Advanced Python Programming"))
                .andExpect(jsonPath("$.content[1].level").value("ADVANCED"));
    }
}
