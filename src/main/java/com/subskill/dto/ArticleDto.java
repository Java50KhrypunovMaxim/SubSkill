package com.subskill.dto;

public record ArticleDto (
		Long id,
		String articleName,
		
		String textOfArticle,

        long microskillId)
	{}