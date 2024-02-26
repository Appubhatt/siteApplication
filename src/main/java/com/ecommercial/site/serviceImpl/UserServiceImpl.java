package com.ecommercial.site.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommercial.site.entity.User;
import com.ecommercial.site.repository.UserRepository;
import com.ecommercial.site.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User saveUserInDB(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

//	@Override
//	public long checkEmail(String email,String role) {
//		// TODO Auto-generated method stub
//		return userRepository.countByEmail(email);
//	}
	@Override
	public long checkEmailAndRole(String email, String role) {
		// TODO Auto-generated method stub
		return userRepository.countByEmailAndRole(email, role);
	}

	@Override
	public User findByEmailAndPasswordAndRole(String email, String password, String role) {
		// TODO Auto-generated method stub
		return userRepository.findByEmailAndPasswordAndRole(email, password, role);
	}

	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		Optional<User> user= userRepository.findById(id); 
		return user.get();
	}

	@Override
	public User updateUserById(int id, User user) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public User updateOrderById(int id, User user) {
		// TODO Auto-generated method stub
		Optional<User>userOptional= userRepository.findById(id);
		if(userOptional.isPresent()) {
			User userOP=userOptional.get();
			userOP.setUserOrderList(user.getUserOrderList());
		}
		return null;
	}

}
