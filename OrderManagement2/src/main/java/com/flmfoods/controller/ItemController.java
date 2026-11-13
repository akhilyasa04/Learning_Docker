package com.flmfoods.controller;

import com.flmfoods.service.impl.ItemServiceImpl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flmfoods.dto.AddItemRequestDTO;
import com.flmfoods.dto.ItemResponseDTO;
import com.flmfoods.dto.UpdateItemRequestDTO;
import com.flmfoods.dto.UpdateRatingDTO;
import com.flmfoods.service.ItemService;

@RestController
@RequestMapping("/item")
public class ItemController {
	
	private final ItemServiceImpl itemServiceImpl;

	
	@Autowired
	ItemService itemService;

	public ItemController(ItemServiceImpl itemServiceImpl) {
		this.itemServiceImpl = itemServiceImpl;
	}
	
	@PostMapping("/add")
	public ResponseEntity<ItemResponseDTO> addItem(@RequestBody AddItemRequestDTO addItemRequestDTO) {
		ItemResponseDTO itemResponseDTO = itemService.addItem(addItemRequestDTO);
		
		return new ResponseEntity<ItemResponseDTO>(itemResponseDTO, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<ItemResponseDTO>> getAllItems(){
		List<ItemResponseDTO> allItems = itemService.getAllItems();
		
		return new ResponseEntity<List<ItemResponseDTO>>(allItems, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ItemResponseDTO> getItem(@PathVariable int id) {
		ItemResponseDTO itemById = itemService.getItemById(id);
		
		return new ResponseEntity<>(itemById,HttpStatus.OK);
	}
	
	@GetMapping("/byDiscount")
	public List<ItemResponseDTO> getItemsByDiscountGreaterThan(@RequestParam(required = false) Double discount){
		return itemService.getItemsByDiscount(discount);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteItem(@PathVariable int id) {
		return itemService.deleteItem(id);
	}
	
	@PutMapping("/update/{id}")
	public ItemResponseDTO updateItem(@PathVariable int id,@RequestBody UpdateItemRequestDTO updateRequestDTO) {
		return itemService.updateItem(id, updateRequestDTO);
	}
	
	
	@PutMapping("/rating")
	public ItemResponseDTO updateRating(@RequestBody UpdateRatingDTO updateRatingDTO) {
		
		return itemServiceImpl.updateRating(updateRatingDTO);
		
	}
	
}
