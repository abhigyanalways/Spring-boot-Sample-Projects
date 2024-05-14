package entity_jpa_demo.example.demo.service;

import java.util.List;

import entity_jpa_demo.example.demo.entity.UserEntity;

public interface UserService {
	
	public UserEntity findByUsername(String username);
	
	public UserEntity save
	(String username,String password, String address1, 
			String address2, String landmark, 
			String city, int pincode, String state, 
			String country, int mobileNumber);

	public List<UserEntity> findByAddressState(String state);

}
