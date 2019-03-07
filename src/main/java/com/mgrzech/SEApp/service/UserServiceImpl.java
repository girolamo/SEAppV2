package com.mgrzech.SEApp.service;

import java.math.BigDecimal;

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
		
		BigDecimal amountBC = new BigDecimal(amount);
		BigDecimal balance = user.getBalance();
		BigDecimal valueOfStocks = stockPriceService.getCompanyLatestStockPriceByCompany(company).getPrice().multiply(amountBC);
		BigDecimal finalBalance = new BigDecimal(0);
	
		if(action == "BUY") {
			
			finalBalance = balance.subtract(valueOfStocks);
		} else if (action =="SELL") {
	
			finalBalance = balance.add(valueOfStocks);
		}
			
		user.setBalance(finalBalance);
		userRepository.save(user);
	}
	
	
	
	
}
