package com.myapp.service.security;

import com.myapp.service.so.UserSo;
import com.myapp.service.validator.MyValidationException;

public interface UserServiceInterface {

	UserSo findByUsername(String username);
	
	UserSo save(UserSo userSo) throws MyValidationException;
}