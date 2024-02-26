package com.ecommercial.site.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommercial.site.entity.Address;
import com.ecommercial.site.entity.AddressProductResponse;
import com.ecommercial.site.entity.OrderedProducts;
import com.ecommercial.site.entity.Orders;
import com.ecommercial.site.entity.Product;
import com.ecommercial.site.entity.User;
import com.ecommercial.site.repository.UserRepository;
import com.ecommercial.site.service.AddressService;
import com.ecommercial.site.service.OrderService;
import com.ecommercial.site.service.ProductService;
import com.ecommercial.site.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BuyController {

	@Autowired
	ProductService productService;

	@Autowired
	AddressService addressService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("buyProduct")
	public ResponseEntity<AddressProductResponse> buyProduct(@RequestParam("id") int id,HttpSession session){

		System.out.println(id);
		User user= (User) session.getAttribute("user");
		
		List<Address>addressList= addressService.fetchAddress(user.getUserId());
		Product product=productService.getProductById(id);
		AddressProductResponse response = new AddressProductResponse(addressList, product);
		return new ResponseEntity<AddressProductResponse>(response,HttpStatus.ACCEPTED);
		
	}
	
	@PostMapping("deliver-product")
	public ResponseEntity<String> deliverProduct(@RequestBody Map<String, String>payload){
		System.out.println(payload);
		
		Orders order= new Orders();
		order.setPrice(Integer.parseInt(payload.get("price")));
		order.setQnt(Integer.parseInt(payload.get("qnt")));
		
		LocalDateTime dateTime = LocalDateTime.now();
		
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		order.setTime(dateTime.format(formatDate));
		
		Address addressObj = addressService.findById(Integer.parseInt(payload.get("addressId")));
		
		User userObj = userService.findById(Integer.parseInt(payload.get("userId")));
		
		Product productObj = productService.getProductById(Integer.parseInt(payload.get("productId")));
		
		order.setAddress(addressObj);
		order.setProduct(productObj);
		order.setUser(userObj);
		orderService.saveOrder(order);
		
		List<Orders> orderList=new ArrayList<>();
		orderList.add(order);
		
		addressObj.setAddressOrderList(orderList);
		addressService.updateOrderById(addressObj.getAddressId(), addressObj);
		
		productObj.setProductOrderList(orderList);
		productService.updateOrderById(productObj.getProductId(), productObj);
		
		userObj.setUserOrderList(orderList);
		userService.updateOrderById(userObj.getUserId(), userObj);
		
		return new ResponseEntity<String>("Done", HttpStatus.ACCEPTED);
		
	}
	@GetMapping("myOrder")
	public ResponseEntity<List<OrderedProducts>> myOrderedProducts(@RequestParam("userId") int id){
		//User user = (User)session.getAttribute("user");
		//orderService.findOrdersByUserId(user.getUserId());
		//List<Orders> orderList = orderService.findOrdersByUserId(id);
		List<OrderedProducts> myOrderList = orderService.getMyOrders(id);
		return new ResponseEntity<List<OrderedProducts>>(myOrderList,HttpStatus.ACCEPTED);
		
		//return new ResponseEntity<List<OrderedProducts>>(myOrderList,HttpStatus.ACCEPTED);
		
	}
}
