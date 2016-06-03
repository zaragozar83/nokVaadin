package com.coffeenok.nok.dao;

import java.util.List;

import com.coffeenok.nok.domain.Order;

public interface OrderDao {

	public void createDbTable();
	public List<Order> findAll();
	public void save(Order order);
	public void deleteOrder(Order order);
}
