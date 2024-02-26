package com.ecommercial.site.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommercial.site.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	List<Product> findAllByProductCategory(String productCategory);
	@Query(value="SELECT * FROM product WHERE product_id IN (SELECT product_id FROM whishlist WHERE user_id = :id)",
			nativeQuery = true)
	public List<Product> getwhishlistData(@Param("id") int id);
}
