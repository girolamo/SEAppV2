package com.mgrzech.SEApp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mgrzech.SEApp.entity.Company;
//import com.mgrzech.SEApp.entity.Stock;
import com.mgrzech.SEApp.entity.User;
import com.mgrzech.SEApp.entity.UserStock;

public interface UserStockRepository extends JpaRepository<UserStock, Long> {

	List<UserStock> findAllByUser(User user);
	
	UserStock findByUserAndCompany(User user, Company company);

}
