package com.myapp.service.security;

import com.myapp.service.so.UserSo;

public interface UserServiceInterface {

	UserSo findByUsername(String userName);
	
	UserSo save(UserSo userSo);
}