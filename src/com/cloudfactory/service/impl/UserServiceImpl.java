package com.cloudfactory.service.impl;

import java.util.List;

import com.cloudfactory.dao.UserDaoImpl;
import com.cloudfactory.entity.User;
import com.cloudfactory.service.UserService;

public class UserServiceImpl implements UserService {

	private static UserServiceImpl instance;

	private UserServiceImpl() {
	}

	/**
	 * 实现单例模式
	 * @return 返回UserServiceImpl这个对象
	 */
	public static UserServiceImpl getInstance() {
		if (instance == null) {
			instance = new UserServiceImpl();
		}
		return instance;
	}

	public boolean addUser(User u)  {
		return UserDaoImpl.getInstance().addUser(u);

	}

	@Override
	public List<User> getAll() {
		return UserDaoImpl.getInstance().getAll();
	}

	@Override
	public User findUser(String id) {
		return UserDaoImpl.getInstance().findUser(id);
	}

	@Override
	public boolean removeUser(String s) {
		return UserDaoImpl.getInstance().removeUser(s);
	}

	@Override
	public boolean modifyUser(String s, User u) {
		return UserDaoImpl.getInstance().modifyUser(s, u);
	}
	
	/**
	 * @param id
	 * @param pwd
	 * @return 1 系统管理员，2 云工厂管理员，3 经销商，-1 未找到用户，-2 密码错误
	 */
	@Override
	public int login(String id, String pwd) {
		if (id.equals("admin")) {
			if(pwd.equals("admin")) {
				return 1;
			}
			else {
				return -2;
			}
			
		} else {
			User u = UserDaoImpl.getInstance().findUser(id);
			if (u == null) {
				return -1;
			} else {
				if (u.getPwd().equals(pwd)) {
					if (u.getClass().getSimpleName().equals("FacAdmin")) {
						return 2;
					} else {
						return 3;
					}
				} else {
					return -2;
				}
			}
		}

	}

	@Override
	public void stopFac(String id) {
		UserDaoImpl.getInstance().stopFac(id);
		
	}



}
