package com.shopping.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.shopping.dao.IWishListDao;
import com.shopping.entity.Product;
import com.shopping.entity.User;
import com.shopping.entity.WishListItem;
import com.shopping.util.HibernateUtil;

public class WishListDaoImpl implements IWishListDao {

	@Override
	public void addToWishList(Integer productId, Integer userId, Integer quantity) {
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
			WishListItem theWishListItem = new WishListItem(product, quantity);
			
			// add item to user
			user.addWishListItem(theWishListItem);
			
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
	public WishListItem checkProduct(Integer productId, Integer userId) {
		Session session = null;
		Transaction transaction = null;
		WishListItem theWishListItem = null;
		try {
			// begin transaction
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();
			
			List<WishListItem> wishListItems = session.createQuery("from WishListItem w"
																 + " WHERE w.product.productId=?"
																 + " AND w.user.id=?", WishListItem.class)
													  .setParameter(0, productId)
													  .setParameter(1, userId)
													  .list();
			if (!wishListItems.isEmpty()) {
				theWishListItem = wishListItems.get(0);
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
		return theWishListItem;
	}

	@Override
	public void updateWishList(Integer productId, Integer userId, Integer quantity) {
		Session session = null;
		Transaction transaction = null;
		try {
			// begin transaction
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();
			
			List<WishListItem> wishListItems = session.createQuery("from WishListItem w"
																 + " WHERE w.product.productId=?"
																 + " AND w.user.id=?", WishListItem.class)
													  .setParameter(0, productId)
													  .setParameter(1, userId)
													  .list();
			if (!wishListItems.isEmpty()) {
				WishListItem theWishListItem = wishListItems.get(0);
				theWishListItem.setQuantity(quantity);
				session.update(theWishListItem);
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
	public List<WishListItem> selectAllWishListItem(Integer userId) {
		Session session = null;
		Transaction transaction = null;
		List<WishListItem> wishListItems = null;
		try {
			// begin transaction
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();
			
			// get the user and use join fetch to fetch the lazy loading
			List<User> users = session.createQuery("SELECT u FROM User u"
												+ " JOIN FETCH u.wishListItems"
												+ " WHERE u.id=:uid", User.class)
									 .setParameter("uid", userId)
									 .list();
			
			// get the cart items list
			if (!users.isEmpty()) { 
				wishListItems = users.get(0).getWishListItems();
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
		return wishListItems;
	}

	@Override
	public void removeWishListItem(Integer productId, Integer userId) {
		Session session = null;
		Transaction transaction = null;
		
		try {
			// begin transaction
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();
			
			session.createQuery("DELETE FROM WishListItem w"
					+ " WHERE w.product.productId=?"
					+ " AND w.user.id=?")
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

	public static void main(String[] args) {
//		new WishListDaoImpl().addToWishList(2, 2, 3);
		new WishListDaoImpl().removeWishListItem(2, 2);
		System.out.println(new WishListDaoImpl().selectAllWishListItem(2));
	}
}
