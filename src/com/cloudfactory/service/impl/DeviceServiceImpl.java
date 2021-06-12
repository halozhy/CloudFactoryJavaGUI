package com.cloudfactory.service.impl;

import java.util.List;

import com.cloudfactory.dao.DeviceDaoImpl;
import com.cloudfactory.entity.Device;
import com.cloudfactory.service.DeviceService;

public class DeviceServiceImpl implements DeviceService{

	
	private static DeviceServiceImpl instance;

	private DeviceServiceImpl() {
	
	}

	/**
	 * 实现单例模式
	 * @return 返回DeviceServiceImpl这个对象
	 */
	public static DeviceServiceImpl getInstance() {
		if (instance == null) {
			instance = new DeviceServiceImpl();
		}
		return instance;
	}
	
	@Override
	public boolean addDeviceCenter(Device u) {
		return DeviceDaoImpl.getInstance().addDeviceCenter(u);
	}

	@Override
	public boolean removeDeviceCenter(String s) {
		return DeviceDaoImpl.getInstance().removeDeviceCenter(s);
	}

	@Override
	public boolean modifyDeviceCenter(String s, Device u) {
		return DeviceDaoImpl.getInstance().modifyDeviceCenter(s, u);
	}

	@Override
	public List<Device> getAll() {
		return DeviceDaoImpl.getInstance().getAll();
	}

	@Override
	public Device findDevice(String id) {
		return DeviceDaoImpl.getInstance().findDevice(id);
	}

	@Override
	public String findFac(String id) {
		return DeviceDaoImpl.getInstance().findFac(id);
	}
	
	@Override
	public List<Device> getMyAll(String id) {
		return DeviceDaoImpl.getInstance().getMyAll(id);
	}

	@Override
	public boolean addMyDevice(Device u, String userID) {
		return DeviceDaoImpl.getInstance().addMyDevice(u,userID);
	}

	@Override
	public boolean removeMyDevice(String id, String userID) {
		return DeviceDaoImpl.getInstance().removeMyDevice(id, userID);
	}

	@Override
	public boolean modifyMyDevice(String s, Device u, String userID) {
		return DeviceDaoImpl.getInstance().modifyMyDevice(s,u,userID);
	}
	
	@Override
	public void opencloseDevice(String id) {
		DeviceDaoImpl.getInstance().opencloseDevice(id);
	}

	@Override
	public List<Device> getRentableAll() {
		return DeviceDaoImpl.getInstance().getRentableAll();
	}

	@Override
	public Boolean RentDevice(String id, String userID) {
		return DeviceDaoImpl.getInstance().RentDevice(id, userID);
	}

	@Override
	public Boolean releaseRentDevice(String id, String userID) {
		return DeviceDaoImpl.getInstance().releaseRentDevice(id, userID);
	}

}
