package entity_jpa_demo.example.demo.UserServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity_jpa_demo.example.demo.dao.AddressDao;
import entity_jpa_demo.example.demo.dao.UserDao;
import entity_jpa_demo.example.demo.entity.Address;
import entity_jpa_demo.example.demo.entity.UserEntity;
import entity_jpa_demo.example.demo.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserDao userdao;
	@Autowired
	AddressDao addressdao;

	@Override
	public UserEntity findByUsername(String userId) {
		UserEntity userentity=userdao.findByUsername(userId);
		
		return userentity;//returning that one object with username as given
	}

	@Override
	public UserEntity save(String username,String password, String address1, 
			String address2, String landmark, 
			String city, int pincode, String state, 
			String country, int mobileNumber) {
		UserEntity userentity = new UserEntity();
		userentity.setUsername(username);
		userentity.setPassword(password);
		
		Address address=new Address();
		address.setAddressLine1(address1);
		address.setAddressLine2(address2);
		address.setLandmark(landmark);
		address.setCity(city);
		address.setPincode(pincode);
		address.setState(state);
		address.setCountry(country);
		address.setMobileNumber(mobileNumber);
		addressdao.save(address);
		
		
		userentity.setAddress(address);
		
		userdao.save(userentity);
		return userentity;//returning after setting and saving
	}

	@Override
	public List<UserEntity> findByAddressState(String state) {
		List<UserEntity>statewiseuser=userdao.findByAddressState(state);
		return statewiseuser;
	}

}
