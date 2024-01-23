package com.subskill.exception;

import com.subskill.api.ServiceExceptionMessages;

public class IllegalArticleStateException extends IllegalStateException {
		public IllegalArticleStateException() {
			super(ServiceExceptionMessages.ARTICLE_ALREADY_EXISTS);
		}
	}

