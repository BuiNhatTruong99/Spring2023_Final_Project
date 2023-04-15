package com.datamining.dao;

import com.datamining.entity.OrderStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusDAO extends JpaRepository<OrderStatus, Integer> {
	
}
