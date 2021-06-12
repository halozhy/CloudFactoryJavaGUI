package com.cloudfactory.entity;

import java.io.Serializable;

import com.cloudfactory.util.IDGenerator;

public class Device implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5L;
//	设备编号
//	设备名称
//	设备类型
//	设备规格
//	设备描述
//	序号
//	设备状态
//	设备来源
	private String isDeleted;
	private int uid;
	private String id;
	private String name;
	private String type;
	private String guige;
	private String detail;
	private int state;
	//0 闲置 1 开机生产 -1 关闭
	private int source;
	//1 自有 2 租用
	
	
	
	
	public Device(String name, String type, String guige, String detail, int state,
			int source) {
		super();
		this.uid=IDGenerator.getDeviceUID();
		this.isDeleted = "0";
		this.id = IDGenerator.getDeviceID();
		this.name = name;
		this.type = type;
		this.guige = guige;
		this.detail = detail;
		this.state = 0;
		this.source = source;
	}
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGuige() {
		return guige;
	}
	public void setGuige(String guige) {
		this.guige = guige;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	/**
	 * @return 0 闲置 1 开机生产 -1 关闭
	 */
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	/**
	 * @return 1 自有 2 租用
	 */
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
	}
	@Override
	public String toString() {
		return "Device [isDeleted=" + isDeleted + ", id=" + id + ", name=" + name + ", type=" + type + ", guige="
				+ guige + ", detail=" + detail + ", state=" + state + ", source=" + source + "]";
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	

}
