package com.gdconsulting.adamedia.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.gdconsulting.adamedia.DataInterface;
import com.gdconsulting.adamedia.R;
import com.gdconsulting.adamedia.RestaurantActivity;
import com.gdconsulting.adamedia.adapters.RestaurantsListAdapter;
import com.gdconsulting.adamedia.apicall.ApiCall;
import com.gdconsulting.adamedia.apicall.ApiResult;
import com.gdconsulting.adamedia.apicall.OnApiCallCompleted;
import com.gdconsulting.adamedia.location.GPSListener;
import com.gdconsulting.adamedia.model.Restaurant;

public class AroundMeFragment extends Fragment {

	private ListView listView;
	private ArrayList<Restaurant> currentData = new ArrayList<Restaurant>();
	private RestaurantsListAdapter adapter;
	private boolean restaurantsShown = false;
	private Menu optionsMenu;
	
	public Location currentLocation = null;
	public GPSListener gpsListener;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		setHasOptionsMenu(true);
		getActivity().setTitle("Around Me");
		gpsListener = new GPSListener(getActivity());
		
		View myFragmentView = inflater.inflate(R.layout.aroundmefragment,
				container, false);
		
		listView = (ListView)myFragmentView.findViewById(R.id.list);
		
		adapter = new RestaurantsListAdapter(getActivity(),currentData);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
				DataInterface.getSingletonInstance().setCurrentRestaurant(currentData.get(position));
				getActivity().startActivity(new Intent(getActivity(), RestaurantActivity.class));
			}
        	
        });
		
        if (!restaurantsShown){
        	getCurrentPosition();
        	restaurantsShown = true;
        }
        
		return myFragmentView;
	}
	
	
	private void getCurrentPosition() {
    	
    	Location location = gpsListener.getLastBestLocation(200, 0);
    	if (location != null){
    		currentLocation = location;
    		Log.i("currentLocation", Double.toString(location.getLatitude())+" : "+Double.toString(location.getLongitude()));
    		getRestaurants();
    	}else gpsListener.setChangedLocationListener(locationListenerGps);
        
    }

	
	public void getRestaurants(){
		new ApiCall(getActivity(),"Around Me",new Callback()).execute(DataInterface.getSingletonInstance().WSUrl+"searchRestaurant.php?type=1&name=res");
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		this.optionsMenu = menu;
	    inflater.inflate(R.menu.search_menu, menu);
	    super.onCreateOptionsMenu(menu,inflater);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    	case R.id.airport_menuRefresh:
	    		getCurrentPosition();
	    		return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
	
	public void setRefreshActionButtonState(final boolean refreshing) {
	    if (optionsMenu != null) {
	        final MenuItem refreshItem = optionsMenu.findItem(R.id.airport_menuRefresh);
	        if (refreshItem != null) {
	            if (refreshing) {
	                refreshItem.setActionView(R.layout.actionbar_indeterminate_progress);
	            } else {
	                refreshItem.setActionView(null);
	            }
	        }
	    }
	}
	
	
	public class Callback implements OnApiCallCompleted{
	    @Override
	    public void onApiCallCompleted(ApiResult result) {
	        Log.e("onApiCallCompleted", "onApiCallCompleted");
	        
	        JSONObject jsonObject = result.getData();
	        
			try {

	    		currentData.clear();
	    		JSONArray restaurantsObject = new JSONArray(jsonObject.getString("restaurants"));
	    		
	    		for(int i=0;i<restaurantsObject.length();i++){
	                JSONObject json_restaurant = restaurantsObject.getJSONObject(i);
	                
	                Restaurant restaurant = new Restaurant();
	                restaurant.setId(json_restaurant.getInt("id_restaurant"));
	                restaurant.setName(json_restaurant.getString("name"));
	                restaurant.setAddress(json_restaurant.getString("address"));
	                restaurant.setCity(json_restaurant.getString("city"));
	                restaurant.setZipcode(json_restaurant.getInt("zipcode"));
	                restaurant.setCountry(json_restaurant.getString("country"));
	                restaurant.setType(json_restaurant.getString("type"));
	                restaurant.setPhone(json_restaurant.getString("phone"));
	                restaurant.setEmail(json_restaurant.getString("email"));
	                restaurant.setLatitude(json_restaurant.getDouble("latitude"));
	                restaurant.setLongitude(json_restaurant.getDouble("longitude"));
	                restaurant.setThumb(json_restaurant.getString("thumb"));
	                
    		    	if (!json_restaurant.getString("images").equals("")){
	                
		                JSONArray restaurantsImages = new JSONArray(json_restaurant.getString("images"));
		                String[] images = new String[restaurantsImages.length()];
		                
		                Log.e("restaurantsImages.length()", ""+restaurantsImages.length());
		                
		                for(int j=0;j<restaurantsImages.length();j++){
			                JSONObject json_restaurant_image = restaurantsImages.getJSONObject(j);
			                images[j] = json_restaurant_image.getString("id_image");
		                }
		                
		                restaurant.setImages(images);
    		    	
    		    	}
    		    	
    		    	if (!json_restaurant.getString("details").equals("")){
	                
		                JSONArray restaurantsDetails = new JSONArray(json_restaurant.getString("details"));
		                List<String> groups = new ArrayList<String>();
		                HashMap<String, List<String>> ListItems = new HashMap<String, List<String>>();
		                
		                Log.e("restaurantsDetails.length()", ""+restaurantsDetails.length());
		                
		                for(int j=0;j<restaurantsDetails.length();j++){
			                JSONObject json_restaurant_group = restaurantsDetails.getJSONObject(j);
			                groups.add(json_restaurant_group.getString("name"));
			                
			                List<String> items = new ArrayList<String>();
				            if (!json_restaurant_group.getString("items").equals("")){
			                	JSONArray restaurantsItems = new JSONArray(json_restaurant_group.getString("items"));
			                	 
			                	for(int k=0;k<restaurantsItems.length();k++){
					                JSONObject json_restaurant_item = restaurantsItems.getJSONObject(k);
					                items.add(json_restaurant_item.getString("name"));
			                	}
			                }
			                
			                ListItems.put(json_restaurant_group.getString("name"), items);
		                }
		                
		                restaurant.setListDataGroup(groups);
		                restaurant.setListDataItems(ListItems);
    		    	
    		    	}
		    		currentData.add(restaurant);
	    		}
	    		
	    		Log.e("currentData", ""+currentData);
	    		adapter.notifyDataSetChanged();
		    		
			} catch (JSONException e) {
				e.printStackTrace();
			}
	        
	    }
	    @Override
	    public void onApiCallError(ApiResult result) {
	        Log.e("onApiCallError", "onApiCallError");
	        Log.e("onApiCallError", ""+result.toString());
	    }
	}
	
	
	
	LocationListener locationListenerGps = new LocationListener() {
        public void onLocationChanged(Location location) {
        	Log.i("enter get location", "locationListenerGps");
        	Log.i("currentLocation", Double.toString(location.getLatitude())+" : "+Double.toString(location.getLongitude()));
    		currentLocation = location;
    		getRestaurants();
        }
        public void onProviderDisabled(String provider) {}
        public void onProviderEnabled(String provider) {}
        public void onStatusChanged(String provider, int status, Bundle extras) {}
    };

}
