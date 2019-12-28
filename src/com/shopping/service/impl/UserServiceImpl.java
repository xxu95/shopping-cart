package com.shopping.service.impl;

import com.shopping.dao.IUserDao;
import com.shopping.dao.impl.UserDaoImpl;
import com.shopping.entity.User;
import com.shopping.service.IUserService;

public class UserServiceImpl implements IUserService {

	IUserDao userDaoImpl = new UserDaoImpl();

	@Override
	public boolean checkUserName(String userName) {
		return userDaoImpl.checkUserName(userName);
	}

	@Override
	public void addUser(String userName, String password) {
		userDaoImpl.addUser(userName, password);
	}

	@Override
	public User checkUser(String userName, String password) {
		return userDaoImpl.checkUser(userName, password);
	}

	public static void main(String[] args) {
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		System.out.println(userServiceImpl.checkUserName("xxu95"));
		System.out.println(userServiceImpl.checkUserName("sssss"));
		System.out.println(userServiceImpl.checkUser("xxu95","123123"));
		System.out.println(userServiceImpl.checkUser("xxu95","123"));
	}
}
