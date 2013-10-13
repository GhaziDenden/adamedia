package com.gdconsulting.adamedia;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.gdconsulting.adamedia.adapters.MenuListAdapter;
import com.gdconsulting.adamedia.model.MenuItem;

public class MenuListFragment extends ListFragment {

	ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		//String[] list = new String[] { "Account", "Login", "Home", "Home", "Search", "Around me", "Others", "Favorites", "About" };
		
		/*
		ArrayAdapter<String> colorAdapter = new ArrayAdapter<String>(getActivity(), 
				android.R.layout.simple_list_item_1, android.R.id.text1, list);
		*/
		
		MenuListAdapter adapter = new MenuListAdapter(getActivity(), createMenu());
        setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView lv, View v, int position, long id) {
		
		MenuItem item = menu.get(position);
		if (item.isSelectable()){
			MainActivity ma = (MainActivity) getActivity();
			ma.switchContent(position);
		}
	}

	
	private ArrayList<MenuItem> createMenu(){
		
		menu = new ArrayList<MenuItem>();
		menu.add(new MenuItem("Account",true,0xFFFFFFFF,false));
		menu.add(new MenuItem("Login",false,0xFFFF4500,true));
		menu.add(new MenuItem("Home",true,0xFFFFFFFF,false));
		menu.add(new MenuItem("Home",false,0xFF00BAFF,true));
		menu.add(new MenuItem("Search",false,0xFFAE5BD7,true));
		menu.add(new MenuItem("Around me",false,0xFF3D7B1E,true));
		menu.add(new MenuItem("Others",true,0xFFFFFFFF,false));
		menu.add(new MenuItem("Favorites",false,0xFFB35919,true));
		menu.add(new MenuItem("About",false,0xFF3C6190,true));
		return menu;
	}
	
}
