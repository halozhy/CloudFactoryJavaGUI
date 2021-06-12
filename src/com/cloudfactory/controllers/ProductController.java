package com.cloudfactory.controllers;




import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.cloudfactory.factory.MyServiceFactory;
import com.cloudfactory.service.ProductService;
import com.cloudfactory.entity.Product;

public class ProductController extends BaseController {
	private static ProductController instance;
	private ProductService ProductService;

	/**
	 * 实现单例模式
	 * 
	 * @return 返回ProductServiceImpl这个对象
	 */
	public static ProductController getInstance(String message) {
		if (instance == null) {
			instance = new ProductController(message);
		}
		return instance;
	}

	private ProductController(String message) {
		super(message);
		ProductService = (ProductService) MyServiceFactory.createService(message);
	}

	public boolean addProduct(Product Product) {
		return ProductService.addProduct(Product);
	}

	public boolean removeProduct(String s) {
		return ProductService.removeProduct(s);
	}

	public boolean modifyProduct(String s, Product u) {
		return ProductService.modifyProduct(s, u);
	}

	public String[][] getAll() {

		List<Product> list = ProductService.getAll();
		Collections.sort(list,new Comparator<Product>() {

            @Override
            public int compare(Product o1, Product o2) {
                // 返回值为int类型，大于0表示正序，小于0表示逆序
            	if(o1.getUid()-o2.getUid()>0) {
            		return 1;
            	}
            	else if(o1.getUid()-o2.getUid()==0) {
            		return 0;
            	}
                return -1;
            }
        });
		int cnt=0;
		for (Product u : list) {
			if(u.getIsDeleted().equals("0")) {
				cnt++;
			}
		}
//		{ "序号", "产品编号", "产品名称", "产品类别", "产品规格", "产品描述", "操作" };
		String[][] data = new String[cnt][7];
		int i=0;
		for (Product u : list) {
			if(u.getIsDeleted().equals("0")) {
			data[i][0]=String.valueOf(i+1);
			data[i][1]=u.getId();
			data[i][2]=u.getName();
			data[i][3]=u.getType();
			data[i][4]=u.getGuige();
			data[i][5]=u.getDetail();
			data[i][6]="";
			i++;
			}
		}
		return data;
	}

	public Product findProduct(String id) {
		return ProductService.findProduct(id);
	}


}
