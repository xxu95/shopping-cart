package com.shopping.service.impl;

import java.util.List;

import com.shopping.dao.IWishListDao;
import com.shopping.dao.impl.WishListDaoImpl;
import com.shopping.entity.WishListItem;
import com.shopping.service.IWishListService;

public class WishListServiceImpl implements IWishListService {

	IWishListDao wishListDaoImpl = new WishListDaoImpl();

	@Override
	public void addToWishList(Integer productId, Integer userId, Integer quantity) {
		WishListItem wishListItem = checkProduct(productId, userId);
		if (wishListItem == null) {
			wishListDaoImpl.addToWishList(productId, userId, quantity);
		} else {
			wishListDaoImpl.updateWishList(productId, userId, wishListItem.getQuantity() + quantity);
		}
	}

	@Override
	public WishListItem checkProduct(Integer productId, Integer userId) {
		return wishListDaoImpl.checkProduct(productId, userId);
	}

	@Override
	public void updateWishList(Integer productId, Integer userId, Integer quantity) {
		wishListDaoImpl.updateWishList(productId, userId, quantity);
	}

	@Override
	public List<WishListItem> selectAllWishListItem(Integer userId) {
		return wishListDaoImpl.selectAllWishListItem(userId);
	}

	@Override
	public void removeCartItem(Integer productId, Integer userId) {
		wishListDaoImpl.removeWishListItem(productId, userId);
	}

	@Override
	public void modifyCartItemQuantity(Integer productId, Integer userId, Integer quantity) {
		wishListDaoImpl.updateWishList(productId, userId, quantity);
	}
}
