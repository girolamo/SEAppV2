package com.mgrzech.SEApp.controller;

import java.math.BigDecimal;
import java.math.MathContext;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mgrzech.SEApp.entity.Company;
import com.mgrzech.SEApp.entity.User;
import com.mgrzech.SEApp.entity.UserStock;
import com.mgrzech.SEApp.service.CompanyService;
import com.mgrzech.SEApp.service.StockPriceService;
import com.mgrzech.SEApp.service.UserService;
import com.mgrzech.SEApp.service.UserStockService;

@Controller
public class SEAppController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	UserStockService userStockService;
	
	@Autowired
	StockPriceService stockPriceService;
	
	@GetMapping({"/", "/index"})
	public String showHome(Principal user, Model model) {	
		
		if(user != null) {	
			User theUser = userService.findByUsername(user.getName());
			
			List<UserStock> theStocks = userStockService.getUserStocks(theUser);
			List<Company> companies = companyService.getListOfCompanies();
			
			BigDecimal roundedBalance = userService.findByUsername(user.getName()).getBalance();
			roundedBalance = roundedBalance.round(new MathContext(4));
			
			model.addAttribute("companies", companies);
			model.addAttribute("stockList", theStocks);
			model.addAttribute("userBalance", roundedBalance);
		}
		
		return "index";
	}
	
	@GetMapping({"/stock/{code}"})
	public String showChart(@PathVariable String code,
			Principal user, Model model) {	
		
		return "stock";
	}
	
	@PostMapping({"/buyStock"})
	public String setBuys(@RequestParam("company-stock") String companyCode,
			@RequestParam("quantity-buy-units") String quantityBuyUnits, Principal user) {

		String action = "BUY";
		User theUser = userService.findByUsername(user.getName());
		Company theCompany = companyService.findCompanyByCode(companyCode);
		int numberOfBoughtUnits = checkData(theCompany, quantityBuyUnits, user);	
		
		BigDecimal numberOfBoughtUnitsBD = new BigDecimal(numberOfBoughtUnits);
		int currentAmountOfStock = theCompany.getAmount();
		BigDecimal userBallance = userService.findByUsername(user.getName()).getBalance();
		BigDecimal valueOfBoughtStocks = stockPriceService.getCompanyLatestStockPriceByCompany(theCompany).getPrice().multiply(numberOfBoughtUnitsBD);
		
		if(numberOfBoughtUnits > 0 && (valueOfBoughtStocks.compareTo(userBallance) <= 0) && currentAmountOfStock >= numberOfBoughtUnits) {
			finalizeTransaction(theCompany, theUser, numberOfBoughtUnits, action);
			
			return "redirect:/index";
		} else {
			 return "redirect:/index?incorrectValue";
		}
	}
	
	@PostMapping({"/sellStock"})
	public String getSells(@RequestParam("company-stock") String companyCode,
			@RequestParam("quantity-sold-units") String quantitySoldUnits, Principal user) {
			
		String action = "SELL";
		User theUser = userService.findByUsername(user.getName());
		Company theCompany = companyService.findCompanyByCode(companyCode);
		int numberOfsoldUnits = checkData(theCompany, quantitySoldUnits, user);		
		
		int amountOfUserStocks = userStockService.findUserStockByUserAndCompany(theUser, theCompany).getAmmountOfStocks();

		if(numberOfsoldUnits > 0 && amountOfUserStocks >= numberOfsoldUnits) {	
			finalizeTransaction(theCompany, theUser, numberOfsoldUnits, action);
			
			return "redirect:/index";
		} else {
			 return"redirect:/index?incorrectValue";
		}	
	}
	
	private void finalizeTransaction(Company company, User user, int units, String action) {
		userStockService.setUserStock(company, user, units, action);
		companyService.setCompanyStocks(company, units, action);
		userService.setUserBallance(company, user, units, action);
	}
	
	private int checkData(Company theCompany, String units, Principal user) {
		
		int companyUnitStock = theCompany.getUnit();
		int tradedUnits = 0;
		
		try {
			tradedUnits = Integer.parseInt(units);
				
			if(tradedUnits <= 0 || tradedUnits%companyUnitStock != 0) {
				throw new Exception();
			}
			
		} catch(Exception e) {
			return -1;
		}
		
		return tradedUnits;
	}
	
}
