package com.cloudfactory.entity;

import java.io.Serializable;

import com.cloudfactory.util.IDGenerator;

public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;
//	序号、产品编号、产品名称、产品类别、产品规格和产品描述
	private String isDeleted;
	private int uid;
	private String id;
	private String name;
	private String type;
	private String guige;
	private String detail;
	
	public Product( String name, String type, String guige, String detail) {
		super();
		isDeleted="0";
		this.uid = IDGenerator.getProductUID();
		this.id = IDGenerator.getProductID();
		this.name = name;
		this.type = type;
		this.guige = guige;
		this.detail = detail;
	}
	
	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
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
	
	@Override
	public String toString() {
		return "Product [uid=" + uid + ", id=" + id + ", name=" + name + ", type=" + type + ", guige=" + guige
				+ ", detail=" + detail + "]";
	}
}
