package com.appdevelopers.app.ws.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionalHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(exception = Exception.class)
	public ResponseEntity<ErrorMessage> handleAnyException(Exception ex, WebRequest request) {

		ErrorMessage errorMessage = new ErrorMessage(LocalDateTime.now(), ex.getLocalizedMessage().toString());
		return new ResponseEntity<ErrorMessage>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(exception = {NullPointerException.class,UserServiceException.class})
	public ResponseEntity<Object> handleSpecificException(Exception ex, WebRequest request) {

		String localizedMessge= ex.getLocalizedMessage();
		
		if(localizedMessge ==null) localizedMessge=ex.toString();
		ErrorMessage errorMessage = new ErrorMessage(LocalDateTime.now(), ex.getLocalizedMessage().toString());
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
