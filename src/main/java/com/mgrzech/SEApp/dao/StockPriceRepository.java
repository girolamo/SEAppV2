package com.mgrzech.SEApp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mgrzech.SEApp.entity.Company;
import com.mgrzech.SEApp.entity.StockPrice;

public interface StockPriceRepository extends JpaRepository<StockPrice, Long>, StockPriceRepositoryCustom {

	List<StockPrice> findAllByCompany(Company company);
	
}
