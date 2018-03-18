package com.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.service.so.UserSo;
import com.myapp.service.validator.MyValidationException;
import com.myapp.service.validator.UserValidator;

@RestController
public class TestController {

	@Autowired
	private UserValidator userValidator;

	@RequestMapping(value = "/createAccount1", method = RequestMethod.POST)
	public ResponseEntity<Object> createAccount(@RequestBody UserSo userSo, BindingResult bindingResult)
			throws MyValidationException {

		System.out.println("+++++++++ TestController : createAccount ++++++++");
		
		bindingResult.rejectValue("username", "Username is empty");
		
		// userValidator.validate(userSo, bindingResult);
		if(bindingResult.hasErrors()) {
			
			MyValidationException excep = new MyValidationException();
			//excep.setErrorName("Vicky-Error");
			//excep.setErrorMsg("User Name is empty");
			
			// return new ResponseEntity(excep, HttpStatus.OK);
			throw excep;
		}
		
		return new ResponseEntity(userSo, HttpStatus.OK);
	}
}