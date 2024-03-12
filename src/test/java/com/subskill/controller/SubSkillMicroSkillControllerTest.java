package com.subskill.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subskill.config.ObjectMapperConfig;
import com.subskill.service.MicroSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("test")
@SpringBootTest(classes = {CartController.class})
@Import(ObjectMapperConfig.class)
@AutoConfigureMockMvc(addFilters = false)
public class SubSkillMicroSkillControllerTest {
    @MockBean
    MicroSkillService microSkillService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

}
