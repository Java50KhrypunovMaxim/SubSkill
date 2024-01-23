package com.subskill.exception;

import subskill.subskill.api.ServiceExceptionMessages;

@SuppressWarnings("serial")
public class ArticleNotFoundException extends NotFoundException {

	public  ArticleNotFoundException() {
		super(ServiceExceptionMessages.ARTICLE_NOT_FOUND);
		
	}

}
