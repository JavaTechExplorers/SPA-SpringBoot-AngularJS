package com.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.entity.SysRole;

public interface RoleRepository extends JpaRepository<SysRole, Integer> {

}
