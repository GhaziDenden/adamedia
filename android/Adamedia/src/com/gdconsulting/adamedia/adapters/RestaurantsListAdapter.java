package com.gdconsulting.adamedia.adapters;

import java.util.ArrayList;
import java.util.HashMap;

import com.gdconsulting.adamedia.R;
import com.gdconsulting.adamedia.model.Restaurant;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class RestaurantsListAdapter extends ArrayAdapter {
  	 
    private Context context;
    private LayoutInflater vi;
    private ArrayList<Restaurant> currentData = null;
 
    public RestaurantsListAdapter(Context context,ArrayList<Restaurant> currentData) {
        super(context,0, currentData);
        this.context = context;
        this.currentData = currentData;
        vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    
    @Override
    public int getCount(){
    	return currentData.size();
    }
    
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
		
        View v = convertView;
        
        Restaurant current = new Restaurant();
        current = currentData.get(position);
        
        v = vi.inflate(R.layout.row_restaurant, null);
    
    	TextView labelName = (TextView)v.findViewById(R.id.restaurant_name);
    	labelName.setText(current.getName());
    	
    	TextView labelDetail = (TextView)v.findViewById(R.id.restaurant_detail);
    	labelDetail.setText(current.getAddress());
    	
    	ImageView image = (ImageView)v.findViewById(R.id.image);
    	UrlImageViewHelper.setUrlDrawable(image, "http://192.168.1.53/adamedia/www/img/"+current.getThumb()+".png");
      	
        return v;
    }
    
    
}
