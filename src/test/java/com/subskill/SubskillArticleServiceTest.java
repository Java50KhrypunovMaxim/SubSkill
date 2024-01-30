package com.subskill;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
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
import com.subskill.exception.IllegalArticleStateException;
import com.subskill.models.Article;
import com.subskill.repository.ArticleRepository;
import com.subskill.repository.MicroSkillRepository;
import com.subskill.service.ArticleService;
import com.subskill.service.MicroSkillService;

@SpringBootTest
@Sql(scripts = {"classpath:users.sql"})
class SubskillArticleServiceTest {
	//Repo's
	@Autowired
	ArticleRepository articleRepo;
	@Autowired
	MicroSkillRepository microSkillRepo;
	//Service bean
	@Autowired
	ArticleService articleService;
	@Autowired
	MicroSkillService microSkillsService;

	private static final String ARTICLE_SERVICE_TEST = "Article Service Test: ";
	//Article names
	private static final String ARTICLENAME1 = "About Java";
	private static final String ARTICLENAME2 = "About C++";
	private static final String ARTICLENAME3 = "About Pyton";

	/**************************************************************/
	//Article text
	private static final String TEXT1 = "Rambo";
	private static final String TEXT2 = "Leo";
	private static final String TEXT3 = "Vandam";

	/*********************************************************************/

	//Id skills
	private static final long IdSkills1 = 1234;
	private static final long IdSkills2 = 4567;
	private static final long IdSkills3 = 8901;


	/****************************************************************************/

	public static final List<String> ALLAARTICLES = Arrays.asList();


	/***************************************************************************************/

	@Test
	@DisplayName(ARTICLE_SERVICE_TEST + SubSkilleTestNameUserService.SHOW_ALL_ARTICLES)
	void testShowAllUsers() {
		assertEquals(ALLAARTICLES, articleService.allArticles());
	}

	@Test
	@DisplayName(ARTICLE_SERVICE_TEST + SubSkilleTestNameUserService.ADD_ARTICLE)
	void testAddArticle() {
		MicroSkillDto microSkillDto1 = new MicroSkillDto("Java Basics", 4.66, "java_basics.jpg");
		MicroSkill microSkill = MicroSkill.of(microSkillDto1);

		ArticleDto articleDto1 = new ArticleDto(ARTICLENAME1, TEXT1, microSkill);
		microSkill.setArticles(Arrays.asList(Article.of(articleDto1)));

		microSkillRepo.save(microSkill);
		microSkill = microSkillRepo.findById(microSkill.getId()).orElseThrow();

		assertEquals(articleDto1, articleService.addArticle(articleDto1));
		assertThrowsExactly(IllegalArticleStateException.class, () -> articleService.addArticle(articleDto1));

		Article article = articleRepo.findByArticleName(articleDto1.articleName()).orElse(null);
		assertEquals(articleDto1, article.build());
	}


	@Test
	@DisplayName(ARTICLE_SERVICE_TEST + SubSkilleTestNameUserService.DELETE_ARTICLE)
	void testDeleteArticle() {
		MicroSkillDto microSkillDto2 = new MicroSkillDto("Spring Framework", 4.8, "spring_framework.jpg");
		MicroSkill microSkill = MicroSkill.of(microSkillDto2);

		ArticleDto articleDto2 = new ArticleDto(ARTICLENAME2, TEXT2, microSkill);
		microSkill.setArticles(Arrays.asList(Article.of(articleDto2)));

		microSkillRepo.save(microSkill);
		microSkill = microSkillRepo.findById(microSkill.getId()).orElseThrow();
		articleService.deleteArticle("Article2");
		assertThrowsExactly(ArticleNotFoundException.class, () -> articleService.deleteArticle("Article2"));
	}

	@Test
	@DisplayName(ARTICLE_SERVICE_TEST + SubSkilleTestNameUserService.UPDATE_ARTICLE)
	void testUpdateArticle() {
		MicroSkillDto microSkillDto3 = new MicroSkillDto("Database Design", 4.3, "database_design.jpg");
		MicroSkill microSkill = MicroSkill.of(microSkillDto3);

		ArticleDto articleDto3 = new ArticleDto(ARTICLENAME3, TEXT3, microSkill);
		microSkill.setArticles(Arrays.asList(Article.of(articleDto3)));

		microSkillRepo.save(microSkill);
		microSkill = microSkillRepo.findById(microSkill.getId()).orElseThrow();
		ArticleDto articleDtoUpdate = new ArticleDto(ARTICLENAME2, TEXT3, MicroSkill.of(microSkillDto3));
		assertEquals(articleDtoUpdate, articleService.updateArticle(articleDtoUpdate));
		assertEquals(TEXT3, articleRepo.findByArticleName(ARTICLENAME2).get().getTextOfArticle());
	}
}