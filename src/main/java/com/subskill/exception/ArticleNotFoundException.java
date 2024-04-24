package com.subskill.exception;

import com.subskill.api.ServiceExceptionMessages;

@SuppressWarnings("serial")
public class ArticleNotFoundException extends NotFoundException {

	public  ArticleNotFoundException() {
		super(ServiceExceptionMessages.ARTICLE_NOT_FOUND);
		
	}

}
