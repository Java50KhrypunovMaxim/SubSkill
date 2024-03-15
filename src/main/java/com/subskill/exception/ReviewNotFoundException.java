package com.subskill.exception;

import com.subskill.api.ServiceExceptionMessages;
import org.springframework.transaction.TransactionSystemException;

@SuppressWarnings("serial")
public class ReviewNotFoundException extends NotFoundException {

	public  ReviewNotFoundException() {
		super(ServiceExceptionMessages.ARTICLE_NOT_FOUND);
		
	}

}