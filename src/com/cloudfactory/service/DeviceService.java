package com.cloudfactory.service;

import java.util.List;

import com.cloudfactory.entity.Device;

public interface DeviceService extends BaseService{
	public boolean addDeviceCenter(Device u);
	public boolean removeDeviceCenter(String s);
	public boolean modifyDeviceCenter(String s, Device u);
	List<Device> getAll();
	public Device findDevice(String id);
	public String findFac(String id);
	List<Device> getMyAll(String id);
	public boolean addMyDevice(Device u, String userID);
	public boolean removeMyDevice(String id, String userID);
	public boolean modifyMyDevice(String s, Device u, String userID);
	void opencloseDevice(String id);
	public List<Device> getRentableAll();
	public Boolean RentDevice(String id, String userID);
	public Boolean releaseRentDevice(String id, String userID);
	
}
