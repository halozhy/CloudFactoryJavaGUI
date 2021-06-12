//package com.cloudfactory.controllers;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.List;
//
//import com.cloudfactory.factory.MyServiceFactory;
//import com.cloudfactory.service.OrderService;
//import com.cloudfactory.entity.FacAdmin;
//import com.cloudfactory.entity.Order;
//
//public class OrderController extends BaseController {
//	private static OrderController instance;
//	private OrderService orderService;
//
//	/**
//	 * 实现单例模式
//	 * 
//	 * @return 返回OrderServiceImpl这个对象
//	 */
//	public static OrderController getInstance(String message) {
//		if (instance == null) {
//			instance = new OrderController(message);
//		}
//		return instance;
//	}
//
//	private OrderController(String message) {
//		super(message);
//		orderService = (OrderService) MyServiceFactory.createService(message);
//	}
//
//	public boolean addOrder(Order order) {
//		return orderService.addOrder(order);
//	}
//
//	public boolean removeOrder(String s) {
//		return orderService.removeOrder(s);
//	}
//
//	public boolean modifyOrder(String s, Order u) {
//		return orderService.modifyOrder(s, u);
//	}
//
//	public String[][] getAll() {
//
//		List<Order> list = orderService.getAll();
//		Collections.sort(list,new Comparator<Order>() {
//
//            @Override
//            public int compare(Order o1, Order o2) {
//                // 返回值为int类型，大于0表示正序，小于0表示逆序
//            	if(o1.getUid()-o2.getUid()>0) {
//            		return 1;
//            	}
//            	else if(o1.getUid()-o2.getUid()==0) {
//            		return 0;
//            	}
//                return -1;
//            }
//        });
//		int cnt=0;
//		for (Order u : list) {
//			if(u.getIsDeleted().equals("0")) {
//				cnt++;
//			}
//		}
//		String[][] data = new String[cnt][7];
//		int i=0;
//		for (Order u : list) {
//			if(u.getIsDeleted().equals("0")) {
////			data[i][0]=String.valueOf(u.getUid());
//			data[i][0]=String.valueOf(i+1);
//			data[i][1]=u.getId();
//			data[i][2]=u.getName();
//			data[i][3]=u.getPwd();
//			data[i][4]=u.getContact();
//			data[i][5]=u.getClass().getSimpleName().equals("FacAdmin")?"云工厂管理员":"经销商";
//			data[i][6]="";
//			i++;
//			}
//		}
//		return data;
//	}
//
//	public String[][] getAllFacInfo() {
//		List<Order> list = orderService.getAll();
//		Collections.sort(list,new Comparator<Order>() {
//
//            @Override
//            public int compare(Order o1, Order o2) {
//                // 返回值为int类型，大于0表示正序，小于0表示逆序
//            	if(o1.getUid()-o2.getUid()>0) {
//            		return 1;
//            	}
//            	else if(o1.getUid()-o2.getUid()==0) {
//            		return 0;
//            	}
//                return -1;
//            }
//        });
//		int cnt=0;
//		for (Order u : list) {
//			if(u.getIsDeleted().equals("0")&&u.getClass().getSimpleName().equals("FacAdmin")) {
//				cnt++;
//			}
//		}
//		String[][] data = new String[cnt][8];
//		int i=0;
////		"序号", "工厂名称", "工厂简介", "负责人", "联系方式", "登录账号", "工厂状态","操作"
//		for (Order u : list) {
//			if(u.getIsDeleted().equals("0")&&u.getClass().getSimpleName().equals("FacAdmin")) {
////			data[i][0]=String.valueOf(u.getUid());
//			FacAdmin facAdmin =(FacAdmin)u; 
//			data[i][0]=String.valueOf(i+1);
//			data[i][1]=facAdmin.getFacName();
//			data[i][2]=facAdmin.getFacDetail();
//			data[i][3]=facAdmin.getName();
//			data[i][4]=facAdmin.getContact();
//			data[i][5]=facAdmin.getId();
//			data[i][6]=facAdmin.getState()==1?"正常":"关停";
//			data[i][7]="";
//			i++;
////			System.out.println(data[i][0]+data[i][1]+data[i][2]+data[i][3]+data[i][4]+data[i][5]+data[i][6]);
////			System.out.println(data[i][0]);
//			}
//		}
//		return data;
//		
//	}
//	public Order findOrder(String id) {
//		return orderService.findOrder(id);
//	}
//
//
//}
