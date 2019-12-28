package com.shopping.dao;

import com.shopping.entity.User;

public interface IUserDao {

	boolean checkUserName(String userName);
	
	void addUser(String userName, String password);

	User checkUser(String userName, String password);
}
