package com.flmfoods.service;

import java.util.List;

import com.flmfoods.dto.AddItemRequestDTO;
import com.flmfoods.dto.ItemResponseDTO;
import com.flmfoods.dto.UpdateItemRequestDTO;
import com.flmfoods.dto.UpdateRatingDTO;

public interface ItemService {
	
	public ItemResponseDTO addItem(AddItemRequestDTO addItemRequestDTO);
	
	public List<ItemResponseDTO> getAllItems();
	
	public ItemResponseDTO getItemById(int id);
	
	public List<ItemResponseDTO> getItemsByDiscount(Double discount);

	public String deleteItem(int id);
	
	public ItemResponseDTO updateItem(int id, UpdateItemRequestDTO updateItemRequestDTO);
	
	public ItemResponseDTO updateRating(UpdateRatingDTO updateRatingDTO);
}
