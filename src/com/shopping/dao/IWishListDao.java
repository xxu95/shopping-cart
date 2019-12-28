package com.shopping.dao;

import java.util.List;

import com.shopping.entity.WishListItem;

public interface IWishListDao {

	void addToWishList(Integer productId, Integer userId, Integer quantity);

	WishListItem checkProduct(Integer productId, Integer userId);

	void updateWishList(Integer productId, Integer userId, Integer quantity);

	List<WishListItem> selectAllWishListItem(Integer userId);

	void removeWishListItem(Integer productId, Integer userId);	
}
