package com.cloudfactory.controllers;

import com.cloudfactory.factory.MyServiceFactory;

public class BaseController {

	public BaseController(String message)
	{
		MyServiceFactory.createService(message);
	}

}
