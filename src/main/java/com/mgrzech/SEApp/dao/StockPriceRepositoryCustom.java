package com.mgrzech.SEApp.dao;

import java.util.List;

import com.mgrzech.SEApp.entity.Company;
import com.mgrzech.SEApp.entity.StockPrice;

public interface StockPriceRepositoryCustom {

	List<StockPrice> getLatestStockPrices();
	
	StockPrice getLatestStocPriceByCompany(Company company);
}
