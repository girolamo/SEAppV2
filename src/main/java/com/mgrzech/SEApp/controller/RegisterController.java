package com.mgrzech.SEApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mgrzech.SEApp.entity.User;
import com.mgrzech.SEApp.service.SecurityService;
import com.mgrzech.SEApp.service.UserService;
import com.mgrzech.SEApp.validator.UserValidator;

@Controller
public class RegisterController {
	
	@Autowired
    private UserService userService;
	
	@Autowired
    private SecurityService securityService;
	
	@Autowired
    private UserValidator userValidator;
	
	@GetMapping("/registration")
	public String registration(Model model) {	
		
		User theUser = new User();
		model.addAttribute("user", theUser);
		
		return "register-form";	
	}

	@PostMapping("/registration")
	public String registerUser(@ModelAttribute("user") User user, BindingResult bindingResult) {  

		userValidator.validate(user, bindingResult);
		
		if (bindingResult.hasErrors()) {
            return "register-form";
        }
	
		
        userService.save(user);
        securityService.autoLogin(user.getUsername(), user.getPassword_confirmation());   
        
        return "redirect:/index?accountCreated";
	}
	
}
