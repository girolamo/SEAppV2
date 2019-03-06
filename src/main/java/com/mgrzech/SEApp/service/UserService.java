package com.mgrzech.SEApp.service;

import com.mgrzech.SEApp.entity.Company;
import com.mgrzech.SEApp.entity.User;

public interface UserService {
	
    void save(User user);

    User findByUsername(String username);
    
    void setUserBallance(Company company, User user, int amount, String action);
}