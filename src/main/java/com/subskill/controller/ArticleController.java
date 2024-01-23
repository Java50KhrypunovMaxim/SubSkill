package com.subskill.controller;

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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.subskill.dto.ArticleDto;
import com.subskill.service.ArticleService;

import static com.subskill.api.ValidationConstants.MISSING_ID_OF_ARTICLE;

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
	ArticleDto updateArticle(@RequestBody @Valid ArticleDto articleDto) {
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