package com.subskill.service;

import java.util.List;

import com.subskill.dto.ArticleDto;

public interface ArticleService {
	ArticleDto addArticle(ArticleDto articleDto);
	ArticleDto updateArticle(ArticleDto articleDto);
	ArticleDto deleteArticle(String articleName);
	List<String> allArticles();

}
