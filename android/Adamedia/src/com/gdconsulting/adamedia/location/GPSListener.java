package com.gdconsulting.adamedia.location;

import java.util.List;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class GPSListener implements IGPSListener {
	
	protected static String TAG = "PreGingerbreadLastLocationFinder";
	  
	  protected LocationListener locationListener;
	  protected LocationManager locationManager;
	  protected Criteria criteria;
	  protected Context context;
	  public boolean gps_enabled=false;
	  
	  
	  public GPSListener(Context context) {
	    locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
	    gps_enabled=locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        
	    criteria = new Criteria();
	    criteria.setAccuracy(Criteria.ACCURACY_COARSE);
	    this.context = context;
	  }
	  
	  
	  public Location getLastBestLocation(int minDistance, long minTime) {
	    Location bestResult = null;
	    float bestAccuracy = Float.MAX_VALUE;
	    long bestTime = Long.MAX_VALUE;
	    
	    Log.i("enter ", "getLastBestLocation");
	   
	    List<String> matchingProviders = locationManager.getAllProviders();
	    for (String provider: matchingProviders) {
	      Location location = locationManager.getLastKnownLocation(provider);
	      if (location != null) {
	        float accuracy = location.getAccuracy();
	        long time = location.getTime();
	        
	        if ((time < minTime && accuracy < bestAccuracy)) {
	          bestResult = location;
	          bestAccuracy = accuracy;
	          bestTime = time;
	        }
	        else if (time > minTime && bestAccuracy == Float.MAX_VALUE && time < bestTime) {
	          bestResult = location;
	          bestTime = time;
	        }
	      }
	    }
	    
	    Long bestTime_long = bestTime/1000;
	    Long currentTime_long = System.currentTimeMillis()/1000;
	    
	    if(currentTime_long > bestTime_long+900) {
	    	
	    	String provider = locationManager.getBestProvider(criteria, true);
		      if (provider != null)
		        locationManager.requestLocationUpdates(provider, 0, 0, singeUpdateListener, context.getMainLooper());
	    	
		    return null;
		      
	    }else return bestResult;
	 
	  }
	  
	  
	  protected LocationListener singeUpdateListener = new LocationListener() {
	    public void onLocationChanged(Location location) {
	      Log.d(TAG, "Single Location Update Received: " + location.getLatitude() + "," + location.getLongitude());
	      if (locationListener != null && location != null)
	        locationListener.onLocationChanged(location);
	      locationManager.removeUpdates(singeUpdateListener);
	    }
	    
	    public void onStatusChanged(String provider, int status, Bundle extras) {}
	    public void onProviderEnabled(String provider) {}    
	    public void onProviderDisabled(String provider) {}
	  };
	  
	  
	  public void setChangedLocationListener(LocationListener l) {
	    locationListener = l;
	  }
	  
	  public void cancel() {
	    locationManager.removeUpdates(singeUpdateListener);
	  }
}
