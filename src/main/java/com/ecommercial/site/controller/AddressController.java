package com.ecommercial.site.controller;

import java.util.Enumeration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecommercial.site.entity.Address;
import com.ecommercial.site.entity.User;
import com.ecommercial.site.service.AddressService;
import com.ecommercial.site.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@CrossOrigin
public class AddressController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AddressService addressService;
	
	@PostMapping("saveAddress")
	public ResponseEntity<String> saveAddress(@RequestBody Address address,HttpServletRequest request)throws Exception{
		
		HttpSession session = request.getSession(false);
		System.out.println("session attributes:"+session.getAttributeNames());
		Enumeration<String> enumeration = session.getAttributeNames();
		System.out.println("Session:");
		while(enumeration.hasMoreElements()) {
			System.out.println("enum:"+enumeration.nextElement());
		}
		User temp=(User) session.getAttribute("user");
		System.out.println(temp.getUserId());
		User user = userService.findById(temp.getUserId());
		user.getUserAddress().add(address);
		address.setUser(user);
		addressService.saveAddress(address);
		return new ResponseEntity<String>("Address has been save",HttpStatus.ACCEPTED);
		
		//return null;
	}
}
