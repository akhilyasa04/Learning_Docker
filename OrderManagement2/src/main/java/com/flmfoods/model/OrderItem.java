package com.flmfoods.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderItemId;
	
	private double price;
	
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name = "item_id")
	private Item item;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	public OrderItem(double price, int quantity, Item item) {
		super();
		this.price = price;
		this.quantity = quantity;
		this.item = item;
	}

	public OrderItem(double price, int quantity) {
		super();
		this.price = price;
		this.quantity = quantity;
	}
	
}
