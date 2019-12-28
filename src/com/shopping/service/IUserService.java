package com.shopping.service;

import com.shopping.entity.User;

public interface IUserService {

	boolean checkUserName(String userName);
	
	void addUser(String userName, String password);
	
	User checkUser(String userName, String password);
}
