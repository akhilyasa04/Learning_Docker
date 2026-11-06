package com.flmfoods.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponseDTO {
	
	private int itemId;
	
	private String itemName;
	
	private double price;
	
	private int stockQuantity;
	
	private double rating;
	
	private double discount;

}
