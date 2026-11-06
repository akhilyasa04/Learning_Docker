package com.flmfoods.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
	
	private int orderId;
	
	private double totalPrice;
	
	private List<OrderItemResponseDTO> orderItemsResponse;

}
