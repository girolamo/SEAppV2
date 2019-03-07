package com.mgrzech.SEApp.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

@JsonFilter("StockPriceFilter")
@Entity
@Table(name="stock_price")
public class StockPrice {

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private BigDecimal price;
	
	@ManyToOne
	@JoinColumn(name="company_id")
	private Company company;
		
	@ManyToOne
	@JoinColumn(name="stock_date_id")
	private StockDate stockDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public StockDate getStockDate() {
		return stockDate;
	}

	public void setStockDate(StockDate stockDate) {
		this.stockDate = stockDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}
