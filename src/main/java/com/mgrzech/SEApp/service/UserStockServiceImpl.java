package com.mgrzech.SEApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mgrzech.SEApp.dao.UserStockRepository;
import com.mgrzech.SEApp.entity.Company;
import com.mgrzech.SEApp.entity.User;
import com.mgrzech.SEApp.entity.UserStock;

@Service
public class UserStockServiceImpl implements UserStockService {

	@Autowired
	UserStockRepository userStockRepository;

	@Override
	public List<UserStock> getUserStocks(User user) {
			
		List<UserStock> theStocks = userStockRepository.findAllByUser(user);
		return theStocks;
	}

	// Save bought or sold stocks into db
	@Override
	public void setUserStock(Company company, User user, int amount, String action) {
		
		UserStock theUserStock = userStockRepository.findByUserAndCompany(user, company);		

		if(action == "SELL") {
			theUserStock.setAmmountOfStocks(theUserStock.getAmmountOfStocks() - amount);
		} else if(action == "BUY") {
			if(theUserStock == null) {
				theUserStock = new UserStock();
				theUserStock.setAmmountOfStocks(amount);
				theUserStock.setCompany(company);
				theUserStock.setUser(user);
			} else {
				theUserStock.setAmmountOfStocks(theUserStock.getAmmountOfStocks() + amount);
			}
		}
		
		userStockRepository.save(theUserStock);	
	}
	
	@Override
	public UserStock findUserStockByUserAndCompany(User user, Company company) {
		return userStockRepository.findByUserAndCompany(user, company);
	}
}
