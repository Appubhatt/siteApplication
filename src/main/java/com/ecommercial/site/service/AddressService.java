package com.ecommercial.site.service;

import java.util.List;

import com.ecommercial.site.entity.Address;
import com.ecommercial.site.entity.User;

public interface AddressService {
	void saveAddress(Address address);
	List<Address> fetchAddress(int userId);
	Address findById(int id);
	Address updateOrderById(int id,Address address);
}
