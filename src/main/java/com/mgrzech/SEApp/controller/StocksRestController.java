package com.mgrzech.SEApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.mgrzech.SEApp.entity.Company;
import com.mgrzech.SEApp.entity.StockDate;
import com.mgrzech.SEApp.entity.StockPrice;
import com.mgrzech.SEApp.service.CompanyService;
import com.mgrzech.SEApp.service.StockDateService;
import com.mgrzech.SEApp.service.StockPriceService;

@RestController
@RequestMapping("/api")
public class StocksRestController {
	
	@Autowired
	StockDateService stockDateService;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	StockPriceService stockPriceService;
	
	// Rest for Stock Date
	@GetMapping("/stockDate")
	public StockDate getLatestStockDate() {
		return stockDateService.getLatestStockDate();
	}
	
	// Rest for companies
	@GetMapping("/company/{id}") 
	public Company getCompany(@PathVariable Long id) {
		return companyService.getCompany(id);
	}
	
	@GetMapping("/companies")
	public List<Company> getAllCompanies() {
		return companyService.getListOfCompanies();
	}
	
	// Rest for stock prices
	@GetMapping("/prices")
	public  MappingJacksonValue getLatestPrices() {		
		
		List<StockPrice> listOfStockPrices = stockPriceService.getLatestStockPrices(); 	
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("price", "company");
		FilterProvider filters = new SimpleFilterProvider().addFilter("StockPriceFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(listOfStockPrices);
		mapping.setFilters(filters);
		
		
		return mapping;
	}
	
	@GetMapping("/prices/{code}")
	public MappingJacksonValue getAllCompanyPrices(@PathVariable String code) {		
		
		Company theCompany = companyService.findCompanyByCode(code);
		List<StockPrice> companyPrices = stockPriceService.getAllComapnyPricesByCompany(theCompany);	
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("price", "stockDate");
		FilterProvider filters = new SimpleFilterProvider().addFilter("StockPriceFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(companyPrices);
		mapping.setFilters(filters);
		
		return mapping;
	}
	
}
