package com.mgrzech.SEApp.service;

import java.util.List;

import com.mgrzech.SEApp.entity.StockDate;

public interface StockDateService {

	void getAndSaveDate();
	
	StockDate getLatestStockDate();
	
	List<StockDate> getAllStockDates();
}
