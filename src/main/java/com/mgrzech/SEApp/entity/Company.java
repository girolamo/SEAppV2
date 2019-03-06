package com.mgrzech.SEApp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="company")
public class Company {

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;

	private String code;

	private int unit;
	
	private int amount;

	@JsonIgnore
	@OneToMany(mappedBy="company", cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	private List<UserStock> userStock;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int ammount) {
		this.amount = ammount;
	}
	
	public List<UserStock> getUserStock() {
		return userStock;
	}

	public void setUserStock(List<UserStock> userStock) {
		this.userStock = userStock;
	}
	
}
