package com.subskill;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.subskill.dto.ArticleDto;
import com.subskill.dto.UserDto;
import com.subskill.exception.ArticleNotFoundException;
import com.subskill.exception.IllegalArticleStateException;
import com.subskill.exception.IllegalUsersStateException;
import com.subskill.exception.NoUserInRepositoryException;
import com.subskill.models.Article;
import com.subskill.models.Roles;
import com.subskill.models.User;
import com.subskill.repository.ArticleRepository;
import com.subskill.repository.UserRepository;
import com.subskill.service.ArticleService;
import com.subskill.service.UserService;

	@SpringBootTest
	@Sql(scripts = {"classpath:users.sql"})
	class SubskillArticleServiceTest {
		
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
		private static final  long IdSkills2 = 4567;
		private static final long IdSkills3 = 8901;
		/****************************************************************************/
		
		//Article DTO
		ArticleDto ArticleDto1 = new ArticleDto (ARTICLENAME1,TEXT1,IdSkills1);
		ArticleDto ArticleDto2 = new ArticleDto (ARTICLENAME2,TEXT2,IdSkills2);
		ArticleDto ArticleDto3 = new ArticleDto (ARTICLENAME3,TEXT3,IdSkills3);
		
		public static final List<String> ALLAARTICLES = Arrays.asList(
				  "Article1","Article2","Article3","Article4","Article5");
		
		//Repo's
		@Autowired
		ArticleRepository articleRepo;
		//Service bean
		@Autowired
		ArticleService articleService;
		/***************************************************************************************/
		
		@Test
		@DisplayName(ARTICLE_SERVICE_TEST + SubSkilleTestNameUserService.SHOW_ALL_ARTICLES)
		void testShowAllUsers() {
			assertEquals(ALLAARTICLES, articleService.allArticles());
		}
		
		@Test
		@DisplayName(ARTICLE_SERVICE_TEST + SubSkilleTestNameUserService.ADD_ARTICLE)
		void testdeArticle() {
			assertEquals(ArticleDto1, articleService.addArticle(ArticleDto1));
			assertThrowsExactly(IllegalArticleStateException.class,() -> articleService.addArticle(ArticleDto1));
			Article article = articleRepo.findByArticleName(ArticleDto1.articleName()).orElse(null);
			assertEquals(ArticleDto1, article.build());
		}
		
		@Test
		@DisplayName(ARTICLE_SERVICE_TEST + SubSkilleTestNameUserService.DELETE_ARTICLE)
		void testDeleteArticle() {
			ArticleDto articleFromBase = new ArticleDto	("Article1","Text of Article 11",11);
			assertEquals(articleFromBase, articleService.deleteArticle("Article1"));
			assertThrowsExactly(ArticleNotFoundException.class, () -> articleService.deleteArticle("Article1"));
		}
		
		@Test
		@DisplayName(ARTICLE_SERVICE_TEST + SubSkilleTestNameUserService.UPDATE_ARTICLE)
		void testUpdateArticle() {
			articleService.addArticle(ArticleDto2);
			ArticleDto articleDtoUpdate = new ArticleDto(ARTICLENAME2,TEXT3,IdSkills2);
			assertEquals(articleDtoUpdate, articleService.updateArticle(articleDtoUpdate));
			assertEquals(TEXT3,articleRepo.findByArticleName(ARTICLENAME2).get().getTextOfArticle());	
		}
		
		
		
		
		
		
		
	}