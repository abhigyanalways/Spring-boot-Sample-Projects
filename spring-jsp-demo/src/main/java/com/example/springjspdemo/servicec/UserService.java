package com.example.springjspdemo.servicec;

import com.example.springjspdemo.controller.bean.User;

public interface UserService {

	
	public User getUserById(String userId);

	public int insertuser(String username, String password);
	
		
	
}
