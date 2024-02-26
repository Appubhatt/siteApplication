package com.ecommercial.site.service;

import java.util.List;


import com.ecommercial.site.entity.Whishlist;

public interface WhishlistService {

	void setWhishlistData(Whishlist whishlist);
	void deleteByUserIdAndProductId(int userId,int productId);
}
