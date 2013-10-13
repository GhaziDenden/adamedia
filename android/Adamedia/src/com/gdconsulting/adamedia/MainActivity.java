package com.gdconsulting.adamedia;

import android.os.Bundle;

import com.gdconsulting.adamedia.fragments.AboutFragment;
import com.gdconsulting.adamedia.fragments.FavoritesFragment;
import com.gdconsulting.adamedia.fragments.HomeFragment;
import com.gdconsulting.adamedia.fragments.LoginFragment;
import com.gdconsulting.adamedia.fragments.SearchFragment;
import com.gdconsulting.adamedia.fragments.SignupFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends BaseActivity {

	/*
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] menuTitles;
	*/
	
	private LoginFragment loginFragment = null;
	private SignupFragment signupFragment = null;
	private SearchFragment searchFragment = null;
	private HomeFragment homeFragment = null;
	private FavoritesFragment favoritesFragment = null;
	private AboutFragment aboutFragment = null;
	
	public MainActivity() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);

		
		// set the Behind View
				setBehindContentView(R.layout.menu_frame);
				getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.menu_frame, new MenuListFragment())
				.commit();
				
				// customize the SlidingMenu
				getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		
		switchContent(1);
		
		/*
		mTitle = mDrawerTitle = getTitle();
		menuTitles = new String[] { "Title 1", "Title 2", "Title 3" };
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		// set a custom shadow that overlays the main content when the drawer
		// opens
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, menuTitles));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		// enable ActionBar app icon to behave as action to toggle nav drawer
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, 
		mDrawerLayout, 
		R.drawable.ic_drawer, 
		R.string.drawer_open, 
		R.string.drawer_close 
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			selectItem(0);
		}
		*/
	}
	
	
	public void switchContent(int position) {

		switch(position){
			case 0:
				getFragmentManager().beginTransaction()
				.replace(R.id.content_frame, (loginFragment != null ? loginFragment : (loginFragment = new LoginFragment()))).commit();
				break;
			case 1:
				getFragmentManager().beginTransaction()
				.replace(R.id.content_frame, (homeFragment != null ? homeFragment : (homeFragment = new HomeFragment()))).commit();
				break;
			case 2:
				getFragmentManager().beginTransaction()
				.replace(R.id.content_frame, (searchFragment != null ? searchFragment : (searchFragment = new SearchFragment()))).commit();
				break;
			case 3:
				getFragmentManager().beginTransaction()
				.replace(R.id.content_frame, (searchFragment != null ? searchFragment : (searchFragment = new SearchFragment()))).commit();
				break;
			case 4:
				getFragmentManager().beginTransaction()
				.replace(R.id.content_frame, (favoritesFragment != null ? favoritesFragment : (favoritesFragment = new FavoritesFragment()))).commit();
				break;
			case 5:
				getFragmentManager().beginTransaction()
				.replace(R.id.content_frame, (aboutFragment != null ? aboutFragment : (aboutFragment = new AboutFragment()))).commit();
				break;
		}
		getSlidingMenu().showContent();
		
	}
	
/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the nav drawer is open, hide action items related to the content
		// view
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return true;
	}

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}

	private void selectItem(int position) {

		if (position == 1) {
			getFragmentManager().beginTransaction()
					.replace(R.id.content_frame, new LoginFragment()).commit();
		}

		mDrawerList.setItemChecked(position, true);
		setTitle(menuTitles[position]);
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}


	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
*/
}
