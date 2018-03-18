package com.myapp.service.validator;

import org.springframework.validation.Errors;

public class MyValidationException extends Exception {

	private boolean isErrorsExists;
	private Errors errors;

	public MyValidationException() {
		super();
	}

	public boolean isErrorsExists() {
		return isErrorsExists;
	}

	public void setErrorsExists(boolean isErrorsExists) {
		this.isErrorsExists = isErrorsExists;
	}

	public Errors getErrors() {
		return errors;
	}

	public void setErrors(Errors errors) {
		this.errors = errors;
	}

}