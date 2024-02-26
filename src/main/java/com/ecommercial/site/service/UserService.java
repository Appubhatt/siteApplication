package com.ecommercial.site.service;

import com.ecommercial.site.entity.User;

public interface UserService {

	User saveUserInDB(User user);
	long checkEmailAndRole (String email,String role);
	User findByEmailAndPasswordAndRole(String email,String password,String role);
	User findById(int id);
	User updateUserById(int id,User user);
	User updateOrderById(int id,User user);
}
