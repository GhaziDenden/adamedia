package com.gdconsulting.adamedia.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gdconsulting.adamedia.R;
import com.gdconsulting.adamedia.model.MenuItem;


public class MenuListAdapter extends ArrayAdapter {
  	 
    private Context context;
    private LayoutInflater vi;
    private int currentPosition;
    private ArrayList<MenuItem> currentList = null;
 
    public MenuListAdapter(Context context, ArrayList<MenuItem> currentList, int currentPosition) {
        super(context,0, currentList);
        this.context = context;
        this.currentList = currentList;
        this.currentPosition = currentPosition;
        vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    
    @Override
    public int getCount(){
    	return currentList.size();
    }
    
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
		
        View v = convertView;
        MenuItem current = currentList.get(position);
        
        if (current.isSection())
        	return createSectionCell(v, current);
        else return createValueCell(v, current, position);
        
    }
    
    
    private View createValueCell(View v, MenuItem item, int position){
    	v = vi.inflate(R.layout.row_menu, null);
   
    	final View bar = (View)v.findViewById(R.id.left_bar);
    	bar.setBackgroundColor(item.getBarColor());
    	
    	final TextView labelName = (TextView)v.findViewById(R.id.label);
    	labelName.setText(item.getLabel());
    	
    	if (position == currentPosition)
    		v.setBackgroundColor(0xFF99E5FF);
    	
    	return v;
    }
    
    
    private View createSectionCell(View v, MenuItem item){
    	v = vi.inflate(R.layout.row_menu_section, null);
    	v.setFocusable(false);
    	v.setClickable(false);
    	v.setBackgroundColor(0xFFCCCCCC);
    	
    	final TextView sectionView = (TextView) v.findViewById(R.id.list_item_section_text);
    	sectionView.setText(item.getLabel());
    	
    	return v;
    }
}
