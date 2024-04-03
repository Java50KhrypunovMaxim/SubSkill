package com.subskill.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import com.subskill.config.ObjectMapperConfig;
import com.subskill.dto.TechnologyDto;
import com.subskill.enums.Level;
import com.subskill.models.MicroSkill;
import com.subskill.models.Profession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subskill.models.Technology;
import com.subskill.repository.MicroSkillRepository;
import com.subskill.repository.TechnologyRepository;
import com.subskill.service.MicroSkillService;
import com.subskill.service.TechnologyService;

@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
@Import(ObjectMapperConfig.class)
@SpringBootTest(classes = {TechnologyController.class})
@AutoConfigureJsonTesters
public class SubSkillTechnologyControllerTest {

    @MockBean
    TechnologyService technologyService;

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
    private static final long TECHNOLOGY_ID_1 = 123124L;
    private static final long TECHNOLOGY_ID_2 = 876512L;


    @Test
    void testGetAllTechnologies() throws Exception {
        List<TechnologyDto> expectedTechnologyList = List.of(new TechnologyDto(1L,TECHNOLOGY_NAME_1));
        when(technologyService.getAllTechnology()).thenReturn(expectedTechnologyList);
        String actualJSON = mockMvc.perform(get("/api/v1/technology/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        List<Technology> actualTechnologyList = Arrays.asList(mapper.readValue(actualJSON, Technology[].class));
        assertEquals(expectedTechnologyList, actualTechnologyList);
    }


    @Test
    void testGetTechnologyById() throws Exception {
        Technology expectedTechnology = new Technology(TECHNOLOGY_ID_1, TECHNOLOGY_NAME_1,
                new Profession(), List.of(), 10L);
         when(technologyService.getByID(TECHNOLOGY_ID_1)).thenReturn(expectedTechnology);
         String jsonExpected = mapper.writeValueAsString(expectedTechnology);
         String actualJSON = mockMvc.perform(get("/api/v1/technology/id/" + TECHNOLOGY_ID_1)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(jsonExpected, actualJSON);
    }


    @Test
    void testGetTechnologyByName() throws Exception {

        Technology expectedTechnology = new Technology(TECHNOLOGY_ID_1, TECHNOLOGY_NAME_1, new Profession(),List.of() , 1L);
        String jsonExpected = mapper.writeValueAsString(expectedTechnology);
        when(technologyService.getByName(TECHNOLOGY_NAME_1)).thenReturn(expectedTechnology);
        String actualJSON = mockMvc.perform(get("/api/v1/technology/name/" + TECHNOLOGY_NAME_1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(jsonExpected, actualJSON);
    }

}

