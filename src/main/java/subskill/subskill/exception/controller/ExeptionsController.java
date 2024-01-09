package subskill.subskill.exception.controller;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import lombok.extern.slf4j.Slf4j;
import subskill.subskill.exception.NotFoundException;

@ControllerAdvice
@Slf4j
public class ExeptionsController {
	public static String TYPE_MISMATCH_MESSAGE = "URL parameter has type mismatch";
	public static String JSON_TYPE_MISMATCH_MESSAGE = "JSON contains field with type mismatch";	

@ExceptionHandler(NotFoundException.class)
ResponseEntity<String> notFoundHandler(NotFoundException e) {
	return returnResponse(e.getMessage(), HttpStatus.NOT_FOUND);
}
private ResponseEntity<String> returnResponse(String message, HttpStatus status) {
	log.error(message);
	return new ResponseEntity<String>(message, status);
}
@ExceptionHandler(IllegalStateException.class)
ResponseEntity<String> badRequestHandler(IllegalStateException e) {
	return returnResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
}
@ExceptionHandler(MethodArgumentNotValidException.class)
ResponseEntity<String> methodArgumentNotValidHandler(MethodArgumentNotValidException e) {
	String message = e.getAllErrors().stream().map(error -> error.getDefaultMessage())
			.collect(Collectors.joining(";"));
	return returnResponse(message, HttpStatus.BAD_REQUEST);
}
@ExceptionHandler(HandlerMethodValidationException.class)
ResponseEntity<String> methodValidationHandler(HandlerMethodValidationException e) {
	String message = e.getAllErrors().stream().map(error -> error.getDefaultMessage())
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
}

