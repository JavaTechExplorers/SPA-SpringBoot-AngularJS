package com.myapp.service.so;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ErrorSo implements Serializable {

	private boolean isErrorsExists;
	private Map<String, String> errorMessagesMap = new HashMap<>();

	public boolean isErrorsExists() {
		return isErrorsExists;
	}

	public void setErrorsExists(boolean isErrorsExists) {
		this.isErrorsExists = isErrorsExists;
	}

	public Map<String, String> getErrorMessagesMap() {
		return errorMessagesMap;
	}

	public void setErrorMessagesMap(Map<String, String> errorMessagesMap) {
		this.errorMessagesMap = errorMessagesMap;
	}

}
