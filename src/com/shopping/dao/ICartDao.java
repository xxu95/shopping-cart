package com.shopping.dao;

import java.util.List;

import com.shopping.entity.CartItem;

public interface ICartDao {

	// add
	void addItem(Integer productId, Integer userId, Integer quantity);
	
	// delete
	void removeItem(Integer productId, Integer userId);
	
	// update
	void updateItem(Integer productId, Integer userId, Integer quantity);
	
	// select
	CartItem selectItem(Integer productId, Integer userId);

	List<CartItem> selectAllItems(Integer userId);
}
