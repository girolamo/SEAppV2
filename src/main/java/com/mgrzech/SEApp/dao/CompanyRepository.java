package com.mgrzech.SEApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mgrzech.SEApp.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	Company findByCode(String code);
	
}
