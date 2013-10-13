package com.gdconsulting.adamedia.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gdconsulting.adamedia.R;

public class SignupFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		getActivity().setTitle("Signup");
		
		View myFragmentView = inflater.inflate(R.layout.signupfragment,
				container, false);


		return myFragmentView;
	}

}
