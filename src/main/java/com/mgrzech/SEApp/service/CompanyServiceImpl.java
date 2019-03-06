package com.mgrzech.SEApp.service;

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
	
	@Override
	public void saveCompaniesIntoDb(List<Company> listOfCompanies) {
		
		for(Company oneCompany : listOfCompanies) {		

			try {
				
				Company companyFromDb = companyRepository.findByCode(oneCompany.getCode());		
				oneCompany.setId(companyFromDb.getId());
				
			} catch (Exception e) {
				oneCompany.setAmount(5000);
				logger.info("New company detected -> Insert into Database");
				logger.info("Name: " + oneCompany.getName() + ", Code: " + oneCompany.getCode() + ", Unit: " + oneCompany.getUnit());
				
				companyRepository.save(oneCompany);
			}
			
		}
	}
	
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
