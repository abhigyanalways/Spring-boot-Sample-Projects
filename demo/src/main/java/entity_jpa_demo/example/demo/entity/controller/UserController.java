package entity_jpa_demo.example.demo.entity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.qos.logback.core.model.Model;
import entity_jpa_demo.example.demo.entity.UserEntity;
import entity_jpa_demo.example.demo.service.UserService;

@SuppressWarnings("unused")
@Controller
public class UserController {
	@Autowired
	UserService userservice;

	@GetMapping("/login2")
	public String loginpage(ModelMap model, @RequestParam(required = false) String successMSG) {
		if (successMSG != null) {
			model.put("successMSG", successMSG);
		}
		return "login2";
	}

	@PostMapping("/welcome")//default url remains login2, changed in action attribute	
	public String welcomepage(ModelMap model, @RequestParam("username") String username,
			@RequestParam("password") String password) {
		UserEntity userentity = userservice.findByUsername(username);
		if (userentity.getPassword() != null && userentity.getPassword().equals(password)) {
			model.put("successMSG", "logged in successfully!");
			model.put("user", username);
			return "welcome";
		} else {
			model.put("errorMSG", "enter correct credentials");
			return "login2";
		}

	}

	@GetMapping("/register")
	public String registrationpage() {
		return "register";
	}

	@PostMapping("/register")
	public String registeruser(ModelMap model, @RequestParam("username") String userId,
			@RequestParam("password") String password, @RequestParam("addressLine1") String address1,
			@RequestParam("addressLine2") String address2, @RequestParam("landmark") String landmark,
			@RequestParam("city") String city, @RequestParam("pincode") int pincode,
			@RequestParam("state") String state, @RequestParam("country") String country,
			@RequestParam("mobileNumber") int mobileNumber) {
		userservice.save(userId, password, address1, address2, landmark, city, pincode, state, country, mobileNumber);
		// model.put("successMSG", "user registered!");

		return "redirect:/login2?successMSG=user+registered!!!%21";

	}

	@GetMapping("/info")
	public String infopage() {

		return "info";
	}

	@PostMapping("/info")
	public String checkadmin(ModelMap model, @RequestParam("username") String username,
			@RequestParam("password") String password) {
		UserEntity userentity = userservice.findByUsername(username);
		if (userentity.getPassword() != null && userentity.getUsername().equals("admin")
				&& userentity.getPassword().equals(password)) {
			model.put("successMSG", "HELLO admin , this page will show admin specific information!");
			return "/adminaccess";
		} else {
			model.put("errorMSG", "enter correct credentials for admin");
			return "/info";
		}

	}

	@PostMapping("/adminaccess")//these paths have something to do with th pages they are associated with 
	public String getUserByState(ModelMap model, @RequestParam("state") String state) {
		model.put("stateName", state);
		List<UserEntity> statewiseusers = userservice.findByAddressState(state);
		model.put("userbystate", statewiseusers);
		return "data";

	}
}
