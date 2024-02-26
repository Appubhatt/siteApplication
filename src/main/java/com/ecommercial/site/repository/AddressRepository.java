package com.ecommercial.site.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommercial.site.entity.Address;
import com.ecommercial.site.entity.User;

public interface AddressRepository extends JpaRepository<Address, Integer>{
	
	@Query(value =  "Select * from user_address where user_user_id= :userId",
			nativeQuery = true)
	List<Address> findAddressByUserId(@Param("userId")int userId);
}
