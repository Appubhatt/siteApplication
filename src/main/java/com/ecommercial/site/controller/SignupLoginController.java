package com.ecommercial.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommercial.site.entity.User;
import com.ecommercial.site.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@CrossOrigin
public class SignupLoginController {

	@Autowired
	private UserService userService;

	@CrossOrigin(origins = "*", exposedHeaders = { "user-name", "user-id", "role" })
	@PostMapping("user-register")
	public ResponseEntity<String> addUser(@RequestBody User user, HttpSession session) {

		long ans = userService.checkEmailAndRole(user.getEmail(), user.getRole());
		System.out.println(ans);
		if (ans == 0) {
			User saveUser = userService.saveUserInDB(user);
			HttpHeaders responseHeaders = new HttpHeaders();
			session.setAttribute("user", saveUser);
			responseHeaders.set("user-name", saveUser.getName());
			responseHeaders.set("user-id", "" + saveUser.getUserId());
			responseHeaders.set("role", saveUser.getRole());
			return ResponseEntity.ok().headers(responseHeaders).body("Welcome " + saveUser.getName());
		} else {
			return new ResponseEntity<String>("User already Exits", HttpStatus.BAD_REQUEST);
		}
	}

	@CrossOrigin(origins = "*", exposedHeaders = { "user-name", "user-id", "role" })
	@PostMapping("user-login")
	public ResponseEntity<String> userLogin(@RequestBody User user, HttpSession session) {
		User userData = userService.findByEmailAndPasswordAndRole(user.getEmail(), user.getPassword(), user.getRole());
		System.out.println(userData);
		if (userData == null) {
			return new ResponseEntity<String>("User Not Found", HttpStatus.BAD_REQUEST);

		} else {

			HttpHeaders responseHeaders = new HttpHeaders();
			session.setAttribute("user", userData);

			responseHeaders.set("user-name", userData.getName());
			responseHeaders.set("user-id", "" + userData.getUserId());
			responseHeaders.set("role", userData.getRole());
			// return new ResponseEntity<String>("Welcome "+userData.getName(),
			// HttpStatus.OK);
			return ResponseEntity.ok().headers(responseHeaders).body("Welcome " + userData.getName());
		}
	}

	@CrossOrigin(origins = "*")
	@GetMapping("logout")
	public ResponseEntity<String> userLogout(@RequestParam("id") int id, HttpSession session) {
		User user = userService.findById(id);
		session.invalidate();
		return new ResponseEntity<String>("User already Exits", HttpStatus.ACCEPTED);
	}
}
