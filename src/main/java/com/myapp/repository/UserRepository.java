package com.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.entity.SysUser;

public interface UserRepository extends JpaRepository<SysUser, Integer> {
	SysUser findByUserName(String userName);
	
	SysUser findByMailId(String mailId);
}