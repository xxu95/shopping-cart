package com.shopping.service.impl;

import java.util.List;

import com.shopping.dao.ICartDao;
import com.shopping.dao.impl.CartDaoImpl;
import com.shopping.entity.CartItem;
import com.shopping.service.ICartService;

public class CartServiceImpl implements ICartService {

	ICartDao cartDao = new CartDaoImpl();

	@Override
	public void addCartItem(Integer productId, Integer userId, Integer quantity) {
		CartItem cartItem = cartDao.selectItem(productId, userId);
		if (cartItem == null) {
			cartDao.addItem(productId, userId, quantity);
		} else {
			cartDao.updateItem(productId, userId, cartItem.getQuantity() + quantity);
		}
	}

	@Override
	public void removeCartItem(Integer productId, Integer userId) {
		cartDao.removeItem(productId, userId);

	}

	@Override
	public void modifyCartItemQuantity(Integer productId, Integer userId, Integer quantity) {
		cartDao.updateItem(productId, userId, quantity);

	}

	@Override
	public CartItem selectCartItem(Integer productId, Integer userId) {
		return cartDao.selectItem(productId, userId);
	}

	@Override
	public List<CartItem> selectAllCartItem(Integer userId) {
		return cartDao.selectAllItems(userId);
	}

	public static void main(String[] args) {
		CartServiceImpl cartServiceImpl = new CartServiceImpl();
		cartServiceImpl.addCartItem(8, 1, 3);
	}
}
