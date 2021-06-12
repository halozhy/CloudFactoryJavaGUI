package com.cloudfactory.entity;

import java.io.Serializable;
import java.util.Date;

import com.cloudfactory.util.IDGenerator;

public class Order implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
//	订单编号（自动生成）产品名称、订购数量，交付日期，投标截止日期，收货人、收货人联系方式、收货地址、订单状态
	private String isDeleted;
	private int uid;
	private String name;
	private Date jiaofuDate;
	private Date jiezhiDate;
	private int state;
	private String receiverName;
	private String receiverContact;
	private String receiverAddress;
	
	public Order(String isDeleted, int uid, String name, Date jiaofuDate, Date jiezhiDate,String receiverName, String receiverContact, String receiverAddress) {
		super();
		this.isDeleted = "0";
		this.uid = IDGenerator.getOrderUID();
		this.name = name;
		this.jiaofuDate = jiaofuDate;
		this.jiezhiDate = jiezhiDate;
		this.setState(0);
		this.receiverName = receiverName;
		this.receiverContact = receiverContact;
		this.receiverAddress = receiverAddress;		
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getJiaofuDate() {
		return jiaofuDate;
	}
	public void setJiaofuDate(Date jiaofuDate) {
		this.jiaofuDate = jiaofuDate;
	}
	public Date getJiezhiDate() {
		return jiezhiDate;
	}
	public void setJiezhiDate(Date jiezhiDate) {
		this.jiezhiDate = jiezhiDate;
	}
	
	/**
	 * @return 0 已保存 1 已发布 2 已中标 3 已发货 4 已确认
	 */
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverContact() {
		return receiverContact;
	}
	public void setReceiverContact(String receiverContact) {
		this.receiverContact = receiverContact;
	}
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	
}
