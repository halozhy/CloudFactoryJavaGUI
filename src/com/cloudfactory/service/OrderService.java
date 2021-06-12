package com.cloudfactory.service;

import java.util.List;

import com.cloudfactory.entity.Order;

public interface OrderService extends BaseService{

	boolean addOrder(Order order);

	boolean removeOrder(String s);

	boolean modifyOrder(String s, Order u);

	List<Order> getAll();

	Order findOrder(String id);



}
