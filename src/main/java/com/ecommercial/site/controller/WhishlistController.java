package com.ecommercial.site.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommercial.site.entity.Product;
import com.ecommercial.site.entity.Whishlist;
import com.ecommercial.site.repository.WhishlistRepository;
import com.ecommercial.site.service.ProductService;
import com.ecommercial.site.service.WhishlistService;

@Controller
@CrossOrigin
public class WhishlistController {

	@Autowired
	private WhishlistService whishlistService;
	
	@Autowired
	private ProductService productService;

	@PostMapping("setWhishlist")
	public ResponseEntity<String> setWhishlist(@RequestBody Whishlist whishlist){
		whishlistService.setWhishlistData(whishlist);
		return new ResponseEntity<String>("Added",HttpStatus.ACCEPTED);
	}
	@GetMapping("getWhishlist")
	public ResponseEntity<List<Product>> getWhishlist(@RequestParam("userId") int userId){
		List<Product> productList = new ArrayList<>();
		productList = productService.getWhishlistProducts(userId);
		return new ResponseEntity<>(productList,HttpStatus.ACCEPTED);
	}
	@PostMapping("removeFromList")
	public ResponseEntity<String> removeProductFromWhishlist(@RequestBody Whishlist whishlist){
		whishlistService.deleteByUserIdAndProductId(whishlist.getUserId(), whishlist.getProductId());
		return new ResponseEntity<String>("Removed",HttpStatus.ACCEPTED);
	}
}
