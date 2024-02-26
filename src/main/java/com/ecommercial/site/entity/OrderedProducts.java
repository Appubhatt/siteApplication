package com.ecommercial.site.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderedProducts {

	private int productid;
	
	private int price;
	
	private int qnt;
	
	private String fileCode;
	
	private String time;
	
	private String productName;
	
	private String shortDesc;
	
	private int userId;
	
}
