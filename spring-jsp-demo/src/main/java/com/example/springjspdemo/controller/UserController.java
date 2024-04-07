package com.example.springjspdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.springjspdemo.controller.bean.User;
import com.example.springjspdemo.servicec.UserService;

@Controller

public class UserController {

	@Autowired
	UserService userService;

	// @RequestMapping(value = "/login", method = RequestMethod.GET)
	@GetMapping("/login")
	public String loginpage() {
		return "login";
	}

	@PostMapping("/login")
	public String welcomepage(ModelMap model, @RequestParam String username, @RequestParam String password) {
		User user = userService.getUserById(username);
		if (user.getPassword() != null && user.getPassword().equals(password)) {
			model.put("userId", username);
			return "welcome";

		}
		
		model.put("errorMSG", "enter correct creds");
		return "login";
	}

	@GetMapping("/register")
	public String registrationpage() {
		return "register";
	}

	@PostMapping("/register")
	public String registeruser(ModelMap model, @RequestParam("username") String username,
			@RequestParam("password") String password) {
		// calling the function of service layer that goes to dao
		int i = userService.insertuser(username, password);
		if (i != 0) {
		    //model.put("successMSG", "you are registered,enter username and password.");
		   // System.out.println("ModelMap value: " + model); // Print the value of model
			return "redirect:/login";
		   // return "register";
					
		} else {
			model.put("regerrorMSG", "error: username already exists");
			return "register";
		}
		// we will; check with the integer value if the process is a success
	}

}
//In Spring MVC, the string value returned by a controller method corresponds to the logical view name. The logical view name is a reference that Spring uses to determine which actual view should be rendered. In this case, the logical view name "login" is associated with the file name "login.jsp" because of the configuration in the application.properties file
//
