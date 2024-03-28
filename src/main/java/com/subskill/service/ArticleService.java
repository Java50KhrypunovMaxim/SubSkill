package com.subskill.service;

import java.util.List;

import com.subskill.dto.ArticleDto;

public interface ArticleService {
	ArticleDto addArticle(ArticleDto articleDto);

	ArticleDto updateArticle(ArticleDto articleDto,String articleName);

	void deleteArticle(String articleName);

    List<ArticleDto> allArticles();
}
