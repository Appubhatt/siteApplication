package com.ecommercial.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommercial.site.entity.User;

import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Integer>{
	@Transactional
	long countByEmailAndRole(String email,String role);
	@Transactional
	User findByEmailAndPasswordAndRole(String email,String password,String role);
}
