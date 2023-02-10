package com.datamining.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
public class Revenue implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date date;
	private Double total;
	private Integer amount;
	@JoinColumn(name = "category_name")
	private String categoryName;
	@JoinColumn(name = "product_name")
	private String productName;
}
