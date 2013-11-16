package com.gdconsulting.adamedia.model;

public class User {
	
	private int id;
	private String firstname;
	private String lasttname;
	private String email;
	private String phone;
	private String fb_uid;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLasttname() {
		return lasttname;
	}
	public void setLasttname(String lasttname) {
		this.lasttname = lasttname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFb_uid() {
		return fb_uid;
	}
	public void setFb_uid(String fb_uid) {
		this.fb_uid = fb_uid;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lasttname="
				+ lasttname + ", email=" + email + ", phone=" + phone
				+ ", fb_uid=" + fb_uid + "]";
	}
	
}
