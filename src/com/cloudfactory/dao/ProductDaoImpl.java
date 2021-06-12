package com.cloudfactory.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cloudfactory.entity.Product;
import com.cloudfactory.util.FileUtils;

public class ProductDaoImpl {
	public static final String PRODUCTFILENAME = "ProductData.dat";// 存放用户数据的文件名

	private static ProductDaoImpl instance;

	private ProductDaoImpl() {
	}

	/**
	 * 实现单例模式
	 * 
	 * @return 返回ProductDaoImpl这个对象
	 */
	public static ProductDaoImpl getInstance() {
		if (instance == null) {
			instance = new ProductDaoImpl();
		}
		return instance;
	}
	
	
	public boolean addProduct(Product product) {
		FileUtils<ArrayList<Product>> f = new FileUtils<ArrayList<Product>>();
		ArrayList<Product> list = f.getData(PRODUCTFILENAME);
		for (Product uu : list) {
			if (product.getId().equals(uu.getId()) && uu.getIsDeleted().equals("0")) {
				return false;
			}
		}
//		if (u != null&&u.getIsDeleted().equals("0")) {
//			return false;
//		} else {
		list.add(product);
		f.writeData(list, PRODUCTFILENAME);

		return true;
//		}
	}

	
	public boolean removeProduct(String id) {
		FileUtils<ArrayList<Product>> f = new FileUtils<ArrayList<Product>>();
		ArrayList<Product> list = f.getData(PRODUCTFILENAME);
		Product user = null;
		for (Product uu : list) {
			if (id.equals(uu.getId()) && uu.getIsDeleted().equals("0")) {
				user = uu;

			}
		}

		if (user == null) {
			return false;
		} else {
			list.remove(user);
			user.setIsDeleted("1");// 逻辑删除，杜绝物理删除
			list.add(user);
			f.writeData(list, PRODUCTFILENAME);

			return true;
		}
	}

	
	public boolean modifyProduct(String id, Product newP) {
		FileUtils<ArrayList<Product>> f = new FileUtils<ArrayList<Product>>();
		ArrayList<Product> list = f.getData(PRODUCTFILENAME);
		Product user = null;
		for (Product uu : list) {
			if (id.equals(uu.getId()) && uu.getIsDeleted().equals("0")) {
				user = uu;
			}
		}
		if (user == null) {
			return false;
		} else {
			list.remove(user);
			list.add(newP);
			f.writeData(list, PRODUCTFILENAME);
			return true;
		}
	}

	
	public List<Product> getAll() {
		FileUtils<ArrayList<Product>> f = new FileUtils<ArrayList<Product>>();
		ArrayList<Product> list = f.getData(PRODUCTFILENAME);
		List<Product> list1 = new ArrayList<Product>();
		for (Product u : list) {
			list1.add(u);
		}
		return list1;
	}

	public Product findProduct(String id) {
		FileUtils<ArrayList<Product>> f = new FileUtils<ArrayList<Product>>();
		ArrayList<Product> list = f.getData(PRODUCTFILENAME);
		for (Product u : list) {
			if (id.equals(u.getId()) && u.getIsDeleted().equals("0")) {
				return u;
			}
		}
		return null;
	}
	
	/**
	 * 测试用，读文件并将读到的数据打印到控制台，可以选择初始化文件
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		FileUtils<ArrayList<Product>> f = new FileUtils<ArrayList<Product>>();
		ArrayList<Product> list = new ArrayList<Product>();
		list = f.getData(PRODUCTFILENAME);
		System.out.println("源文件中读取到的数据如下");
		if (list.size() == 0) {
			System.out.println("空的");
		} else {
			for (Product u : list) {
				System.out.println(u.getClass().getSimpleName() + " " + u + " " + u.getUid());
			}
		}
		System.out.println("需要初始化文件，请输入yes");
		Scanner scan = new Scanner(System.in);
		if (scan.nextLine().equals("yes")) {
			list = new ArrayList<Product>();
			f.writeData(list, PRODUCTFILENAME);
			
			list.add(new Product("一般手机壳", "手机壳", "10*20","说明" ));
			f.writeData(list, PRODUCTFILENAME);
			
			System.out.println(PRODUCTFILENAME + "文件已初始化");
			scan.close();
		}

	}
}
