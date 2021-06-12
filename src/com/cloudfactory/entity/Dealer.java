package com.cloudfactory.entity;

import java.util.ArrayList;

public class Dealer extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Order> orderList;
	
	public Dealer(String id, String pwd, String name, String contact) {
		super(id, pwd, name, contact);
		this.orderList=new ArrayList<Order>();
	}

	public ArrayList<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(ArrayList<Order> orderList) {
		this.orderList = orderList;
	}

	
	

}
