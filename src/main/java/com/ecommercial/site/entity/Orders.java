package com.ecommercial.site.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Orders implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7259551744025171669L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	
	@Column
	private int price;
	
	@Column
	private int qnt;
	
	@Column
	private String time;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Product product;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Address address;
	
}
