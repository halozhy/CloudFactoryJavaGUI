package com.cloudfactory.service.impl;

import java.util.List;

import com.cloudfactory.dao.ProductDaoImpl;
import com.cloudfactory.entity.Product;
import com.cloudfactory.service.ProductService;

public class ProductServiceImpl implements ProductService{

	
	private static ProductServiceImpl instance;

	private ProductServiceImpl() {
	
	}

	/**
	 * 实现单例模式
	 * @return 返回ProductServiceImpl这个对象
	 */
	public static ProductServiceImpl getInstance() {
		if (instance == null) {
			instance = new ProductServiceImpl();
		}
		return instance;
	}
	
	@Override
	public boolean addProduct(Product product) {
		return ProductDaoImpl.getInstance().addProduct(product);
	}

	@Override
	public boolean removeProduct(String id) {
		 return ProductDaoImpl.getInstance().removeProduct(id);
	}

	@Override
	public boolean modifyProduct(String id, Product newP) {
		return ProductDaoImpl.getInstance().modifyProduct(id, newP);
	}

	@Override
	public List<Product> getAll() {
		return ProductDaoImpl.getInstance().getAll();
	}

	@Override
	public Product findProduct(String id) {
		return ProductDaoImpl.getInstance().findProduct(id);
	}

}
