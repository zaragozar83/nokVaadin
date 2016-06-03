package com.coffeenok.nok.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coffeenok.nok.dao.OrderDao;
import com.coffeenok.nok.domain.Order;
import com.coffeenok.nok.service.OrderService;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Autowired
	//@Qualifier("orderDAO")
	private OrderDao orderDao;
	
	public List<Order> findAll(){
		List<Order> res = orderDao.findAll();
		return res;
	}

}
