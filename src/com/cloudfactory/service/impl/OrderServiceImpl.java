package com.cloudfactory.service.impl;

import java.util.List;

import com.cloudfactory.entity.Order;
import com.cloudfactory.service.OrderService;

public class OrderServiceImpl implements OrderService{
	private static OrderServiceImpl instance;

	private OrderServiceImpl() {
	
	}

	/**
	 * 实现单例模式
	 * @return 返回OrderServiceImpl这个对象
	 */
	public static OrderServiceImpl getInstance() {
		if (instance == null) {
			instance = new OrderServiceImpl();
		}
		return instance;
	}

	@Override
	public boolean addOrder(Order order) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeOrder(String s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifyOrder(String s, Order u) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Order> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order findOrder(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}
