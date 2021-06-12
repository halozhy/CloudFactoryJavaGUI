package com.cloudfactory.factory;

import com.cloudfactory.service.BaseService;
import com.cloudfactory.service.impl.DeviceServiceImpl;
import com.cloudfactory.service.impl.OrderServiceImpl;
import com.cloudfactory.service.impl.ProductServiceImpl;
import com.cloudfactory.service.impl.UserServiceImpl;
/** 
 * 使用工厂模式实现Service对象的获取
 */  
public class MyServiceFactory  {
	
    public static BaseService createService(String message) {
		
    	BaseService  baseService=null;
		
    	// 返回用户管理的Service类
    	if ("User".equals(message))
    		baseService = UserServiceImpl.getInstance();
    	if ("Product".equals(message))
    		baseService = ProductServiceImpl.getInstance();
    	if ("Device".equals(message))
    		baseService = DeviceServiceImpl.getInstance();
    	if ("Order".equals(message))
    		baseService = OrderServiceImpl.getInstance();
        return baseService;
	}
}