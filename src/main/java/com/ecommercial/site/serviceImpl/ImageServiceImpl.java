package com.ecommercial.site.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommercial.site.entity.SaveImage;
import com.ecommercial.site.repository.SaveImageRepository;
import com.ecommercial.site.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService{

	@Autowired
	SaveImageRepository imageRepository;
	@Override
	public SaveImage imageSave(SaveImage image) {
		// TODO Auto-generated method stub
		
		return imageRepository.save(image);
	}

}
