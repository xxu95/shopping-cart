package com.shopping.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.shopping.dao.IProductDao;
import com.shopping.entity.Product;
import com.shopping.util.HibernateUtil;

public class ProductDaoImpl implements IProductDao {

	@Override
	public List<Product> selectProductByCategory(String category) {
		Session session = null;
		Transaction transaction = null;
		List<Product> products = null;
		try {
			// begin transaction
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();
			
			products = session.createQuery("from Product p WHERE p.category=?", Product.class)
							  .setParameter(0, category)
							  .list();
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
		} finally {
			if (session != null)
				session.close();
		}
		return products;
	}

	@Override
	public Product selectProductById(Integer productId) {
		Session session = null;
		Transaction transaction = null;
		Product product = null;
		try {
			// begin transaction
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();
			
			List<Product >products = session.createQuery("from Product p WHERE p.productId=?", Product.class)
							  .setParameter(0, productId)
							  .list();
			
			if (!products.isEmpty()) { 
				product = products.get(0);
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
		return product;
	}
}
