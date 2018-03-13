package com.myapp.service.security;

import com.myapp.service.so.UserSo;

public interface UserServiceInterface {

	UserSo findByUsername(String username);
	
	UserSo save(UserSo userSo);
}