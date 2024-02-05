package com.subskill.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import com.subskill.models.Profession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subskill.dto.MicroSkillDto;
import com.subskill.models.Technology;
import com.subskill.repository.MicroSkillRepository;
import com.subskill.repository.TechnologyRepository;
import com.subskill.service.MicroSkillService;
import com.subskill.service.TechnologyService;

@WebMvcTest(TechnologyController.class)
public class SubSkillTechologyControllerTest {

    @MockBean
    TechnologyService technologeService;

    @MockBean
    MicroSkillRepository microSkillRepository;

    @MockBean
    TechnologyRepository technologyRepository;

    @MockBean
    MicroSkillService micSkillService;
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    private static final String TECHNOLOGY_NAME_1 = "About Java";
    private static final String TECHNOLOGY_NAME_2 = "About C++";

    private static final long TECHNOLOGY_ID_1 = 123124l;
    private static final long TECHNOLOGY_ID_2 = 876512l;

    @BeforeEach()
    public void setup() {
        MicroSkillDto microSkillDto1 = new MicroSkillDto("Database Design", 4.3, "database_design.jpg", List.of());
        MicroSkillDto microSkillDto2 = new MicroSkillDto("Database Backend", 13.3, "database_backend.jpg", List.of());
        micSkillService.addMicroskill(microSkillDto1);
        micSkillService.addMicroskill(microSkillDto2);
        Profession profession = new Profession("QA");
        Technology techology_1 = new Technology(TECHNOLOGY_ID_1, TECHNOLOGY_NAME_1, profession, microSkillRepository.findByViews(76766l));
        Technology techology_2 = new Technology(TECHNOLOGY_ID_2, TECHNOLOGY_NAME_2, profession, microSkillRepository.findByViews(71231l));
        technologyRepository.save(techology_1);
        technologyRepository.save(techology_2);
        
    }


    @Test
    void testGetAllTechnologes() throws Exception {
        List<String> nameOfTechnologyList = Arrays.asList(TECHNOLOGY_NAME_1, TECHNOLOGY_NAME_2);
        when(technologeService.getAllTechnology()).thenReturn(nameOfTechnologyList);

        String actualJSON = mockMvc.perform(get("/api/v1/technology/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<String> actualArticlesList = Arrays.asList(mapper.readValue(actualJSON, String[].class));
        assertEquals(nameOfTechnologyList, actualArticlesList);
    }

    @Test
    void testGetTechnologyById() throws Exception {
        Technology expectedTechology = new Technology(TECHNOLOGY_ID_1, TECHNOLOGY_NAME_1, new Profession(), microSkillRepository.findByViews(76766l));
        String jsonExpected = mapper.writeValueAsString(expectedTechology);
        when(technologeService.getByID(TECHNOLOGY_ID_1)).thenReturn(expectedTechology);
        String actualJSON = mockMvc.perform(get("/api/v1/technology/id/" + TECHNOLOGY_ID_1))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(jsonExpected, actualJSON);
    }


    @Test
    void testGetTechnologyByName() throws Exception {
    	
    	Technology expectedTechnology = new Technology(TECHNOLOGY_ID_1, TECHNOLOGY_NAME_1, new Profession(), microSkillRepository.findByViews(76766L));
        String jsonExpected = mapper.writeValueAsString(expectedTechnology);
        when(technologeService.getByName(TECHNOLOGY_NAME_1)).thenReturn(expectedTechnology);
        String actualJSON = mockMvc.perform(get("/api/v1/technology/name/" + TECHNOLOGY_NAME_1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(jsonExpected, actualJSON);
    }
    @Test
    void testGetByProfessionName() throws Exception {
        Profession profession = new Profession("QA");
        Technology technology_1 = new Technology(TECHNOLOGY_ID_1, TECHNOLOGY_NAME_1, profession, microSkillRepository.findByViews(76766L));
        Technology technology_2 = new Technology(TECHNOLOGY_ID_2, TECHNOLOGY_NAME_2, profession, microSkillRepository.findByViews(71231L));
        List<Technology> expectedTechnologyList = Arrays.asList(technology_1, technology_2);

        when(technologeService.getByProfessionName("QA")).thenReturn(expectedTechnologyList);

        String actualJSON = mockMvc.perform(get("/api/v1/technology/professional/QA").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<Technology> actualTechnologyList = Arrays.asList(mapper.readValue(actualJSON, Technology[].class));
        assertEquals(expectedTechnologyList, actualTechnologyList);
}

}

