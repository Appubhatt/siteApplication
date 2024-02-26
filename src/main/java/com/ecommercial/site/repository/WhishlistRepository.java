package com.ecommercial.site.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommercial.site.entity.Product;
import com.ecommercial.site.entity.Whishlist;

import jakarta.transaction.Transactional;

@Repository
public interface WhishlistRepository extends JpaRepository<Whishlist, Integer>{
	
	@Transactional
	public void deleteByUserIdAndProductId(int userId,int productId);
}
