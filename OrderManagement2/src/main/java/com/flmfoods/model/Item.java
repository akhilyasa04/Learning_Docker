package com.flmfoods.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int itemId;
	
	private String itemName;
	
	@Column(nullable = false)
	private double price;
	
	private int stockQuantity;
	
	private double rating;
	
	private double discount;

	public Item(String itemName, double price, int stockQuantity, double rating, double discount) {
		this.itemName = itemName;
		this.price = price;
		this.stockQuantity = stockQuantity;
		this.rating = rating;
		this.discount = discount;
	}

	
}
