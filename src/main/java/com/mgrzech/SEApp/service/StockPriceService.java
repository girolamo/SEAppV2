package com.mgrzech.SEApp.service;

import java.util.List;

import com.mgrzech.SEApp.entity.Company;
import com.mgrzech.SEApp.entity.StockPrice;

public interface StockPriceService {

	void savePricesIntoDB(List<StockPrice> listOfStockPrice);
	
	List<StockPrice> getLatestStockPrices();
	
	List<StockPrice> getAllComapnyPricesByCompany(Company company);
	
	StockPrice getCompanyLatestStockPriceByCompany(Company company);
}
