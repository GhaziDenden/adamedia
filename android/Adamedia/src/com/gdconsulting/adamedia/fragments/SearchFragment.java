package com.gdconsulting.adamedia.fragments;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.gdconsulting.adamedia.R;
import com.gdconsulting.adamedia.adapters.RestaurantsListAdapter;
import com.gdconsulting.adamedia.apicall.ApiCall;
import com.gdconsulting.adamedia.apicall.OnApiCallCompleted;
import com.gdconsulting.adamedia.model.Restaurant;

public class SearchFragment extends Fragment {

	private ListView listView;
	private ArrayList<Restaurant> currentData = new ArrayList<Restaurant>();
	private RestaurantsListAdapter adapter;
	private boolean restaurantsShown = false;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		getActivity().setTitle("Search");
		
		View myFragmentView = inflater.inflate(R.layout.searchfragment,
				container, false);
		
		listView = (ListView)myFragmentView.findViewById(R.id.list);
		
		adapter = new RestaurantsListAdapter(getActivity(),currentData);
        listView.setAdapter(adapter);
        
		/*
		Button button = (Button) myFragmentView.findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
						.create();
				alertDialog.setTitle("Search");
				alertDialog.setMessage("Search Message");
				alertDialog.show();
			}
		});
		*/
		
        if (!restaurantsShown){
        	getRestaurants();
        	restaurantsShown = true;
        }
        
		return myFragmentView;
	}
	
	public void getRestaurants(){
		new ApiCall(getActivity(),"Restaurants",new Callback()).execute("http://192.168.1.53/adamedia/www/ws/searchRestaurant.php?type=1&name=res");
	}
	
	
	public class Callback implements OnApiCallCompleted{
	    @Override
	    public void onApiCallCompleted(String result) {
	        Log.e("onApiCallCompleted", "onApiCallCompleted");

	        JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(result);
		        
		    	String state = jsonObject.getString("state"); 
		    	JSONObject resultString = jsonObject.getJSONObject("result");
		    	String errors = jsonObject.getString("errors");
		    	
		    	if (state.equals("OK")){
		    		
		    		JSONArray restaurantsObject = new JSONArray(resultString.getString("restaurants"));
		    		
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
		                
		                JSONArray restaurantsImages = new JSONArray(json_restaurant.getString("images"));
		                String[] images = new String[restaurantsImages.length()];
		                
		                Log.e("restaurantsImages.length()", ""+restaurantsImages.length());
		                
		                for(int j=0;j<restaurantsImages.length();j++){
			                JSONObject json_restaurant_image = restaurantsImages.getJSONObject(j);
			                images[j] = json_restaurant_image.getString("id_image");
		                }
		                
		                restaurant.setImages(images);
			    		Log.e("ghazi state restaurant", restaurant.toString());
			    		currentData.add(restaurant);
		    		}
		    		
		    		adapter.notifyDataSetChanged();
		    		
		    	}else if (state.equals("KO")){
		    		Log.e("ghazi state", "KO");
		    	}
		    	
		    	
		    	
			} catch (JSONException e) {
				e.printStackTrace();
			}
	        
	    }
	    @Override
	    public void onApiCallError(String result) {
	        Log.e("onApiCallError", "onApiCallError");
	        Log.e("onApiCallError", ""+result);
	    }
	}

}
