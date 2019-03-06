package com.mgrzech.SEApp.validator;


import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mgrzech.SEApp.entity.User;
import com.mgrzech.SEApp.service.UserService;
	
@Component
public class UserValidator implements Validator {
	
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors)  {

        User user = (User) o;
        Pattern noSpecialChracters = Pattern.compile("[^a-z0-9]", Pattern.CASE_INSENSITIVE);
   
        
        if (user.getUsername().length() < 4 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.user.username");
        }
         
        if(noSpecialChracters.matcher(user.getUsername()).find()) {
        	errors.rejectValue("username", "Prohibited.user.username");
        } 
        
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.user.username");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        
        
        if (user.getPassword().length() < 6 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.user.password");
        }

        if (!user.getPassword_confirmation().equals(user.getPassword())) {
            errors.rejectValue("password_confirmation", "Diff.user.passwordConfirm");
        }
        
    }
    
}
	

