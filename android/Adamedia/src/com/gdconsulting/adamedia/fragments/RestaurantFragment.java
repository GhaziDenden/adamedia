package com.gdconsulting.adamedia.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.gdconsulting.adamedia.DataInterface;
import com.gdconsulting.adamedia.MapActivity;
import com.gdconsulting.adamedia.R;
import com.gdconsulting.adamedia.adapters.DetailRestaurantExpandableListAdapter;
import com.gdconsulting.adamedia.model.Restaurant;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

public class RestaurantFragment extends Fragment {

	private Restaurant currentRestaurant = null;
	private TextView RestaurantName;
	private TextView RestaurantAddress;
	private TextView RestaurantPhone;
	private TextView RestaurantMail;
	private ImageView RestaurantImage;
	private ExpandableListView detailsListView;
	private DetailRestaurantExpandableListAdapter detailsListAdapter;
	
	private Button RestaurantCall;
	private Button RestaurantEmail;
	
	private String currentAddress = "";
    List<String> listDataHeader = new ArrayList<String>();
    HashMap<String, List<String>> listDataChild = new HashMap<String, List<String>>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		currentRestaurant = DataInterface.getSingletonInstance().getCurrentRestaurant();
		
		setHasOptionsMenu(true);
		getActivity().setTitle(currentRestaurant.getName());
		
		View myFragmentView = inflater.inflate(R.layout.restaurantfragment, container, false);
		
		RestaurantCall = (Button)myFragmentView.findViewById(R.id.restaurant_call_action);
		RestaurantCall.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				RestaurantCall();
			}
		});
		RestaurantEmail = (Button)myFragmentView.findViewById(R.id.restaurant_email_action);
		RestaurantEmail.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				RestaurantEmail();
			}
		});
		
		RestaurantName = (TextView)myFragmentView.findViewById(R.id.restaurant_name);
		RestaurantAddress = (TextView)myFragmentView.findViewById(R.id.restaurant_address);
		RestaurantPhone = (TextView)myFragmentView.findViewById(R.id.restaurant_phone);
		RestaurantMail = (TextView)myFragmentView.findViewById(R.id.restaurant_email);
		RestaurantImage = (ImageView)myFragmentView.findViewById(R.id.restaurant_image);
		
		
		if (!currentRestaurant.getAddress().equals("")) currentAddress = currentAddress.concat(currentRestaurant.getAddress());
		if (currentRestaurant.getZipcode() != 0) currentAddress = currentAddress.concat(", "+currentRestaurant.getZipcode());
		if (!currentRestaurant.getCity().equals("")) currentAddress = currentAddress.concat(", "+currentRestaurant.getCity());
		if (!currentRestaurant.getCountry().equals("")) currentAddress = currentAddress.concat(", "+currentRestaurant.getCountry());
		
		RestaurantName.setText(currentRestaurant.getName());
		RestaurantAddress.setText(currentAddress);
		RestaurantPhone.setText(currentRestaurant.getPhone());
		RestaurantMail.setText(currentRestaurant.getEmail());
		
    	UrlImageViewHelper.setUrlDrawable(RestaurantImage, DataInterface.getSingletonInstance().ImagesUrl+currentRestaurant.getThumb()+".png");
      	
    	detailsListView = (ExpandableListView)myFragmentView.findViewById(R.id.restaurant_details);
    	listDataHeader = currentRestaurant.getListDataGroup();
    	listDataChild = currentRestaurant.getListDataItems();
 
        detailsListAdapter = new DetailRestaurantExpandableListAdapter(getActivity(), listDataHeader, listDataChild);
        detailsListView.setAdapter(detailsListAdapter);
		
		return myFragmentView;
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	    inflater.inflate(R.menu.restaurant_menu, menu);
	    super.onCreateOptionsMenu(menu,inflater);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    	case R.id.airport_menuShare:
	    		RestaurantShare();
	    		return true;
	    	case R.id.airport_menuMap:
	    		RestaurantMap();
	    		return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
	
	public void RestaurantAction(View v){
		if (v.getId() == R.id.restaurant_call_action){
			RestaurantCall();
		}else if (v.getId() == R.id.restaurant_email_action){
			RestaurantEmail();
		}
	}
	
	
	private void RestaurantEmail() {
		Intent emailIntent = new Intent(Intent.ACTION_SEND);
		emailIntent.setType("text/html");
		emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {currentRestaurant.getEmail()});
		startActivity(Intent.createChooser(emailIntent, currentRestaurant.getName())); 
	}

	private void RestaurantShare() {
		Intent shareIntent = new Intent(Intent.ACTION_SEND);
		shareIntent.setType("text/plain");
		shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Great Restaurant");
		shareIntent.putExtra(Intent.EXTRA_TEXT, currentRestaurant.getName());
		startActivity(Intent.createChooser(shareIntent, currentRestaurant.getName())); 
	}
	
	private void RestaurantCall() {
		startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:"+currentRestaurant.getPhone())));
	}

	private void RestaurantMap() {
		getActivity().startActivity(new Intent(getActivity(), MapActivity.class));
	}
}
