package com.subskill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.subskill.repository.MicroSkillRepository;
import com.subskill.repository.TechnologyRepository;

@SpringBootTest
@Sql(scripts = {"classpath:users.sql"})
public class SubskillTechnologyServiceTest {

    private static final String TECHNOLOGY_SERVICE_TEST = "Technology Service Test: ";
    @Autowired
    MicroSkillRepository microSkillRepository;

    @Autowired
    TechnologyRepository technologyRepository;

    TechnologyService technologyService;
    MicroSkillService micSkillService;

    private static final String TECHNOLOGY_NAME_1 = "About Java";
    private static final String TECHNOLOGY_NAME_2 = "About C++";

    private static final long TECHNOLOGY_ID_1 = 123124l;
    private static final long TECHNOLOGY_ID_2 = 876512l;


}