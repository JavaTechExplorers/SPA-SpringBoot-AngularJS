package com.myapp.service.validator;

public class MyValidationException extends Exception {

	private String errorName;
	private String errorMsg;

	public MyValidationException() {
		super();
	}

	public String getErrorName() {
		return errorName;
	}

	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}