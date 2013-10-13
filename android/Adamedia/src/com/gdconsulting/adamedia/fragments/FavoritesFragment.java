package com.gdconsulting.adamedia.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gdconsulting.adamedia.R;

public class FavoritesFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		getActivity().setTitle("Favorites");
		
		View myFragmentView = inflater.inflate(R.layout.favoritesfragment,
				container, false);

		Button button = (Button) myFragmentView.findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
						.create();
				alertDialog.setTitle("Favorites");
				alertDialog.setMessage("Favorites Message");
				alertDialog.show();
			}
		});

		return myFragmentView;
	}

}
