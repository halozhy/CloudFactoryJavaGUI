package com.cloudfactory.controllers;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.cloudfactory.factory.MyServiceFactory;
import com.cloudfactory.service.DeviceService;
import com.cloudfactory.entity.Device;

public class DeviceController extends BaseController {
	private static DeviceController instance;
	private DeviceService deviceService;

	/**
	 * 实现单例模式
	 * 
	 * @return 返回DeviceServiceImpl这个对象
	 */
	public static DeviceController getInstance(String message) {
		if (instance == null) {
			instance = new DeviceController(message);
		}
		return instance;
	}

	private DeviceController(String message) {
		super(message);
		deviceService = (DeviceService) MyServiceFactory.createService(message);
	}

	public boolean addDeviceCenter(Device u) {
		return deviceService.addDeviceCenter(u);
	}

	public boolean removeDeviceCenter(String id) {
		return deviceService.removeDeviceCenter(id);
	}

	public boolean modifyDeviceCenter(String s, Device u) {
		return deviceService.modifyDeviceCenter(s, u);
	}

	public boolean addMyDevice(Device u, String userID) {
		return deviceService.addMyDevice(u, userID);
	}

	public boolean removeMyDevice(String id, String userID) {
		return deviceService.removeMyDevice(id, userID);
	}

	public boolean modifyMyDevice(String s, Device u, String userID) {
		return deviceService.modifyMyDevice(s, u, userID);
	}

	public String[][] getAll() {

		List<Device> list = deviceService.getAll();
		Collections.sort(list, new Comparator<Device>() {

			@Override
			public int compare(Device o1, Device o2) {
				// 返回值为int类型，大于0表示正序，小于0表示逆序
				if (o1.getUid() - o2.getUid() > 0) {
					return 1;
				} else if (o1.getUid() - o2.getUid() == 0) {
					return 0;
				}
				return -1;
			}
		});
		int cnt = 0;
		for (Device u : list) {
			if (u.getIsDeleted().equals("0")) {
				cnt++;
			}
		}
//		序号、设备编号、设备名称、设备类型、设备规格、设备描述、租用状态、设备状态、所属工厂、操作
		String[][] data = new String[cnt][10];
		int i = 0;
		for (Device u : list) {
			if (u.getIsDeleted().equals("0")) {

				data[i][0] = String.valueOf(i + 1);
				data[i][1] = u.getId();
				data[i][2] = u.getName();
				data[i][3] = u.getType();
				data[i][4] = u.getGuige();
				data[i][5] = u.getDetail();

				if (u.getState() == 0) {
					data[i][6] = "闲置";
				} else if (u.getState() == 1) {
					data[i][6] = "开机";
				} else if (u.getState() == -1) {
					data[i][6] = "关闭";
				}

				if (u.getSource() == 1) {
					data[i][7] = "自有";
				} else if (u.getSource() == 2) {
					data[i][7] = "被租";
				}

				data[i][8] = findFac(u.getId());// 所属工厂
				data[i][9] = "";
				i++;
			}

		}
		return data;
	}

	public String[][] getMyAll(String id) {

		List<Device> list = deviceService.getMyAll(id);
		Collections.sort(list, new Comparator<Device>() {

			@Override
			public int compare(Device o1, Device o2) {
				// 返回值为int类型，大于0表示正序，小于0表示逆序
				if (o1.getUid() - o2.getUid() > 0) {
					return 1;
				} else if (o1.getUid() - o2.getUid() == 0) {
					return 0;
				}
				return -1;
			}
		});
		int cnt = 0;
		for (Device u : list) {
			if (u.getIsDeleted().equals("0")) {
				cnt++;
			}
		}
//		序号、设备编号、设备名称、设备类型、设备规格、设备描述、租用状态、设备状态、所属工厂、操作
		String[][] data = new String[cnt][10];
		int i = 0;
		for (Device u : list) {
			if (u.getIsDeleted().equals("0")) {
//			data[i][0]=String.valueOf(u.getUid());
				data[i][0] = String.valueOf(i + 1);
				data[i][1] = u.getId();
				data[i][2] = u.getName();
				data[i][3] = u.getType();
				data[i][4] = u.getGuige();
				data[i][5] = u.getDetail();

				if (u.getState() == 0) {
					data[i][6] = "闲置";
				} else if (u.getState() == 1) {
					data[i][6] = "开机";
				} else if (u.getState() == -1) {
					data[i][6] = "关闭";
				}

				if (u.getSource() == 1) {
					data[i][7] = "自有";
				} else if (u.getSource() == 2) {
					data[i][7] = "租用";
				}

				data[i][8] = findFac(u.getId());// 所属工厂
				data[i][9] = "";
				i++;
			}
		}
		return data;
	}

	public Device findDeviceCenter(String id) {
		return deviceService.findDevice(id);
	}

	public String findFac(String id) {
		return deviceService.findFac(id);
	}

	public void opencloseDevice(String id) {
		deviceService.opencloseDevice(id);
	}

	public String[][] getRentableAll() {
		List<Device> list = deviceService.getRentableAll();
		Collections.sort(list, new Comparator<Device>() {

			@Override
			public int compare(Device o1, Device o2) {
				// 返回值为int类型，大于0表示正序，小于0表示逆序
				if (o1.getUid() - o2.getUid() > 0) {
					return 1;
				} else if (o1.getUid() - o2.getUid() == 0) {
					return 0;
				}
				return -1;
			}
		});
		int cnt = 0;
		for (Device u : list) {
			if (u.getIsDeleted().equals("0")) {
				cnt++;
			}
		}
//		{ "序号", "设备编号", "设备名称", "设备类型", "设备描述", "设备状态","操作" }
		String[][] data = new String[cnt][7];
		int i = 0;
		for (Device u : list) {
			if (u.getIsDeleted().equals("0")) {
//			data[i][0]=String.valueOf(u.getUid());
				data[i][0] = String.valueOf(i + 1);
				data[i][1] = u.getId();
				data[i][2] = u.getName();
				data[i][3] = u.getType();
				data[i][4] = u.getDetail();

				if (u.getState() == 0) {
					data[i][5] = "闲置";
				} else if (u.getState() == 1) {
					data[i][5] = "开机";
				} else if (u.getState() == -1) {
					data[i][5] = "关闭";
				}

				data[i][6] = "";
				i++;
			}
		}
		return data;
	}

	public Boolean RentDevice(String id, String userID) {
		return deviceService.RentDevice(id, userID);
	}

	public Boolean releaseRentDevice(String id, String userID) {
		return deviceService.releaseRentDevice(id, userID);
	}

}
