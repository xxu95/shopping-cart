package com.shopping.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.shopping.dao.IUserDao;
import com.shopping.entity.User;
import com.shopping.util.HibernateUtil;

public class UserDaoImpl implements IUserDao {

	@Override
	public boolean checkUserName(String userName) {
		Session session = null;
		Transaction transaction = null;
		boolean isExisted = false;
	
		try {
			// begin transaction
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();
			
			List<User> users = session.createQuery("from User u WHERE u.userName=?", User.class)
				   .setParameter(0, userName)
				   .list();
			
			if (!users.isEmpty()) { 
				isExisted = true;
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
		return isExisted;
	}

	@Override
	public void addUser(String userName, String password) {
		Session session = null;
		Transaction transaction = null;
		try {
			// begin transaction
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();
			
			User newUser = new User(userName, password);
			
			session.persist(newUser);
			
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
	public User checkUser(String userName, String password) {
		Session session = null;
		Transaction transaction = null;
		User theUser = null;
	
		try {
			// begin transaction
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();
			
			List<User> users = session.createQuery("from User u"
												 + " WHERE u.userName=?"
												 + " AND u.password=?", User.class)
									  .setParameter(0, userName)
									  .setParameter(1, password)
									  .list();
			
			if (!users.isEmpty()) { 
				theUser = users.get(0);
			}
			
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
		return theUser;
	}

	
	public static void main(String[] args) {
		System.out.println(new UserDaoImpl().checkUser("xxu95", "123123"));
	}
}
