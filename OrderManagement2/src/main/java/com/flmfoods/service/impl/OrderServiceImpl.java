package com.flmfoods.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flmfoods.dao.ItemRepository;
import com.flmfoods.dao.OrderRepository;
import com.flmfoods.dto.OrderItemRequestDTO;
import com.flmfoods.dto.OrderItemResponseDTO;
import com.flmfoods.dto.OrderResponseDTO;
import com.flmfoods.dto.PlaceOrderRequestDTO;
import com.flmfoods.exception.ItemNotFoundException;
import com.flmfoods.model.Item;
import com.flmfoods.model.Order;
import com.flmfoods.model.OrderItem;
import com.flmfoods.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public OrderResponseDTO placeOrder(PlaceOrderRequestDTO orderRequestDTO) {
		
		
		List<OrderItemRequestDTO> orderItemRequestList = orderRequestDTO.getOrderItemRequestList();
		
		Order order = new Order();
		List<OrderItem> orderItemsList = new ArrayList<OrderItem>();
		
		double finalOrderPrice=0;
		
		for(OrderItemRequestDTO orderItemDTO : orderItemRequestList) {
			
			
			Item item = itemRepository.findById(orderItemDTO.getItemId()).orElseThrow(()-> new ItemNotFoundException("Item not found with Id"+orderItemDTO.getItemId()));
			
			
			OrderItem orderItem = new OrderItem();
			
			if(orderItemDTO.getQuantity()<=item.getStockQuantity())
			{
				orderItem.setItem(item);
				orderItem.setQuantity(orderItemDTO.getQuantity());
				double itemPrice = item.getPrice() * orderItemDTO.getQuantity();
				finalOrderPrice += itemPrice;
				
				orderItem.setPrice(itemPrice);
				orderItem.setOrder(order);
				
				orderItemsList.add(orderItem);
				
				item.setStockQuantity(item.getStockQuantity()-orderItemDTO.getQuantity());
				itemRepository.save(item);
			}
			else
			{
				throw new ItemNotFoundException("Item Qunatity Exceded the StockQuantity for ItemId: "+ orderItemDTO.getItemId());
			}
		}
		
		
		order.setOrderItems(orderItemsList);
		order.setPrice(finalOrderPrice);
		
		Order savedOrder = orderRepository.save(order);
		
		OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
		
		List<OrderItemResponseDTO> orderItemResponseDTOs = new ArrayList<OrderItemResponseDTO>();
		
		List<OrderItem> orderItems = savedOrder.getOrderItems();
		
		for(OrderItem orderItem : orderItems) {
			
			OrderItemResponseDTO orderItemResponseDTO = new OrderItemResponseDTO();
			
			orderItemResponseDTO.setItemName(orderItem.getItem().getItemName());
			orderItemResponseDTO.setQuantity(orderItem.getQuantity());
			orderItemResponseDTO.setPrice(orderItem.getItem().getPrice());
			orderItemResponseDTO.setSubTotal(orderItem.getPrice());
			
			orderItemResponseDTOs.add(orderItemResponseDTO);
		}
		
		orderResponseDTO.setOrderId(order.getOrderId());
		orderResponseDTO.setOrderItemsResponse(orderItemResponseDTOs);
		orderResponseDTO.setTotalPrice(order.getPrice());
		return orderResponseDTO;
	}

}
