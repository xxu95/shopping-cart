package com.shopping.service;

import java.util.List;

import com.shopping.entity.WishListItem;

public interface IWishListService {

	void addToWishList(Integer productId, Integer userId, Integer quantity);

	WishListItem checkProduct(Integer productId, Integer userId);

	void updateWishList(Integer productId, Integer userId, Integer quantity);

	List<WishListItem> selectAllWishListItem(Integer userId);

	void removeCartItem(Integer productId, Integer userId);

	void modifyCartItemQuantity(Integer productId, Integer userId, Integer quantity);
}
