package com.gdconsulting.adamedia;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.gdconsulting.adamedia.fragments.RestaurantFragment;

public class RestaurantActivity extends FragmentActivity{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
		getFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame, new RestaurantFragment())
		.commit();
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

}
