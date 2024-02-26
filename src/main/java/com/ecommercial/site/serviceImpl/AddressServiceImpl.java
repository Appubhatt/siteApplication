package com.ecommercial.site.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommercial.site.entity.Address;
import com.ecommercial.site.entity.User;
import com.ecommercial.site.repository.AddressRepository;
import com.ecommercial.site.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService{

	@Autowired
	private AddressRepository repository;
	@Override
	public void saveAddress(Address address) {
		// TODO Auto-generated method stub
		repository.save(address);
	}
	@Override
	public List<Address> fetchAddress(int userId) {
		// TODO Auto-generated method stub
		
		return repository.findAddressByUserId(userId);
	}
	@Override
	public Address findById(int id) {
		// TODO Auto-generated method stub
		Optional<Address> address=repository.findById(id);
		return address.get();
	}
	@Override
	public Address updateOrderById(int id, Address address) {
		// TODO Auto-generated method stub
		Optional<Address> optional=repository.findById(id);
		if(optional.isPresent()) {
			Address addOpt=optional.get();
			addOpt.setAddressOrderList(address.getAddressOrderList());
			repository.save(addOpt);
		}
		return optional.get();
	}
	
}
