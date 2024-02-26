package com.ecommercial.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecommercial.site.entity.SaveImage;
import com.ecommercial.site.service.ImageService;

@Controller
@CrossOrigin
public class ImageController {

	@Autowired
	private ImageService service;
	
	@PostMapping("save")
	public String saveImage(@RequestBody SaveImage image) {
		service.imageSave(image);
		return "Done"; 
	}
}
