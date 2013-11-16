package com.gdconsulting.adamedia;

import android.os.Bundle;

import com.gdconsulting.adamedia.fragments.AboutFragment;
import com.gdconsulting.adamedia.fragments.AroundMeFragment;
import com.gdconsulting.adamedia.fragments.FavoritesFragment;
import com.gdconsulting.adamedia.fragments.HomeFragment;
import com.gdconsulting.adamedia.fragments.LoginFragment;
import com.gdconsulting.adamedia.fragments.MenuListFragment;
import com.gdconsulting.adamedia.fragments.SearchFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends BaseActivity {

	private LoginFragment loginFragment = null;
	private SearchFragment searchFragment = null;
	private AroundMeFragment aroundmeFragment = null;
	private HomeFragment homeFragment = null;
	private FavoritesFragment favoritesFragment = null;
	private AboutFragment aboutFragment = null;
	
	public int currentSelectedPosition = 0;
	
	public MainActivity() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
		// set the Behind View
		setBehindContentView(R.layout.menu_frame);
		
		// customize the SlidingMenu
		getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

		switchContent(3);
		
	}
	
	
	public void createMenuListFragment(){
		
		getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.menu_frame, new MenuListFragment())
			.commit();
	}
	
	
	public void switchContent(int position) {

		currentSelectedPosition = position;
		createMenuListFragment();
		switch(position){
			case 1:
				getFragmentManager().beginTransaction()
				.replace(R.id.content_frame, (loginFragment != null ? loginFragment : (loginFragment = new LoginFragment()))).commit();
				break;
			case 3:
				getFragmentManager().beginTransaction()
				.replace(R.id.content_frame, (homeFragment != null ? homeFragment : (homeFragment = new HomeFragment()))).commit();
				break;
			case 4:
				getFragmentManager().beginTransaction()
				.replace(R.id.content_frame, (searchFragment != null ? searchFragment : (searchFragment = new SearchFragment()))).commit();
				break;
			case 5:
				getFragmentManager().beginTransaction()
				.replace(R.id.content_frame, (aroundmeFragment != null ? aroundmeFragment : (aroundmeFragment = new AroundMeFragment()))).commit();
				break;
			case 7:
				getFragmentManager().beginTransaction()
				.replace(R.id.content_frame, (favoritesFragment != null ? favoritesFragment : (favoritesFragment = new FavoritesFragment()))).commit();
				break;
			case 8:
				getFragmentManager().beginTransaction()
				.replace(R.id.content_frame, (aboutFragment != null ? aboutFragment : (aboutFragment = new AboutFragment()))).commit();
				break;
		}
		getSlidingMenu().showContent();
		
	}
	
}
