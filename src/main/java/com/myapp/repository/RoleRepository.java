package com.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

}
