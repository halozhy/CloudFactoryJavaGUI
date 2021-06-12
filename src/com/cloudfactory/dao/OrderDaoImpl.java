package com.cloudfactory.dao;
//package com.cloudfactory.dao.impl;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//import com.cloudfactory.entity.Dealer;
//import com.cloudfactory.entity.Device;
//import com.cloudfactory.entity.FacAdmin;
//import com.cloudfactory.entity.Order;
//import com.cloudfactory.entity.User;
//import com.cloudfactory.util.FileUtils;
//
//public class OrderDaoImpl {
//
//	public static final String USERFILENAME = "UserData.dat";// 存放用户数据的文件名
//
//	private static OrderDaoImpl instance;
//
//	private OrderDaoImpl() {
//	}
//
//	/**
//	 * 实现单例模式
//	 * 
//	 * @return 返回OrderDaoImpl这个对象
//	 */
//	public static OrderDaoImpl getInstance() {
//		if (instance == null) {
//			instance = new OrderDaoImpl();
//		}
//		return instance;
//	}
//
//	/**
//	 * 查找
//	 * 
//	 * @param id 一个{@code String}类型的对象，即要查找的id
//	 * @return 返回一个{@code Order}对象，即查找到的，返回null即查找不到
//	 */
//	public Order findOrder(int uid) {
//		FileUtils<ArrayList<User>> f = new FileUtils<ArrayList<User>>();
//		ArrayList<User> list = f.getData(USERFILENAME);
//		for (User u : list) {
//			if (u.getClass().getSimpleName().equals("Dealer")) {
//				Dealer dealer = (Dealer) u;
//				ArrayList<Order> dList = dealer.getOrderList();
//				for (Order o : dList) {
//					if (o.getUid() == uid) {
//						return o;
//					}
//				}
//			}
//
//		}
//		return null;
//	}
//
//	/**
//	 * 添加
//	 * 
//	 * @param user 一个{@code Order}类型的对象，是要添加的
//	 * @return 返回一个{@code boolean}对象，true表示成功，false表示存在重名的
//	 */
//	public boolean addOrder(Order user) {
//
//		FileUtils<ArrayList<User>> f = new FileUtils<ArrayList<User>>();
//		ArrayList<Order> list = f.getData(USERFILENAME);
//		for (Order uu : list) {
//			if (user.getId().equals(uu.getId()) && uu.getIsDeleted().equals("0")) {
//				return false;
//			}
//		}
////		if (u != null&&u.getIsDeleted().equals("0")) {
////			return false;
////		} else {
//		list.add(user);
//		f.writeData(list, USERFILENAME);
//
//		return true;
////		}
//
//	}
//
//	/**
//	 * 删除
//	 * 
//	 * @param id 一个{@code String}类型的对象，即要删除的id
//	 * @return 返回一个{@code boolean}对象，true表示成功，false表示不存在
//	 */
//	public boolean removeOrder(String id) {
//
//		FileUtils<ArrayList<Order>> f = new FileUtils<ArrayList<Order>>();
//		ArrayList<Order> list = f.getData(USERFILENAME);
//		Order user = null;
//		for (Order uu : list) {
//			if (id.equals(uu.getId()) && uu.getIsDeleted().equals("0")) {
//				user = uu;
//
//			}
//		}
//
//		if (user == null) {
//			return false;
//		} else {
//			list.remove(user);
//			user.setIsDeleted("1");// 逻辑删除，杜绝物理删除
//			list.add(user);
//			f.writeData(list, USERFILENAME);
//
//			return true;
//		}
//
//	}
//
//	/**
//	 * 修改
//	 * 
//	 * @param id       一个{@code String}类型的对象，即要修改的用户的id
//	 * @param newOrder 一个{@code Order}类型的对象，即新的用户
//	 * @return 返回一个{@code boolean}对象，true表示成功，false表示不存在此用户
//	 */
//	public boolean modifyOrder(String id, Order newOrder) {
//		FileUtils<ArrayList<Order>> f = new FileUtils<ArrayList<Order>>();
//		ArrayList<Order> list = f.getData(USERFILENAME);
//		Order user = null;
//		for (Order uu : list) {
//			if (id.equals(uu.getId()) && uu.getIsDeleted().equals("0")) {
//				user = uu;
//			}
//		}
//		if (user == null) {
//			return false;
//		} else {
//			list.remove(user);
//			list.add(newOrder);
//			f.writeData(list, USERFILENAME);
//
//			return true;
//		}
//
//	}
//
//	/**
//	 * 获取所有的集合
//	 * 
//	 * @return
//	 */
//	public List<Order> getAll() {
//		FileUtils<ArrayList<Order>> f = new FileUtils<ArrayList<Order>>();
//		ArrayList<Order> list = f.getData(USERFILENAME);
//		List<Order> list1 = new ArrayList<Order>();
//		for (Order u : list) {
//			list1.add(u);
//		}
//		return list1;
//	}
//
//	public void stopFac(String id) {
//		FileUtils<ArrayList<Order>> f = new FileUtils<ArrayList<Order>>();
//		ArrayList<Order> list = f.getData(USERFILENAME);
//		Order user = null;
//		for (Order uu : list) {
//			if (id.equals(uu.getId()) && uu.getIsDeleted().equals("0")) {
//				user = uu;
//			}
//		}
//		if (user == null) {
//		} else {
//			FacAdmin admin = (FacAdmin) user;
//			list.remove(admin);
//			if (admin.getState() == 1) {
//				admin.setState(0);
//			} else {
//				admin.setState(1);
//			}
//
//			list.add(admin);
//			f.writeData(list, USERFILENAME);
//
//		}
//	}
//
//	/**
//	 * 测试用，读文件并将读到的数据打印到控制台，可以选择写空文件
//	 * 
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		FileUtils<ArrayList<Order>> f = new FileUtils<ArrayList<Order>>();
//		ArrayList<Order> list = new ArrayList<Order>();
//		list = f.getData(USERFILENAME);
//		System.out.println("源文件中读取到的数据如下");
//		if (list.size() == 0) {
//			System.out.println("空的");
//		} else {
//			for (Order u : list) {
//				System.out.println(u.getClass().getSimpleName() + " " + u + " " + u.getUid());
//			}
//		}
//		System.out.println("需要写空文件，请输入yes");
//		Scanner scan = new Scanner(System.in);
//		if (scan.nextLine().equals("yes")) {
//			list = new ArrayList<Order>();
//			f.writeData(list, USERFILENAME);
//			FacAdmin facAdmin = new FacAdmin("11", "11", "zhy", "1233", "fac1", "testfac", new ArrayList<Device>());
//			list.add(facAdmin);
//			f.writeData(list, USERFILENAME);
//			Dealer dealer = new Dealer("22", "22", "zhy2", "123333");
//			list.add(dealer);
//			f.writeData(list, USERFILENAME);
//			System.out.println(USERFILENAME + "文件已写空");
//			scan.close();
//		}
//
//	}
//}
