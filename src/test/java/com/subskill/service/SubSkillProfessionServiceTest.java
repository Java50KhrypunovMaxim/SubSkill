package com.subskill.service;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import com.subskill.dto.ProfessionDto;
import com.subskill.repository.ProfessionRepository;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = {"classpath:data_for_the_database.sql"})
public class SubSkillProfessionServiceTest {
	@Autowired
	ProfessionRepository professionRepository;

	@Autowired
	ProfessionService professionService;
	
	private static final String PROFESSION_SERVICE_TEST = "Profession Service Test:";
	private List<ProfessionDto> expectList = Arrays.asList(
            new ProfessionDto(10l, "DEVELOPMENT"),
            new ProfessionDto(11l, "BUSINESS"),
            new ProfessionDto(12l, "IT_SOFTWARE"),
            new ProfessionDto(13l, "DESIGN")
    );
	
	@Test
	@DisplayName(PROFESSION_SERVICE_TEST + SubSkillTestNameService.SHOW_ALL_PROFESSION)
	void testfindAllProfession() {
		List<ProfessionDto> actualList = professionService.findAll();
		assertIterableEquals(expectList, actualList);
	};
	
}
