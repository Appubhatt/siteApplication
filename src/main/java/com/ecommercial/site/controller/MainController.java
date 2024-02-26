package com.ecommercial.site.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@CrossOrigin
public class MainController {

	@GetMapping("/")
	public String indexPage() {
		return "Html/index.html";
	}
//	@RequestMapping("/")
//	public ModelAndView index() {
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("Html/index.html");
//		return mav;
//	}
	
}
