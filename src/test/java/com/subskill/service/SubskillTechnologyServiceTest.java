package com.subskill.service;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;

import com.subskill.dto.ArticleDto;
import com.subskill.dto.MicroSkillDto;
import com.subskill.models.MicroSkill;
import com.subskill.models.Technology;
import com.subskill.repository.MicroSkillRepository;
import com.subskill.repository.TechnologyRepository;

@SpringBootTest
@Sql(scripts = { "classpath:users.sql" })
public class SubskillTechnologyServiceTest {

	private static final String TECHNOLOGY_SERVICE_TEST = "Technology Service Test: ";
	@Autowired
	MicroSkillRepository microSkillRepository;

	@Autowired
	TechnologyRepository technologyRepository;

	TechnologyService technologeService;
	MicroSkillService micSkillService;

	private static final String TECHNOLOGY_NAME_1 = "About Java";
	private static final String TECHNOLOGY_NAME_2 = "About C++";

	private static final long TECHNOLOGY_ID_1 = 123124l;
	private static final long TECHNOLOGY_ID_2 = 876512l;

	

}
