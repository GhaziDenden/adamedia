package com.gdconsulting.adamedia.model;

import java.util.Arrays;

public class Restaurant {

	public 	int id;
	public 	String name;
	public 	String address;
	public 	String city;
	public 	int zipcode;
	public 	String country;
	public 	String type;
	public 	String phone;
	public 	String email;
	public 	double latitude;
	public 	double longitude;

	public 	String thumb;
	public 	String[] images;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public String[] getImages() {
		return images;
	}
	public void setImages(String[] images) {
		this.images = images;
	}
	
	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", name=" + name + ", address="
				+ address + ", city=" + city + ", zipcode=" + zipcode
				+ ", country=" + country + ", type=" + type + ", phone="
				+ phone + ", email=" + email + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", thumb=" + thumb + ", images="
				+ Arrays.toString(images) + "]";
	}
	
}
