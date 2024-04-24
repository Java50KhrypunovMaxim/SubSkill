package com.subskill.service;

import com.subskill.dto.ArticleDto;

import java.util.List;

public interface ArticleService {
	ArticleDto addArticle(ArticleDto articleDto);

	ArticleDto updateArticle(ArticleDto articleDto,String articleName);

	void deleteArticle(String articleName);

    List<ArticleDto> allArticles();
}
