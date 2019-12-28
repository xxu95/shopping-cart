package com.shopping.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.shopping.dao.ICartDao;
import com.shopping.entity.CartItem;
import com.shopping.entity.Product;
import com.shopping.entity.User;
import com.shopping.util.HibernateUtil;

public class CartDaoImpl implements ICartDao {
	
	@Override
	public void addItem(Integer productId, Integer userId, Integer quantity) {
		Session session = null;
		Transaction transaction = null;
		try {
			// begin transaction
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();
			
			// get user by userId
			User user = session.get(User.class, userId);
			Product product = session.get(Product.class, productId);
			
			// create item
			CartItem theCartItem = new CartItem(product, quantity);
			
			// add item to user
			user.addCartItems(theCartItem);
			
			// save user
			session.update(user);
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
		} finally {
			if (session != null)
				session.close();
		}
	}

	@Override
	public void removeItem(Integer productId, Integer userId) {
		Session session = null;
		Transaction transaction = null;
		
		try {
			// begin transaction
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();
			
			session.createQuery("DELETE FROM CartItem c"
					+ " WHERE c.product.productId=?"
					+ " AND c.user.id=?")
				   .setParameter(0, productId)
				   .setParameter(1, userId)
				   .executeUpdate();
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null)
				transaction.rollback();
		} finally {
			if (session != null)
				session.close();
		}
	}
	
	@Override
	public void updateItem(Integer productId, Integer userId, Integer quantity) {
		Session session = null;
		Transaction transaction = null;
		try {
			// begin transaction
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();
			
			// get the cart item
			List<CartItem> cartItems = session.createQuery("from CartItem c"
					+ " WHERE c.product.productId=?"
					+ " AND c.user.id=?", CartItem.class)
				   .setParameter(0, productId)
				   .setParameter(1, userId)
				   .list();
			
			if (!cartItems.isEmpty()) { 
				CartItem theCartItem = cartItems.get(0);
				
				// set the quantity
				theCartItem.setQuantity(quantity);
				// update the cart item
				session.update(theCartItem);
			}
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
		} finally {
			if (session != null)
				session.close();
		}
	}

	@Override
	public CartItem selectItem(Integer productId, Integer userId) {
		Session session = null;
		Transaction transaction = null;
		CartItem theCartItem = null;
		try {
			// begin transaction
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();
			
			List<CartItem> cartItems = session.createQuery("from CartItem c"
					+ " WHERE c.product.productId=?"
					+ " AND c.user.id=?", CartItem.class)
				   .setParameter(0, productId)
				   .setParameter(1, userId)
				   .list();
			if (!cartItems.isEmpty()) { 
				theCartItem = cartItems.get(0);
			}
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
		} finally {
			if (session != null)
				session.close();
		}
		return theCartItem;
	}

	@Override
	public List<CartItem> selectAllItems(Integer userId) { 
		Session session = null;
		Transaction transaction = null;
		List<CartItem> cartItems = null;
		try {
			// begin transaction
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();
			
			// get the user and use join fetch to fetch the lazy loading
			List<User> users = session.createQuery("SELECT u FROM User u"
												+ " JOIN FETCH u.cartItems"
												+ " WHERE u.id=:uid", User.class)
									 .setParameter("uid", userId)
									 .list();
			
			// get the cart items list
			if (!users.isEmpty()) { 
				cartItems = users.get(0).getCartItems();
			}
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
		} finally {
			if (session != null)
				session.close();
		}
		return cartItems;
	}
}
