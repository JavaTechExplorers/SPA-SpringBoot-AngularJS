package com.myapp.service.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.myapp.service.security.UserServiceInterface;
import com.myapp.service.so.UserSo;

@Component
public class UserValidator implements Validator {

	@Autowired
	private UserServiceInterface userServiceInterface;

	@Override
	public boolean supports(Class<?> clazz) {
		return UserSo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		if (target == null) {
			errors.reject("UserSO object is empty");
		}

		UserSo userSo = (UserSo) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Username cannot be empty");

		if (userServiceInterface.findByUsername(userSo.getUsername()) != null) {
			errors.rejectValue("username", "Username already exist");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Password cannot be empty");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "First name cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "Last name cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mailId", "Mail id cannot be empty");
	}
}