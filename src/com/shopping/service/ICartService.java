package com.shopping.service;

import java.util.List;

import com.shopping.entity.CartItem;

public interface ICartService {

	// add
	void addCartItem(Integer productId, Integer userId, Integer quantity);

	// remove
	void removeCartItem(Integer productId, Integer userId);

	// update
	void modifyCartItemQuantity(Integer productId, Integer userId, Integer quantity);

	// select
	CartItem selectCartItem(Integer productId, Integer userId);

	// selectAll
	List<CartItem> selectAllCartItem(Integer userId);

}
