package com.cloudfactory.service;

import java.util.List;

import com.cloudfactory.entity.User;

public interface UserService extends BaseService{
	public boolean addUser(User u);
	public boolean removeUser(String s);
	public boolean modifyUser(String s, User u);
	List<User> getAll();
	public User findUser(String id);
	int login(String id, String pwd);
	public void stopFac(String id);
	
}
