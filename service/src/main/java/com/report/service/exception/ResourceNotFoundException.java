package com.report.service.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Slf4j
public class ResourceNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String message) {
		super(message);
		log.error(message);
	}
	
	public ResourceNotFoundException(String message, Throwable throwable) {
		super(message, throwable);
		log.error(message, throwable);
	}
}
