package com.flmfoods.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flmfoods.dao.ItemRepository;
import com.flmfoods.dto.AddItemRequestDTO;
import com.flmfoods.dto.ItemResponseDTO;
import com.flmfoods.dto.UpdateItemRequestDTO;
import com.flmfoods.dto.UpdateRatingDTO;
import com.flmfoods.exception.InvalidRatingException;
import com.flmfoods.exception.ItemNotFoundException;
import com.flmfoods.model.Item;
import com.flmfoods.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	ItemRepository itemRepository;

	@Override
	public ItemResponseDTO addItem(AddItemRequestDTO addItemRequestDTO) {
		Item item = new Item();
		
		item.setItemName(addItemRequestDTO.getItemName());
		item.setPrice(addItemRequestDTO.getPrice());
		item.setStockQuantity(addItemRequestDTO.getStockQuantity());
		item.setDiscount(addItemRequestDTO.getDiscount());
		item.setRating(0);
		
		Item savedItem = itemRepository.save(item);
		
		ItemResponseDTO response = new ItemResponseDTO();
		BeanUtils.copyProperties(savedItem, response);
		return response;
	}

	@Override
	public List<ItemResponseDTO> getAllItems() {
		List<Item> allItems = itemRepository.findAll();
		
		
		List<ItemResponseDTO> list = allItems
									.stream()
									.map(item -> {
										ItemResponseDTO dto = new ItemResponseDTO();
										BeanUtils.copyProperties(item, dto);
										return dto;
									})
									.toList();	
			
		return list;
	}

	@Override
	public ItemResponseDTO getItemById(int id) {
		Item item = itemRepository.findById(id).orElseThrow(()-> new ItemNotFoundException("Item not found with id: "+ id));
		
		ItemResponseDTO dto = new ItemResponseDTO();
		BeanUtils.copyProperties(item, dto);
		return dto;
	}

	@Override
	public List<ItemResponseDTO> getItemsByDiscount(Double discount) {
		
		List<Item> itemsList = itemRepository.findAll();
		
		if(discount == null) {
			return itemsList
					.stream()
					.map(item -> {
						ItemResponseDTO dto = new ItemResponseDTO();
						BeanUtils.copyProperties(item, dto);
						return dto;
					})
					.toList();	
		}
		
		List<ItemResponseDTO> dtoList = itemsList
		.stream()
		.filter(item -> item.getDiscount() > discount)
		.map(item -> {
			ItemResponseDTO dto = new ItemResponseDTO();
			BeanUtils.copyProperties(item, dto);
			return dto;
		})
		.toList();
		
		return dtoList;
	}

	@Override
	public String deleteItem(int id) {
		itemRepository.deleteById(id);
		return "Deleted Item With Id : "+id;
	}

	@Override
	public ItemResponseDTO updateItem(int id, UpdateItemRequestDTO updateItemRequestDTO) {
		
		
		Item item = itemRepository.findById(id).get();
		
		BeanUtils.copyProperties(updateItemRequestDTO, item);
		
		Item savedItem = itemRepository.save(item);
		
		ItemResponseDTO itemResponseDTO = new ItemResponseDTO();
		BeanUtils.copyProperties(savedItem, itemResponseDTO);
		return itemResponseDTO;
	}

	@Override
	public ItemResponseDTO updateRating(UpdateRatingDTO updateRatingDTO) {
		
		
		
		if(updateRatingDTO.getRating()>5.0 || updateRatingDTO.getRating()<0.0)
		{
			throw new InvalidRatingException("Invalid Rating...");
		}
		
			ItemResponseDTO itemResponseDTO = new ItemResponseDTO();
	
			int itemId = itemRepository.updateRating(updateRatingDTO.getItemId(), updateRatingDTO.getRating());
			
			Item response = itemRepository.findById(updateRatingDTO.getItemId()).orElseThrow(() -> new ItemNotFoundException("Item not Found with Id: "+updateRatingDTO.getItemId()));
			
			
			BeanUtils.copyProperties(response, itemResponseDTO);
			
		return itemResponseDTO;
	}
	
}
