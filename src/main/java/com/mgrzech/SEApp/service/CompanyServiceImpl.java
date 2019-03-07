package com.mgrzech.SEApp.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mgrzech.SEApp.dao.CompanyRepository;
import com.mgrzech.SEApp.entity.Company;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);
	
	@Autowired
	CompanyRepository companyRepository;
	
	// ------------------------------------------------------ Get Data
	@Override
	public List<Company> getListOfCompanies() {
		return companyRepository.findAll();
	}
	
	@Override
	public Company getCompany(Long id) {
		return companyRepository.getOne(id);
	}
	
	@Override
	public Company findCompanyByCode(String code) {
		return companyRepository.findByCode(code);
	}
	
	// ------------------------------------------------------ Set Data
	@Override
	public void saveCompaniesIntoDb(List<Company> listOfCompanies) {
		
		List<Company> newCompanies = new ArrayList<>();
		for(Company oneCompany : listOfCompanies) {		

			try {
				
				findCompanyByCode(oneCompany.getCode());		
				
			} catch (Exception e) {
				oneCompany.setAmount(5000);
				logger.info("New company detected -> Insert into Database");
				logger.info("Name: " + oneCompany.getName() + ", Code: " + oneCompany.getCode() + ", Unit: " + oneCompany.getUnit());
				
				newCompanies.add(oneCompany);
			}
			
		}
		
		if(newCompanies.size() > 0) {
			companyRepository.saveAll(newCompanies);
		}
		
	}
	
	
	@Override
	public void setCompanyStocks(Company company, int amount, String action) {
		
		int finalAmount = 0;
		if(action == "BUY") {
			
			finalAmount = company.getAmount() - amount;			
		} else if (action == "SELL") {
			
			finalAmount = company.getAmount() + amount;		
		}
		
		company.setAmount(finalAmount);
		companyRepository.save(company);
	}
	
}
