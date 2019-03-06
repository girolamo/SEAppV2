package com.mgrzech.SEApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mgrzech.SEApp.dao.StockPriceRepository;
import com.mgrzech.SEApp.entity.Company;
import com.mgrzech.SEApp.entity.StockPrice;

@Service
public class StockPriceServiceImpl implements StockPriceService {

	@Autowired
	StockPriceRepository stockPriceRepository;
	
	@Override
	public void savePricesIntoDB(List<StockPrice> listOfStockPrice) {
		stockPriceRepository.saveAll(listOfStockPrice);
	}
	
	@Override
	public List<StockPrice> getLatestStockPrices() {
		return stockPriceRepository.getLatestStockPrices();
	}
	
	@Override
	public List<StockPrice> getAllComapnyPricesByCompany(Company company) {
		return stockPriceRepository.findAllByCompany(company);
	}
	
	@Override
	public StockPrice getCompanyLatestStockPriceByCompany(Company company) {	
		return stockPriceRepository.getLatestStocPriceByCompany(company);
	}

}
