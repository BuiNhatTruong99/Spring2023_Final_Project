package com.datamining.servide.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datamining.dao.OrderDAO;
import com.datamining.entity.Order;
import com.datamining.service.OrderService;


@Service
public class OrderServiceIpml implements OrderService {
	@Autowired
	OrderDAO dao;

	public List<Order> findAll() {
		return dao.findAllOrders();
	}

	@Override
	public List<Order> findAllByCompleted() {
		return dao.findAllByCompleted();
	}

	@Override
	public List<Order> findAllByCanceled() {
		return dao.findAllByCanceled();
	}

	@Override
	public Order update(Integer id, Order order) {
		return dao.save(order);
	}

	@Override
	public Order create(Order order) {
		return dao.save(order);
	}
}



