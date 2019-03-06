package com.mgrzech.SEApp.service;

import java.util.List;

import com.mgrzech.SEApp.entity.Company;

public interface CompanyService {
	
	void saveCompaniesIntoDb(List<Company> listOfCompanies);
	
	List<Company> getListOfCompanies();
	
	Company getCompany(Long id);
	
	Company findCompanyByCode(String code);
	
	void setCompanyStocks(Company company, int amount, String action);
};
