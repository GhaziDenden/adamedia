package com.gdconsulting.adamedia.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdconsulting.adamedia.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapViewFragment extends SupportMapFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		getActivity().setTitle("Map");
		
		View myFragmentView = inflater.inflate(R.layout.mapfragment,
				container, false);
		
		GoogleMap map = ((SupportMapFragment) getFragmentManager()
                .findFragmentById(R.id.map)).getMap();

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(41.889, -87.622), 16));

        map.addMarker(new MarkerOptions()
                .anchor(0.0f, 1.0f) 
                .position(new LatLng(41.889, -87.622)));
		
		return myFragmentView;
	}

}
