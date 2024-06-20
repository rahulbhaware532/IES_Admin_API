package com.jrtp.exception;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptioHnandler {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(AppExceptioHnandler.class);

	@ExceptionHandler(value = AppException.class)
	public ResponseEntity<AppException> handleException(String exMsg) {

		logger.error("Exception occured" + exMsg);

		AppException ex = new AppException();
		ex.setExCode("EX0003");
		ex.setExDesc(exMsg);
		ex.setExDate(LocalDateTime.now());

		return new ResponseEntity<AppException>(ex, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
