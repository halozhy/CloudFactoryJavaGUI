package com.cloudfactory.util;

import java.util.ArrayList;

import com.cloudfactory.entity.Dealer;
import com.cloudfactory.entity.Device;
import com.cloudfactory.entity.FacAdmin;
import com.cloudfactory.entity.Order;
import com.cloudfactory.entity.Product;
import com.cloudfactory.entity.User;

public class IDGenerator {
	public static int getUserUID() {
		FileUtils<ArrayList<User>> f = new FileUtils<ArrayList<User>>();
		ArrayList<User> list = f.getData("UserData.dat");
		return list.size() + 1;
	}

	public static int getProductUID() {
		FileUtils<ArrayList<Product>> f = new FileUtils<ArrayList<Product>>();
		ArrayList<Product> list = f.getData("ProductData.dat");
		return list.size() + 1;
	}

	public static String getProductID() {
		FileUtils<ArrayList<Product>> f = new FileUtils<ArrayList<Product>>();
		ArrayList<Product> list = f.getData("ProductData.dat");
		int newid = list.size() + 1;
		return "P" + newid;
	}

	public static String getDeviceID() {
		FileUtils<ArrayList<User>> f = new FileUtils<ArrayList<User>>();
		
		ArrayList<User> list = f.getData("UserData.dat");
		
		FileUtils<ArrayList<Device>> f2 = new FileUtils<ArrayList<Device>>();
		ArrayList<Device> list2 = f2.getData("DeviceCenterData.dat");
		int cnt = list2.size();
		for (User u : list) {
			if (u.getClass().getSimpleName().equals("FacAdmin")) {
				FacAdmin admin = (FacAdmin) u;
				ArrayList<Device> dList = admin.getDeviceList();
				cnt += dList.size();
			}
		}
		int newid = cnt + 1;
		return "D" + newid;
	}

	public static int getDeviceUID() {
		FileUtils<ArrayList<User>> f = new FileUtils<ArrayList<User>>();
		ArrayList<User> list = f.getData("UserData.dat");
		FileUtils<ArrayList<Device>> f2 = new FileUtils<ArrayList<Device>>();
		ArrayList<Device> list2 = f2.getData("DeviceCenterData.dat");
		int cnt = list2.size();
		for (User u : list) {
			if (u.getClass().getSimpleName().equals("FacAdmin")) {
				FacAdmin admin = (FacAdmin) u;
				ArrayList<Device> dList = admin.getDeviceList();
				cnt += dList.size();
			}
		}
		int newid = cnt + 1;
		return newid;
	}
	
	public static int getOrderUID() {
		FileUtils<ArrayList<User>> f = new FileUtils<ArrayList<User>>();
		ArrayList<User> list = f.getData("UserData.dat");
		int cnt =0;
		for (User u : list) {
			if (u.getClass().getSimpleName().equals("Dealer")) {
				Dealer dealer = (Dealer) u;
				ArrayList<Order> dList = dealer.getOrderList();
				cnt += dList.size();
			}
		}
		return cnt+1;
	}
}
