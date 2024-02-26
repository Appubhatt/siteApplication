package com.ecommercial.site.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Whishlist implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6571079987977018778L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="whishlist_id")
	private int whishlistId;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="product_id")
	private int productId;
}
