package com.datamining.service;

import java.util.List;

import com.datamining.entity.Order;



public interface OrderService {
	List<Order> findAll();

	List<Order> findAllByCompleted();

	List<Order> findAllByCanceled();

	Order update(Integer id, Order order);
	Order create(Order order);
}
