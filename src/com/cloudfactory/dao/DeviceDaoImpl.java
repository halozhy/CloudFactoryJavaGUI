package com.cloudfactory.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cloudfactory.entity.Device;
import com.cloudfactory.entity.FacAdmin;
import com.cloudfactory.entity.User;
import com.cloudfactory.util.FileUtils;

public class DeviceDaoImpl {
	public static final String DEVICEFILENAME = "DeviceCenterData.dat";// 存放数据的文件名
	public static final String USERFILENAME = "UserData.dat";// 存放用户数据的文件名

	private static DeviceDaoImpl instance;

	private DeviceDaoImpl() {
	}

	/**
	 * 实现单例模式
	 * 
	 * @return 返回DeviceDaoImpl这个对象
	 */
	public static DeviceDaoImpl getInstance() {
		if (instance == null) {
			instance = new DeviceDaoImpl();
		}
		return instance;
	}

	public boolean addDeviceCenter(Device device) {
		FileUtils<ArrayList<Device>> f = new FileUtils<ArrayList<Device>>();
		ArrayList<Device> list = f.getData(DEVICEFILENAME);
		for (Device uu : list) {
			if (device.getId().equals(uu.getId()) && uu.getIsDeleted().equals("0")) {
				return false;
			}
		}
		list.add(device);
		f.writeData(list, DEVICEFILENAME);

		return true;
	}

	public boolean removeDeviceCenter(String id) {
		FileUtils<ArrayList<Device>> f = new FileUtils<ArrayList<Device>>();
		ArrayList<Device> list = f.getData(DEVICEFILENAME);
		Device dd = null;
		for (Device uu : list) {
			if (id.equals(uu.getId()) && uu.getIsDeleted().equals("0")) {
				dd = uu;

			}
		}

		if (dd == null) {
			return false;
		} else {
			if (dd.getSource() == 2) {
				return false;
			}
			list.remove(dd);
			dd.setIsDeleted("1");// 逻辑删除，杜绝物理删除
			list.add(dd);
			f.writeData(list, DEVICEFILENAME);

			return true;
		}
	}

	public boolean modifyDeviceCenter(String id, Device newD) {
		FileUtils<ArrayList<Device>> f = new FileUtils<ArrayList<Device>>();
		ArrayList<Device> list = f.getData(DEVICEFILENAME);
		Device user = null;
		for (Device uu : list) {
			if (id.equals(uu.getId()) && uu.getIsDeleted().equals("0")) {
				user = uu;
			}
		}
		if (user == null) {
			return false;
		} else {
			list.remove(user);
			list.add(newD);
			f.writeData(list, DEVICEFILENAME);
			return true;
		}
	}

	public List<Device> getAll() {
		FileUtils<ArrayList<Device>> f = new FileUtils<ArrayList<Device>>();
		ArrayList<Device> list = f.getData(DEVICEFILENAME);
		List<Device> list1 = new ArrayList<Device>();
		for (Device u : list) {
			list1.add(u);
		}
		FileUtils<ArrayList<User>> f2 = new FileUtils<ArrayList<User>>();
		ArrayList<User> list2 = f2.getData(USERFILENAME);
		for (User u : list2) {
			if (u.getClass().getSimpleName().equals("FacAdmin")) {
				FacAdmin admin = (FacAdmin) u;
				ArrayList<Device> dList = admin.getDeviceList();
				for (Device d : dList) {
					list1.add(d);
				}
			}
		}
		return list1;

	}

	public List<Device> getRentableAll() {
		FileUtils<ArrayList<Device>> f = new FileUtils<ArrayList<Device>>();
		ArrayList<Device> list = f.getData(DEVICEFILENAME);
		List<Device> list1 = new ArrayList<Device>();
		for (Device u : list) {
			if (u.getSource() == 1 && (u.getState() == 0 || u.getState() == -1)) {
				list1.add(u);
			}

		}
		return list1;
	}

	public Boolean RentDevice(String id, String userID) {

		FileUtils<ArrayList<Device>> f = new FileUtils<ArrayList<Device>>();
		ArrayList<Device> list = f.getData(DEVICEFILENAME);
		Device dd = null;
		for (Device uu : list) {
			if (id.equals(uu.getId()) && uu.getSource() == 1 && uu.getIsDeleted().equals("0")) {
				dd = uu;
			}
		}

		if (dd == null) {
			return false;
		} else {
			dd.setSource(2);
			list.remove(dd);
			f.writeData(list, DEVICEFILENAME);
			DeviceDaoImpl.getInstance().addMyDevice(dd, userID);
			return true;
		}

	}

	public Boolean releaseRentDevice(String id, String userID) {

		FileUtils<ArrayList<User>> f = new FileUtils<ArrayList<User>>();
		ArrayList<User> userlist = f.getData(USERFILENAME);
		FacAdmin admin = null;
		ArrayList<Device> dList = null;
		Device device = null;
		int flag = 0;
		for (User u : userlist) {
			if (u.getClass().getSimpleName().equals("FacAdmin")) {
				admin = (FacAdmin) u;
				dList = admin.getDeviceList();
				for (Device d : dList) {
					if (id.equals(d.getId())) {
						flag = 1;
						device = d;
						break;
					}
				}
			}
		}
		if (flag == 1) {
			if (device.getState() == 1) {// 还在开机状态
				return false;
			} else {
				// 从user的list里面删除
				dList.remove(device);
				ArrayList<Device> arr = new ArrayList<Device>();
				for (Device dd : dList) {
					arr.add(dd);
				}
				userlist.remove(admin);
				admin.setDeviceList(arr);
				userlist.add(admin);
				f.writeData(userlist, USERFILENAME);
				// 改变状态
				device.setSource(1);
				// 写回center
				DeviceDaoImpl.getInstance().addDeviceCenter(device);
			}

		}
		return false;
	}

	public List<Device> getMyAll(String id) {
//		FileUtils<ArrayList<Device>> f = new FileUtils<ArrayList<Device>>();
//		ArrayList<Device> list = f.getData(DEVICEFILENAME);
		List<Device> list1 = new ArrayList<Device>();
//		for (Device u : list) {
//			list1.add(u);
//		}
		FileUtils<ArrayList<User>> f2 = new FileUtils<ArrayList<User>>();
		ArrayList<User> list2 = f2.getData("UserData.dat");
		for (User u : list2) {
			if (u.getId().equals(id)) {
				FacAdmin admin = (FacAdmin) u;
				ArrayList<Device> dList = admin.getDeviceList();
				for (Device d : dList) {
					list1.add(d);
				}
			}
		}
		return list1;

	}

	public Device findDevice(String id) {
		FileUtils<ArrayList<Device>> f = new FileUtils<ArrayList<Device>>();
		ArrayList<Device> list = f.getData(DEVICEFILENAME);
		for (Device u : list) {
			if (id.equals(u.getId())) {
				return u;
			}
		}
		FileUtils<ArrayList<User>> f2 = new FileUtils<ArrayList<User>>();
		ArrayList<User> list2 = f2.getData("UserData.dat");
		for (User u : list2) {
			if (u.getClass().getSimpleName().equals("FacAdmin")) {
				FacAdmin admin = (FacAdmin) u;
				ArrayList<Device> dList = admin.getDeviceList();
				for (Device d : dList) {
					if (id.equals(d.getId())) {
						return d;
					}
				}
			}
		}
		return null;
	}

	public String findFac(String id) {
		FileUtils<ArrayList<Device>> f = new FileUtils<ArrayList<Device>>();
		ArrayList<Device> list = f.getData(DEVICEFILENAME);
		for (Device u : list) {
			if (id.equals(u.getId())) {
				return "产能中心";
			}
		}
		FileUtils<ArrayList<User>> f2 = new FileUtils<ArrayList<User>>();
		ArrayList<User> list2 = f2.getData(USERFILENAME);
		for (User u : list2) {
			if (u.getClass().getSimpleName().equals("FacAdmin")) {
				FacAdmin admin = (FacAdmin) u;
				ArrayList<Device> dList = admin.getDeviceList();
				for (Device d : dList) {

					if (id.equals(d.getId())) {
						if (d.getSource() == 1) {
							return admin.getFacName();
						} else {
							return "产能中心";
						}
					}
				}
			}
		}

		return "";
	}

	public String findUserID(String id) {
		FileUtils<ArrayList<Device>> f = new FileUtils<ArrayList<Device>>();
		ArrayList<Device> list = f.getData(DEVICEFILENAME);
		for (Device u : list) {
			if (id.equals(u.getId())) {
				return "产能中心";
			}
		}
		FileUtils<ArrayList<User>> f2 = new FileUtils<ArrayList<User>>();
		ArrayList<User> list2 = f2.getData(USERFILENAME);
		for (User u : list2) {
			if (u.getClass().getSimpleName().equals("FacAdmin")) {
				FacAdmin admin = (FacAdmin) u;
				ArrayList<Device> dList = admin.getDeviceList();
				for (Device d : dList) {
					if (id.equals(d.getId())) {
						return admin.getId();
					}
				}
			}
		}
		return "";
	}

	public void opencloseDevice(String id) {
		Device device = null;
		int flag = 0;
		FileUtils<ArrayList<Device>> f = new FileUtils<ArrayList<Device>>();
		ArrayList<Device> list = f.getData(DEVICEFILENAME);
		for (Device u : list) {
			if (id.equals(u.getId())) {
				flag = 1;
				device = u;
				break;
			}
		}
		if (flag == 1) {
			if (device.getState() == 0) {
				device.setState(-1);
				DeviceDaoImpl.getInstance().modifyDeviceCenter(device.getId(), device);
			} else if (device.getState() == 1) {
				device.setState(-1);
				DeviceDaoImpl.getInstance().modifyDeviceCenter(device.getId(), device);
			} else if (device.getState() == -1) {
				device.setState(0);
				DeviceDaoImpl.getInstance().modifyDeviceCenter(device.getId(), device);
			}
		} else {
			String userID = null;
			FileUtils<ArrayList<User>> f2 = new FileUtils<ArrayList<User>>();
			ArrayList<User> list2 = f2.getData(USERFILENAME);
			for (User u : list2) {
				if (u.getClass().getSimpleName().equals("FacAdmin")) {
					FacAdmin admin = (FacAdmin) u;
					ArrayList<Device> dList = admin.getDeviceList();
					for (Device d : dList) {
						if (id.equals(d.getId())) {
							flag = 1;
							device = d;
							userID = admin.getId();
							break;
						}
					}
				}
			}
			if (flag == 1) {
				if (device.getState() == 0) {
					device.setState(-1);
					DeviceDaoImpl.getInstance().modifyMyDevice(device.getId(), device, userID);
				} else if (device.getState() == 1) {
					device.setState(-1);
					DeviceDaoImpl.getInstance().modifyMyDevice(device.getId(), device, userID);
				} else if (device.getState() == -1) {
					device.setState(0);
					DeviceDaoImpl.getInstance().modifyMyDevice(device.getId(), device, userID);
				}
			}
		}
	}

	/**
	 * 测试用，读文件并将读到的数据打印到控制台，可以选择初始化文件
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		FileUtils<ArrayList<Device>> f = new FileUtils<ArrayList<Device>>();
		ArrayList<Device> list = new ArrayList<Device>();
		list = f.getData(DEVICEFILENAME);
		System.out.println("源文件中读取到的数据如下");
		if (list.size() == 0) {
			System.out.println("空的");
		} else {
			for (Device u : list) {
				System.out.println(u.getClass().getSimpleName() + " " + u + " " + u.getUid());
			}
		}
		System.out.println("需要初始化文件，请输入yes");
		Scanner scan = new Scanner(System.in);
		if (scan.nextLine().equals("yes")) {
			list = new ArrayList<Device>();
			f.writeData(list, DEVICEFILENAME);

			list.add(new Device("设备1", "车床", "智能设备", "互联网智能设备", 0, 1));
			f.writeData(list, DEVICEFILENAME);

			System.out.println(DEVICEFILENAME + "文件已初始化");
			scan.close();
		}

	}

	public boolean addMyDevice(Device d, String userID) {
		FileUtils<ArrayList<User>> f = new FileUtils<ArrayList<User>>();
		ArrayList<User> list = f.getData(USERFILENAME);
		FacAdmin facAdmin = null;
		List<Device> userlist = null;
		int flag = 0;
		for (User uu : list) {
			if (userID.equals(uu.getId())) {
				flag = 1;
				facAdmin = (FacAdmin) uu;
				userlist = facAdmin.getDeviceList();
				for (Device dd : userlist) {
					if (d.getId().equals(dd.getId()) && dd.getIsDeleted().equals("0")) {
						return false;
					}
				}
				break;

			}
		}
		if (flag == 1) {
			userlist.add(d);
			ArrayList<Device> arr = new ArrayList<Device>();
			for (Device dd : userlist) {
				arr.add(dd);
			}
			list.remove(facAdmin);
			facAdmin.setDeviceList(arr);
			list.add(facAdmin);
			f.writeData(list, USERFILENAME);
			return true;

		} else {
			return false;
		}

	}

	public boolean removeMyDevice(String id, String userID) {
		FileUtils<ArrayList<User>> f = new FileUtils<ArrayList<User>>();
		ArrayList<User> list = f.getData(USERFILENAME);
		FacAdmin facAdmin = null;
		List<Device> userlist = null;
		int flag = 0;
		for (User uu : list) {
			if (userID.equals(uu.getId())) {
				flag = 1;
				facAdmin = (FacAdmin) uu;
				userlist = facAdmin.getDeviceList();
				break;

			}
		}
		if (flag == 1) {
			for (int i = 0; i < userlist.size(); i++) {
				if (userlist.get(i).getId().equals(id)) {
					userlist.get(i).setIsDeleted("1");// 逻辑删除
					break;
				}
			}

			ArrayList<Device> arr = new ArrayList<Device>();
			for (Device dd : userlist) {
				arr.add(dd);
			}
			list.remove(facAdmin);
			facAdmin.setDeviceList(arr);
			list.add(facAdmin);
			f.writeData(list, USERFILENAME);
			return true;

		} else {
			return false;
		}
	}

	public boolean modifyMyDevice(String s, Device newDevice, String userID) {
		FileUtils<ArrayList<User>> f = new FileUtils<ArrayList<User>>();
		ArrayList<User> list = f.getData(USERFILENAME);
		FacAdmin facAdmin = null;
		List<Device> userlist = null;
		int flag = 0;
		for (User uu : list) {
			if (userID.equals(uu.getId())) {
				flag = 1;
				facAdmin = (FacAdmin) uu;
				userlist = facAdmin.getDeviceList();
				break;

			}
		}
		if (flag == 1) {
			for (int i = 0; i < userlist.size(); i++) {
				if (userlist.get(i).getId().equals(s)) {
					userlist.remove(i);
					break;
				}
			}
			userlist.add(newDevice);
			ArrayList<Device> arr = new ArrayList<Device>();
			for (Device dd : userlist) {
				arr.add(dd);
			}
			list.remove(facAdmin);
			facAdmin.setDeviceList(arr);
			list.add(facAdmin);
			f.writeData(list, USERFILENAME);
			return true;

		} else {
			return false;
		}
	}
}
