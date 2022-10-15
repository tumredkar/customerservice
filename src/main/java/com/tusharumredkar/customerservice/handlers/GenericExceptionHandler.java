package com.tusharumredkar.customerservice.handlers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tusharumredkar.customerservice.common.CustomerServiceConstants;
import com.tusharumredkar.customerservice.model.ErrorResponseBean;

@ControllerAdvice
public class GenericExceptionHandler {

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleCookingServiceException() {
		ErrorResponseBean response = new ErrorResponseBean(CustomerServiceConstants.INTERAL_PROCESSING_ERROR);
		return new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
