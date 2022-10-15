package com.tusharumredkar.customerservice.handlers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tusharumredkar.customerservice.exception.CustomerServiceException;
import com.tusharumredkar.customerservice.model.ErrorResponseBean;

@ControllerAdvice
public class CookingServiceExceptionHandler {

	@ExceptionHandler({ CustomerServiceException.class })
	public ResponseEntity<Object> handleCookingServiceException(CustomerServiceException cse) {
		ErrorResponseBean response = new ErrorResponseBean(cse.getLocalizedMessage());
		return new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
