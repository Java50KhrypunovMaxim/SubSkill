package com.subskill.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.subskill.models.MicroSkill;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.subskill.dto.ArticleDto;
import com.subskill.dto.MicroSkillDto;
import com.subskill.exception.ArticleNotFoundException;
import com.subskill.models.Article;
import com.subskill.repository.ArticleRepository;
import com.subskill.repository.MicroSkillRepository;

@SpringBootTest
@Sql(scripts = {"classpath:users.sql"})
class SubSkillArticleServiceTest {
	@Autowired
	ArticleRepository articleRepo;

	@Autowired
	MicroSkillRepository microSkillRepo;

	@Autowired
	ArticleService articleService;

	@Autowired
	MicroSkillService microSkillsService;

	private static final String ARTICLE_SERVICE_TEST = "Article Service Test: ";

	private static final String ARTICLENAME1 = "About Java";
	private static final String ARTICLENAME2 = "About C++";
	private static final String ARTICLENAME3 = "About Python";
	private static final String TEXT1 = "Rambo";

	public static final List<String> ALLARTICLES = List.of();

	private MicroSkill microSkill;
	private ArticleDto articleDto1;

	@Test
	@DisplayName(ARTICLE_SERVICE_TEST + SubSkillTestNameUserService.SHOW_ALL_ARTICLES)
	void testShowAllArticles() {
		assertEquals(ALLARTICLES, articleService.allArticles());
	}

	@Test
	@DisplayName(ARTICLE_SERVICE_TEST + SubSkillTestNameUserService.ADD_ARTICLE)
	void testAddArticle() {
		MicroSkillDto microSkillDto1 = new MicroSkillDto("Java Basics", 4.66, "java_basics.jpg", List.of());
		MicroSkill microSkill = MicroSkill.of(microSkillsService.addMicroskill(microSkillDto1));

		ArticleDto articleDto1 = new ArticleDto(ARTICLENAME1, TEXT1, microSkill);
		ArticleDto savedArticleDto = articleService.addArticle(articleDto1);

		assertEquals(articleDto1, savedArticleDto);
		deleteArticleAndMicroSkill(ARTICLENAME1);
	}

	public MicroSkill saveMicroSkill(MicroSkillDto microSkillDto) {
		MicroSkill microSkill = MicroSkill.of(microSkillDto);
		microSkillRepo.save(microSkill);
		return microSkill;
	}


	@Test
	@DisplayName(ARTICLE_SERVICE_TEST + SubSkillTestNameUserService.DELETE_ARTICLE)
	void testDeleteArticle() {
		microSkillRepo.save(microSkill);
		microSkill = microSkillRepo.findById(microSkill.getId()).orElseThrow();
		articleService.deleteArticle("Article2");
		assertThrowsExactly(ArticleNotFoundException.class, () -> articleService.deleteArticle("Article2"));
		deleteArticleAndMicroSkill(ARTICLENAME1);
	}

	@Test
	@DisplayName(ARTICLE_SERVICE_TEST + SubSkillTestNameUserService.UPDATE_ARTICLE)
	void testUpdateArticle() {
		microSkillRepo.save(microSkill);
		microSkill = microSkillRepo.findById(microSkill.getId()).orElseThrow();

		ArticleDto articleDtoUpdate = new ArticleDto(ARTICLENAME2, TEXT1, microSkill);
		assertEquals(articleDtoUpdate, articleService.updateArticle(articleDtoUpdate));
		assertEquals(TEXT1, articleRepo.findByArticleName(ARTICLENAME2).get().getTextOfArticle());
		deleteArticleAndMicroSkill(ARTICLENAME2);
	}


	private void deleteArticleAndMicroSkill(String... articleNames) {
		for (String articleName : articleNames) {
			Article article = articleRepo.findByArticleName(articleName).orElse(null);
			if (article != null) {
				MicroSkill microSkill = article.getMicroSkill();
				articleRepo.delete(article);
				if (microSkill != null) {
					microSkillRepo.delete(microSkill);
				}
			}
		}
	}
}