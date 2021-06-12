package com.cloudfactory.entity;

import java.util.ArrayList;

public class FacAdmin extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String facName;
	private String facDetail;
	private int state;// 1 表示正常，0表示关停
	private ArrayList<Device> deviceList;

	public String getFacDetail() {
		return facDetail;
	}

	public void setFacDetail(String facDetail) {
		this.facDetail = facDetail;
	}

	public String getFacName() {
		return facName;
	}

	public void setFacName(String facName) {
		this.facName = facName;
	}
	
	public FacAdmin(String id, String pwd, String name, String contact,String facDetail,ArrayList<Device> deviceList) {
		super(id, pwd, name, contact);
		this.state=1;
		this.setFacName(id+"的云工厂");
		this.setFacDetail("");
		this.deviceList=deviceList;
		this.facDetail=facDetail;
	}

	public FacAdmin(String id, String pwd, String name, String contact, String facName, String facDetail,ArrayList<Device> deviceList) {
		super(id, pwd, name, contact);
		this.state=1;
		this.setFacName(facName);
		this.setFacDetail(facDetail);
		this.deviceList=deviceList;
	}

	/**
	 * @return 1 正常 0 关停
	 */
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public ArrayList<Device> getDeviceList() {
		return deviceList;
	}

	public void setDeviceList(ArrayList<Device> deviceList) {
		this.deviceList = deviceList;
	}

}
