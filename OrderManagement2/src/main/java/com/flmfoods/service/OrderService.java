package com.flmfoods.service;

import com.flmfoods.dto.OrderResponseDTO;
import com.flmfoods.dto.PlaceOrderRequestDTO;

public interface OrderService {
	
	public OrderResponseDTO placeOrder(PlaceOrderRequestDTO orderRequestDTO);

}
