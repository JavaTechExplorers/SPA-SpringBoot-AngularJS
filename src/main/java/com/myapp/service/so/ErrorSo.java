package com.myapp.service.so;

import java.util.HashMap;
import java.util.Map;

public class ErrorSo {

	private String errorsFound;
	private Map<String, String> errorMessagesMap = new HashMap<>();

	public String getErrorsFound() {
		return errorsFound;
	}

	public void setErrorsFound(String errorsFound) {
		this.errorsFound = errorsFound;
	}

	public Map<String, String> getErrorMessagesMap() {
		return errorMessagesMap;
	}

	public void setErrorMessagesMap(Map<String, String> errorMessagesMap) {
		this.errorMessagesMap = errorMessagesMap;
	}

}
