package subskill.subskill.exception;

import subskill.subskill.api.ServiceExceptionMessages;

public class IllegalArticleStateException extends IllegalStateException {
		public IllegalArticleStateException() {
			super(ServiceExceptionMessages.ARTICLE_ALREADY_EXISTS);
		}
	}

