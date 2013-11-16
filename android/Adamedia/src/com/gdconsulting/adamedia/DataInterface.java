package com.gdconsulting.adamedia;

import com.gdconsulting.adamedia.model.Restaurant;
import com.gdconsulting.adamedia.model.User;

public class DataInterface {
	
	private volatile static DataInterface dataInstance;
	
	public String WSUrl = "http://192.168.1.53/adamedia/www/ws/";
	public String ImagesUrl = "http://192.168.1.53/adamedia/www/img/";
	
	private Restaurant currentRestaurant;
	private User currentUser;
	private boolean isConnected;
	
	private DataInterface() {
	}

	// Providing Global point of access
	public static synchronized DataInterface getSingletonInstance() {
		if (null == dataInstance) {
			synchronized (DataInterface.class){
					if (null == dataInstance) {
						dataInstance = new DataInterface();
					}
			}
		}
		return dataInstance;
	}

	public Restaurant getCurrentRestaurant() {
		return currentRestaurant;
	}

	public void setCurrentRestaurant(Restaurant currentRestaurant) {
		this.currentRestaurant = currentRestaurant;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public boolean isConnected() {
		return isConnected;
	}

	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}

}
