package com.ecommercial.site.service;

import java.util.List;

import com.ecommercial.site.entity.OrderedProducts;
import com.ecommercial.site.entity.Orders;

public interface OrderService {
	void saveOrder(Orders order);
	List<Orders> findOrdersByUserId(int userId);
	
	List<OrderedProducts> getMyOrders(int userid);
}
