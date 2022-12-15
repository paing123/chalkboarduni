package edu.chalkboarduni.uniregistrationsystem.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(Exception.class)
	public String globalException(Exception ex) {
		return "error";
	}
}
