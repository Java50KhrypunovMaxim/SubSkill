package com.subskill.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.subskill.dto.ProfessionDto;
import com.subskill.dto.TechnologyDto;
import com.subskill.models.Technology;
import com.subskill.repository.MicroSkillRepository;
import com.subskill.repository.ProfessionRepository;
import com.subskill.repository.TechnologyRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = {"classpath:data_for_the_database.sql"})
public class SubskillTechnologyServiceTest  {
	@Autowired
	TechnologyRepository technologyRepository;

	@Autowired
	TechnologyService technologyService;
	
	private static final String TECHNOLOGY_SERVICE_TEST = "Technology Service Test:";
	private List<TechnologyDto> expectList = Arrays.asList(
            new TechnologyDto(10l, "Java"),
            new TechnologyDto(11l, "Python"),
            new TechnologyDto(12l, "React.js"),
            new TechnologyDto(13l, "Data Science")
    );

	@Test
	@DisplayName(TECHNOLOGY_SERVICE_TEST + SubSkillTestNameService.SHOW_ALL_TECHNOLOGY)
	void testfindAllTechnology() {
		List<TechnologyDto> actualList = technologyService.getAllTechnology();
		assertIterableEquals(expectList, actualList);
	}
	
	@Test
	@DisplayName(TECHNOLOGY_SERVICE_TEST + SubSkillTestNameService.GET_TECHNOLOGY_BY_NAME)
	void testGetTechnologyByName() {
		Technology expectTechnology =  new Technology(11l, 10l, "Python");
		Technology actualTechnology = technologyService.getByName("Python");
		assertEquals(actualTechnology, actualTechnology);
	}
}
