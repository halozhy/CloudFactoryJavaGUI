package com.cloudfactory.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cloudfactory.entity.Dealer;
import com.cloudfactory.entity.Device;
import com.cloudfactory.entity.FacAdmin;
import com.cloudfactory.entity.User;
import com.cloudfactory.util.FileUtils;

public class UserDaoImpl  {

	public static final String USERFILENAME = "UserData.dat";// 存放用户数据的文件名

	private static UserDaoImpl instance;

	private UserDaoImpl() {
	}

	/**
	 * 实现单例模式
	 * 
	 * @return 返回UserDaoImpl这个对象
	 */
	public static UserDaoImpl getInstance() {
		if (instance == null) {
			instance = new UserDaoImpl();
		}
		return instance;
	}

	/**
	 * 查找用户
	 * 
	 * @param id 一个{@code String}类型的对象，即要查找的id
	 * @return 返回一个{@code User}对象，即查找到的用户，返回null即查找不到
	 */
	public User findUser(String id) {
		FileUtils<ArrayList<User>> f = new FileUtils<ArrayList<User>>();
		ArrayList<User> list = f.getData(USERFILENAME);
		for (User u : list) {
			if (id.equals(u.getId()) && u.getIsDeleted().equals("0")) {
				return u;
			}
		}
		return null;
	}

	/**
	 * 添加用户
	 * 
	 * @param user 一个{@code User}类型的对象，是要添加的用户
	 * @return 返回一个{@code boolean}对象，true表示成功，false表示存在重名的用户
	 */
	public boolean addUser(User user) {

		FileUtils<ArrayList<User>> f = new FileUtils<ArrayList<User>>();
		ArrayList<User> list = f.getData(USERFILENAME);
		for (User uu : list) {
			if (user.getId().equals(uu.getId()) && uu.getIsDeleted().equals("0")) {
				return false;
			}
		}
//		if (u != null&&u.getIsDeleted().equals("0")) {
//			return false;
//		} else {
		list.add(user);
		f.writeData(list, USERFILENAME);

		return true;
//		}

	}

	/**
	 * 删除用户
	 * 
	 * @param id 一个{@code String}类型的对象，即要删除的用户的id
	 * @return 返回一个{@code boolean}对象，true表示成功，false表示不存在此用户
	 */
	public boolean removeUser(String id) {

		FileUtils<ArrayList<User>> f = new FileUtils<ArrayList<User>>();
		ArrayList<User> list = f.getData(USERFILENAME);
		User user = null;
		for (User uu : list) {
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
			f.writeData(list, USERFILENAME);

			return true;
		}

	}

	/**
	 * 修改用户
	 * 
	 * @param id      一个{@code String}类型的对象，即要修改的用户的id
	 * @param newUser 一个{@code User}类型的对象，即新的用户
	 * @return 返回一个{@code boolean}对象，true表示成功，false表示不存在此用户
	 */
	public boolean modifyUser(String id, User newUser) {
		FileUtils<ArrayList<User>> f = new FileUtils<ArrayList<User>>();
		ArrayList<User> list = f.getData(USERFILENAME);
		User user = null;
		for (User uu : list) {
			if (id.equals(uu.getId()) && uu.getIsDeleted().equals("0")) {
				user = uu;
			}
		}
		if (user == null) {
			return false;
		} else {
			list.remove(user);
			list.add(newUser);
			f.writeData(list, USERFILENAME);

			return true;
		}

	}

	/**
	 * 获取所有用户的集合
	 * 
	 * @return
	 */
	public List<User> getAll() {
		FileUtils<ArrayList<User>> f = new FileUtils<ArrayList<User>>();
		ArrayList<User> list = f.getData(USERFILENAME);
		List<User> list1 = new ArrayList<User>();
		for (User u : list) {
			list1.add(u);
		}
		return list1;
	}

	

	public void stopFac(String id) {
		FileUtils<ArrayList<User>> f = new FileUtils<ArrayList<User>>();
		ArrayList<User> list = f.getData(USERFILENAME);
		User user = null;
		for (User uu : list) {
			if (id.equals(uu.getId()) && uu.getIsDeleted().equals("0")) {
				user = uu;
			}
		}
		if (user == null) {
		} else {
			FacAdmin admin = (FacAdmin) user;
			list.remove(admin);
			if (admin.getState() == 1) {
				admin.setState(0);
			} else {
				admin.setState(1);
			}

			list.add(admin);
			f.writeData(list, USERFILENAME);

		}
	}
	
	/**
	 * 测试用，读文件并将读到的数据打印到控制台，可以选择初始化文件
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		FileUtils<ArrayList<User>> f = new FileUtils<ArrayList<User>>();
		ArrayList<User> list = new ArrayList<User>();
		list = f.getData(USERFILENAME);
		System.out.println("源文件中读取到的数据如下");
		if (list.size() == 0) {
			System.out.println("空的");
		} else {
			for (User u : list) {
				System.out.println(u.getClass().getSimpleName() + " " + u + " " + u.getUid());
			}
		}
		System.out.println("需要初始化文件，请输入yes");
		Scanner scan = new Scanner(System.in);
		if (scan.nextLine().equals("yes")) {
			list = new ArrayList<User>();
			f.writeData(list, USERFILENAME);
			FacAdmin facAdmin = new FacAdmin("11", "11", "zhy", "1233", "fac1", "testfac", new ArrayList<Device>());
			list.add(facAdmin);
			f.writeData(list, USERFILENAME);
			Dealer dealer = new Dealer("22", "22", "zhy2", "123333");
			list.add(dealer);
			f.writeData(list, USERFILENAME);
			System.out.println(USERFILENAME + "文件已初始化");
			scan.close();
		}

	}
}
