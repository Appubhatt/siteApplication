package com.ecommercial.site.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommercial.site.entity.OrderedProducts;
import com.ecommercial.site.entity.Orders;
import com.ecommercial.site.repository.OrderRepository;
import com.ecommercial.site.service.OrderService;

import jakarta.persistence.criteria.Order;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository repository;
	@Override
	public void saveOrder(Orders order) {
		// TODO Auto-generated method stub
		
		repository.save(order);
	}
	@Override
	public List<Orders> findOrdersByUserId(int userId) {
		// TODO Auto-generated method stub
		return repository.findByUserId(userId);
	}
	@Override
	public List<OrderedProducts> getMyOrders(int userid) {
		// TODO Auto-generated method stub
		return repository.getAllByUserId(userid);
	}

}
