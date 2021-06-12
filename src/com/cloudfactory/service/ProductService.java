package com.cloudfactory.service;

import java.util.List;

import com.cloudfactory.entity.Product;

public interface ProductService extends BaseService{

	boolean addProduct(Product product);

	boolean removeProduct(String s);

	boolean modifyProduct(String s, Product u);

	List<Product> getAll();

	Product findProduct(String id);


}
