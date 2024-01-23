package subskill.subskill.controller;

import static subskill.subskill.api.ValidationConstants.*;
import static subskill.subskill.api.ValidationConstants.MISSING_PASSWORD_MESSAGE;
import static subskill.subskill.api.ValidationConstants.MISSING_PERSON_EMAIL;
import static subskill.subskill.api.ValidationConstants.PASSWORD_REGEXP;
import static subskill.subskill.api.ValidationConstants.WRONG_EMAIL_FORMAT;
import static subskill.subskill.api.ValidationConstants.WRONG_PASSWORD_CREATION_MESSAGE;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import subskill.subskill.dto.ArticleDto;
import subskill.subskill.dto.UserDto;
import subskill.subskill.service.ArticleService;
import subskill.subskill.service.UserService;

@RestController
@RequestMapping("api/v1/articles")
@RequiredArgsConstructor
@Slf4j 

public class ArticleController {
	final ArticleService articlesService;
	
	@PostMapping()
	ArticleDto registerUser(@RequestBody @Valid ArticleDto articleDto) {
		log.debug("register article: received article data: {}", articleDto);
		return articlesService.addArticle(articleDto);
	}
	
	@PutMapping("update/{email}")
	ArticleDto updateUser(@RequestBody @Valid ArticleDto articleDto) {
		log.debug("update article: received new information about article: {}", articleDto);
		return  articlesService.updateArticle(articleDto);
	}
	
	@DeleteMapping("/{id}")
	ArticleDto deleteArticle(@NotEmpty (message=MISSING_ID_OF_ARTICLE) long id) {
		log.debug("delete user: user with email {}", id);
		return articlesService.deleteArticle(id);
	}
	
	@GetMapping ("/all")
	List<String> listOfArticles() {
        log.debug("List of articles have been received");
        return articlesService.allArticles();
    }
}
