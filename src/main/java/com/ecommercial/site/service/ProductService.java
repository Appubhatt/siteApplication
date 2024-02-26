package com.ecommercial.site.service;

import java.util.List;

import com.ecommercial.site.entity.Product;

public interface ProductService {

	Product addProduct(Product product);
	List<Product> getAllByCatagory(String category);
	Product getProductById(int id);
	List<Product> getWhishlistProducts(int id);
	Product updateOrderById(int id,Product product);
}
