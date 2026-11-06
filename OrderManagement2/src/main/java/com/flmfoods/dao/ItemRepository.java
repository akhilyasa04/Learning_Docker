package com.flmfoods.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.flmfoods.dto.UpdateRatingDTO;
import com.flmfoods.model.Item;

import jakarta.transaction.Transactional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>{
	
	@Query("update Item set rating=?2 where itemId=?1")
	@Modifying
	@Transactional
	public int updateRating(int itemId, double rating);

}
