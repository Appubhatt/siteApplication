package com.ecommercial.site.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommercial.site.entity.Product;
import com.ecommercial.site.repository.ProductRepository;
import com.ecommercial.site.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	@Override
	public Product addProduct(Product product) {
		// TODO Auto-generated method stub
		return productRepository.save(product);
	}
	@Override
	public List<Product> getAllByCatagory(String catagory) {
		// TODO Auto-generated method stub
		List<Product> productList;
		productList=productRepository.findAllByProductCategory(catagory);
		return productList;
	}
	@Override
	public Product getProductById(int id) {
		// TODO Auto-generated method stub
		Optional<Product> product=productRepository.findById(id);
		return product.get();
	}
	@Override
	public List<Product> getWhishlistProducts(int id) {
		// TODO Auto-generated method stub
		
		return productRepository.getwhishlistData(id);
	}
	@Override
	public Product updateOrderById(int id, Product product) {
		// TODO Auto-generated method stub
		Optional<Product>optional = productRepository.findById(id);
		if(optional.isPresent()) {
			Product proOpt=optional.get();
			proOpt.setProductOrderList(product.getProductOrderList());
			productRepository.save(proOpt);
		}
		return optional.get();
	}

}
