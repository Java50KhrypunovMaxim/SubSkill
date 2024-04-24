package com.subskill.exception.controller;

import com.subskill.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class ExceptionsController {
	public static String TYPE_MISMATCH_MESSAGE = "URL parameter has type mismatch";
	public static String JSON_TYPE_MISMATCH_MESSAGE = "JSON contains field with type mismatch";

	@ExceptionHandler(NotFoundException.class)
	ResponseEntity<String> notFoundHandler(NotFoundException e) {
		return returnResponse(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	 ResponseEntity<String> returnResponse(String message, HttpStatus status) {
		log.error(message);
		return ResponseEntity.status(status).body(message);
	}

	@ExceptionHandler(IllegalStateException.class)
	ResponseEntity<String> badRequestHandler(IllegalStateException e) {
		return returnResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<String> methodArgumentNotValidHandler(MethodArgumentNotValidException e) {
		String message = e.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
				.collect(Collectors.joining(";"));
		return returnResponse(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HandlerMethodValidationException.class)
	ResponseEntity<String> methodValidationHandler(HandlerMethodValidationException e) {
		String message = e.getAllErrors().stream().map(MessageSourceResolvable::getDefaultMessage)
				.collect(Collectors.joining(";"));

		return returnResponse(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	ResponseEntity<String> methodArgumentTypeMismatch(MethodArgumentTypeMismatchException e) {
		String message = TYPE_MISMATCH_MESSAGE;

		return returnResponse(message, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(HttpMessageNotReadableException.class)
	ResponseEntity<String> jsonFieldTypeMismatchException(HttpMessageNotReadableException e) {
		String message = JSON_TYPE_MISMATCH_MESSAGE;

		return returnResponse(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AuthenticationException.class)
	ResponseEntity<String> authenticationExceptionHandler(AuthenticationException e) {
		return returnResponse(e.getMessage(), HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler(TransactionSystemException.class)
	ResponseEntity<String> transactionSystemExceptionHandler(TransactionSystemException e) {
		return returnResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(DataAccessException.class)
	ResponseEntity<String> dataAccessSystemExceptionHandler(DataAccessException e) {
		return returnResponse(e.getMessage(), HttpStatus.NOT_FOUND);
	}
}

