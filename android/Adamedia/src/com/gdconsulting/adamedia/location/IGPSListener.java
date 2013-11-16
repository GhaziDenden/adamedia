package com.gdconsulting.adamedia.location;

import android.location.Location;
import android.location.LocationListener;

public interface IGPSListener {
	  
	public Location getLastBestLocation(int minDistance, long minTime);
	public void setChangedLocationListener(LocationListener l);
	public void cancel();
}
