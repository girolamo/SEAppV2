package com.mgrzech.SEApp.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="stock_date")
public class StockDate {
	
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date date;
	/*
	@JsonProperty("items")
	@OneToMany(mappedBy="stockDate")
	private List<Stock> stocks;
	*/
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
/*
	public List<Stock> getStocks() {
		return stocks;
	}
*/
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
/*
	public List<Stock> getStock() {
		return stocks;
	}

	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}
	
	public void addSingleStock(Stock stock) {
		this.stocks.add(stock);
	}
	*/
}

