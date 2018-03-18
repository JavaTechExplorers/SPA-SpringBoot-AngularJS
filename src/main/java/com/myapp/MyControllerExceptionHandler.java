package com.myapp;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.myapp.service.so.ErrorSo;
import com.myapp.service.validator.MyValidationException;

/**
 * Controller used to handle all validation exceptions thrown from Controller
 * methods
 * 
 * @author Vignesh
 */
@ControllerAdvice
public class MyControllerExceptionHandler {

	@ExceptionHandler(MyValidationException.class)
	public ResponseEntity<ErrorSo> handleException(MyValidationException e) {

		System.out.println("******************** MyControllerExceptionHandler *******************");
		ErrorSo errorSo = new ErrorSo();
		errorSo.setErrorsFound("Y");

		Errors errors = e.getErrors();
		if (errors != null && errors.hasErrors()) {

			List<ObjectError> erros = errors.getAllErrors();
			for (ObjectError objectError : erros) {

				if (objectError instanceof FieldError) {

					FieldError fieldError = (FieldError) objectError;
					if (fieldError != null && fieldError.getCodes() != null) {

						errorSo.getErrorMessagesMap().put(fieldError.getField(), fieldError.getCodes()[fieldError.getCodes().length - 1]);
					}
				}

			}
		}

		return new ResponseEntity<ErrorSo>(errorSo, HttpStatus.OK);
	}
}