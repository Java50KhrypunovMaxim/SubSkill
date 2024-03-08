package com.subskill.service;

import com.subskill.dto.ArticleDto;
import com.subskill.exception.ArticleNotFoundException;
import com.subskill.models.Article;
import com.subskill.models.MicroSkill;
import com.subskill.repository.ArticleRepository;
import com.subskill.repository.MicroSkillRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = {"classpath:data_for_the_database.sql"})
public class SubSkillArticleServiceTest {
	@Autowired
	ArticleRepository articleRepo;


	@Autowired
	MicroSkillRepository microSkillRepo;

	@Autowired
	ArticleService articleService;

	@Autowired
	MicroSkillService microSkillsService;

	private static final String ARTICLE_SERVICE_TEST = "Article Service Test: ";

	private static final String ARTICLENAME1 = "About Java 1 part";
	private static final String ARTICLENAME2 = "About C++";
	private static final String ARTICLENAME3 = "About Python";
	private static final String TEXT1 = "Rambo 123";
	private static final long idOfMicroskill1 = 10;
	private static final long idOfMicroskill2 = 12;

	List<String> nameArticles = new ArrayList<>(List.of(
			"Python Basics",
			"Web Development with React",
			"Data Science Essentials",
			"Mobile App Development"
	));

	private MicroSkill microSkill;
	private ArticleDto articleDto1;

	@Test
	@DisplayName(ARTICLE_SERVICE_TEST + SubSkillTestNameService.SHOW_ALL_ARTICLES)
	void testShowAllArticles() {
		List<ArticleDto> listOfArticles = articleService.allArticles();
		List<String> articleNames = listOfArticles.stream().map(ArticleDto::articleName)
				.collect(Collectors.toList());
		assertEquals(nameArticles, articleNames);
	}


	@Test
	@DisplayName(ARTICLE_SERVICE_TEST + SubSkillTestNameService.ADD_ARTICLE)
	void testAddArticle() {
		Optional<MicroSkill> optionalMicro = microSkillRepo.findById(idOfMicroskill1);
		MicroSkill micro = new MicroSkill();
		if (optionalMicro.isPresent()) {
			micro = optionalMicro.get();
		}
		ArticleDto articleDto1 = new ArticleDto(ARTICLENAME1, TEXT1, 10L);
		ArticleDto savedArticleDto = articleService.addArticle(articleDto1);
		assertEquals(articleDto1, savedArticleDto);
	}

	@Test
	@DisplayName(ARTICLE_SERVICE_TEST + SubSkillTestNameService.DELETE_ARTICLE)
	void testDeleteArticle() {
		articleService.deleteArticle("Python Basics");
		assertThrowsExactly(ArticleNotFoundException.class, () -> articleService.deleteArticle("Introduction to Java"));
	}

	@SuppressWarnings("deprecation")
	@Test
	@DisplayName(ARTICLE_SERVICE_TEST + SubSkillTestNameService.UPDATE_ARTICLE)
	void testUpdateArticle() {
		Optional<MicroSkill> optionalMicro = microSkillRepo.findById(idOfMicroskill2);
		MicroSkill micro = optionalMicro.orElseThrow(() -> new EntityNotFoundException("MicroSkill not found"));
		ArticleDto articleDto1 = new ArticleDto("Python Basics", "Test", 10L);
		articleService.updateArticle(articleDto1);

		assertEquals(articleDto1.articleName(), articleRepo.findByArticleName("Python Basics").get().getArticleName());
	}

}
	


	