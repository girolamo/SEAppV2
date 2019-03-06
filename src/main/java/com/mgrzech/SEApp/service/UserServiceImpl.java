package com.mgrzech.SEApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mgrzech.SEApp.dao.UserRepository;
import com.mgrzech.SEApp.entity.Company;
import com.mgrzech.SEApp.entity.User;

@Service
public class UserServiceImpl implements UserService {
	
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private StockPriceService stockPriceService;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
	
	@Override 
	public void setUserBallance(Company company, User user, int amount, String action) {
		
		double balance = user.getBalance();
		double valueOfStocks = stockPriceService.getCompanyLatestStockPriceByCompany(company).getPrice()*amount;
		double finalBalance = 0;
	
		if(action == "BUY") {
			
			finalBalance = balance - valueOfStocks;
		} else if (action =="SELL") {
	
			finalBalance = balance + valueOfStocks;
		}
			
		user.setBalance(finalBalance);
		userRepository.save(user);
	}
	
	
	
	
}
