package com.ecommercial.site.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecommercial.site.entity.Product;
import com.ecommercial.site.service.ProductService;
import com.ecommercial.site.serviceImpl.ProductServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private ObjectMapper mapperObject;
	
	@PostMapping(value="/save-product",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String saveProduct(@RequestParam("files") MultipartFile multipartFile,@RequestParam("data") String productData) throws IOException {
		
		//converting String Into json 
		
		Product product = mapperObject.readValue(productData, Product.class);
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		long size = multipartFile.getSize();
		String category=product.getProductCategory();
		String fileCode = com.ecommercial.site.fileUpload.FileUploadUtil.saveFile(fileName, multipartFile,category);
		product.setFileCode(fileCode);
		productService.addProduct(product);
		return "Done";
	}
	
	@GetMapping("/fetch-products")
	public ResponseEntity<List<Product>> fetchProduct(@RequestParam("data") String catagory){
		List<Product> productList;
		productList=productService.getAllByCatagory(catagory);
		return new ResponseEntity<>(productList,HttpStatus.OK);
	}
	
	@GetMapping("/get-product")
	public ResponseEntity<Product> getProduct(@RequestParam("id") int id)
	{
		Product product=productService.getProductById(id);
		return new ResponseEntity<>(product,HttpStatus.OK);
	}
	
}
//