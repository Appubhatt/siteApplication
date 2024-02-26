package com.ecommercial.site.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommercial.site.entity.OrderedProducts;
import com.ecommercial.site.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer>{
	
	@Query(value = "Select * from orders where user_user_id=:userId",
			nativeQuery = true)
	List<Orders> findByUserId(@Param("userId") int userId);
	
	@Query(value = "SELECT product_id,product.price,qnt,file_code,TIME,product_name,short_desc FROM orders INNER JOIN product ON orders.product_product_id = product.product_id WHERE user_user_id= :userId",
			nativeQuery = true)
	List<OrderedProducts> getAllByUserId(@Param("userId") int userId);
}
