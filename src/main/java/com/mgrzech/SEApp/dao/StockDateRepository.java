package com.mgrzech.SEApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mgrzech.SEApp.entity.StockDate;

public interface StockDateRepository extends JpaRepository<StockDate, Long>, StockDateRepositoryCustom {

}
