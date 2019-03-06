package com.mgrzech.SEApp.service;

import java.util.List;

import com.mgrzech.SEApp.entity.Company;
import com.mgrzech.SEApp.entity.User;
import com.mgrzech.SEApp.entity.UserStock;

public interface UserStockService {

	List<UserStock> getUserStocks(User user);
	
	UserStock findUserStockByUserAndCompany(User user, Company company);
	
	void setUserStock(Company company, User user,  int amount, String action);
}
