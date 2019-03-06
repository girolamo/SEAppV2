package com.mgrzech.SEApp.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mgrzech.SEApp.entity.StockDate;

@Repository
public class StockDateRepositoryCustomImpl implements StockDateRepositoryCustom {

	Logger logger = LoggerFactory.getLogger(StockDateRepositoryCustomImpl.class);
	
	@PersistenceContext
    EntityManager entityManager;
	
	// Get Latest StockDate from db
	@Override
	public StockDate getLatestStockDate() {
		
		StockDate newStockDate = new StockDate();
		try {
			Query query = entityManager.createNativeQuery("SELECT sd.* FROM stock_date sd ORDER BY sd.date desc LIMIT 1", StockDate.class);	
			newStockDate = (StockDate) query.getSingleResult();
			
		} catch (Exception e) {
			logger.info("No date found.");
		}
		
		return newStockDate;
	}

}
