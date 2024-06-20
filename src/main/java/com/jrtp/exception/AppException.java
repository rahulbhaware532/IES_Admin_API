package com.jrtp.exception;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AppException extends RuntimeException {

	private String exCode;

	private String exDesc;

	private LocalDateTime exDate;
}
