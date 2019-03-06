package com.mgrzech.SEApp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.mgrzech.SEApp.entity.Company;
import com.mgrzech.SEApp.entity.StockPrice;

@Repository
public class StockPriceRepositoryCustomImpl implements StockPriceRepositoryCustom {

	@PersistenceContext
    EntityManager entityManager;
	
	@Override
	public List<StockPrice> getLatestStockPrices() {
		
		Query query = entityManager.createNativeQuery("SELECT sp.* FROM stock_price as sp WHERE sp.stock_date_id=(SELECT sd.id FROM stock_date as sd ORDER BY sd.date desc LIMIT 1)", StockPrice.class);	
		@SuppressWarnings("unchecked")
		List<StockPrice> stockPrices = (List<StockPrice>) query.getResultList();
		
		return stockPrices;
	}
	
	@Override
	public StockPrice getLatestStocPriceByCompany(Company company) {
		
		Query query = entityManager.createNativeQuery("SELECT sp.* FROM stock_price as sp "
				+ "WHERE sp.stock_date_id=(SELECT sd.id FROM stock_date as sd ORDER BY sd.date desc LIMIT 1)"
				+ "AND sp.company_id=(SELECT comp.id FROM company as comp WHERE comp.code=?1)", StockPrice.class);	
		query.setParameter(1, company.getCode());

		StockPrice stockPrice = (StockPrice) query.getSingleResult();
			
		return stockPrice;
	}

}
