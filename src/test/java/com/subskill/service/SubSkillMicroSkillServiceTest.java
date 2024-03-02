package com.subskill.service;

import com.subskill.enums.Level;
import com.subskill.enums.Tags;
import com.subskill.models.MicroSkill;
import com.subskill.repository.MicroSkillRepository;
import com.subskill.service.impl.MicroSkillServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Sql(scripts = {"classpath:data_for_the_database.sql"})
class SubSkillMicroSkillServiceTest {

	@Mock
	private MicroSkillRepository microSkillRepository;

	@InjectMocks
	private MicroSkillServiceImplementation microSkillService;

	@BeforeEach
	void refreshServiceBeforeEachTest() {
		Mockito.reset(microSkillRepository);
	}



	@Test
	void testFindLevelFromMicroSkill() {

		List<MicroSkill> testMicroSkills = MyMic();
		for (MicroSkill microSkill : testMicroSkills) {
			microSkill.setLevel(Level.INTERMEDIATE);
		}

		Mockito.lenient().when(microSkillRepository.findByLevel(Level.INTERMEDIATE)).thenReturn(testMicroSkills);

		List<MicroSkill> result = microSkillService.findLevelFromMicroSkill(Level.INTERMEDIATE);

		assertNotNull(result);


		for (MicroSkill microSkill : result) {
			assertEquals(Level.INTERMEDIATE, microSkill.getLevel());
		}
	}

	private List<MicroSkill> MyMic (){
		MicroSkill microSkill = new MicroSkill();
		microSkill.setName("Java Programming");
		microSkill.setDescription("Learn Java programming language");
		microSkill.setLevel(Level.INTERMEDIATE);
		MicroSkill microSkill2 = new MicroSkill();
		microSkill2.setName("Python Programming");
		microSkill2.setDescription("Learn Python programming language");
		microSkill2.setLevel(Level.INTERMEDIATE);
		MicroSkill microSkill3 = new MicroSkill();
		microSkill3.setName("React.js");
		microSkill3.setDescription("Building user interfaces with React");
		microSkill3.setLevel(Level.INTERMEDIATE);
		return List.of(microSkill,microSkill2,microSkill3);
	}
	@Test
	void testFindMicroSkillByTag() {
		Tags tag = Tags.DEVELOPMENT;
		List<MicroSkill> expectedMicroSkills = Collections.singletonList(new MicroSkill());

		when(microSkillRepository.findByTags(tag)).thenReturn(expectedMicroSkills);

		List<MicroSkill> actualMicroSkills = microSkillService.findMicroSkillByTag(tag);

		assertNotNull(actualMicroSkills);
		assertEquals(expectedMicroSkills.size(), actualMicroSkills.size());
		assertEquals(expectedMicroSkills.get(0), actualMicroSkills.get(0));
		verify(microSkillRepository, times(1)).findByTags(tag);
	}

	@Test
	void testGetBestDealsByToday() {
		List<MicroSkill> expectedMicroSkills = Collections.singletonList(new MicroSkill());

		when(microSkillRepository.findByCreationDateAfter(any())).thenReturn(expectedMicroSkills);

		List<MicroSkill> actualMicroSkills = microSkillService.getBestDealsByToday();

		assertNotNull(actualMicroSkills);
		assertEquals(expectedMicroSkills.size(), actualMicroSkills.size());
		assertEquals(expectedMicroSkills.get(0), actualMicroSkills.get(0));
		verify(microSkillRepository, times(1)).findByCreationDateAfter(any());
	}

}
