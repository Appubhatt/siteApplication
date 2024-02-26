package com.ecommercial.site.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommercial.site.entity.Product;
import com.ecommercial.site.entity.Whishlist;
import com.ecommercial.site.repository.WhishlistRepository;
import com.ecommercial.site.service.WhishlistService;

@Service
public class WhishlistServiceImpl implements WhishlistService{

	@Autowired
	private WhishlistRepository whishlistRepository;
	@Override
	public void setWhishlistData(Whishlist whishlist) {
		// TODO Auto-generated method stub
		whishlistRepository.save(whishlist);
	}
	@Override
	public void deleteByUserIdAndProductId(int userId, int productId) {
		// TODO Auto-generated method stub
		whishlistRepository.deleteByUserIdAndProductId(userId, productId);
	}
	
	

}
