package com.cloudfactory.entity;

import java.io.Serializable;

import com.cloudfactory.util.IDGenerator;


public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String isDeleted;

	private int uid;
	private String id;
	private String pwd;
	private String name;
	private String contact;
	
	
	public User(String id, String pwd, String name, String contact) {
		isDeleted = "0";
		uid=IDGenerator.getUserUID();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.contact = contact;
	}


	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid=uid;
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

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		User e = (User) o;

		return id.equals(e.getId());
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
	

	@Override
	public String toString() {
		return "User [isDeleted=" + isDeleted + ", id=" + id + ", pwd=" + pwd + ", name=" + name + ", contact="
				+ contact + "]";
	}

}
