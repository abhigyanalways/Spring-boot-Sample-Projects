	package com.example.springjspdemo.servicec.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springjspdemo.controller.bean.User;
import com.example.springjspdemo.dao.UserDao;
import com.example.springjspdemo.servicec.UserService;

@Service
public class UserServiceImpl implements UserService		{
	
	@Autowired
	UserDao userDao;

	@Override
	public User getUserById(String userId) {
		User user= userDao.getUserById(userId);
		
		return user;
	}

	@Override
	public int insertuser(String username, String password) {
		return userDao.insertuser(username,password);
		
	}

}
