package com.gdconsulting.adamedia.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.gdconsulting.adamedia.R;
import com.gdconsulting.adamedia.apicall.ApiCall;
import com.gdconsulting.adamedia.apicall.OnApiCallCompleted;

public class LoginFragment extends Fragment {

	private EditText emailEditText;
	private EditText passwordEditText;
	private Button signinButton;
	private Button signupButton;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		getActivity().setTitle("Login");
		
		View myFragmentView = inflater.inflate(R.layout.loginfragment,
				container, false);
		
		emailEditText = (EditText)myFragmentView.findViewById(R.id.email);
		passwordEditText = (EditText)myFragmentView.findViewById(R.id.password);
		signinButton = (Button)myFragmentView.findViewById(R.id.signin);
		signupButton = (Button)myFragmentView.findViewById(R.id.signup);
		
		
		signinButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				validateFields();
			}
			
		});
		
		return myFragmentView;
	}

	
	protected void validateFields() {
		
		boolean valid = true;
		if (emailEditText.getText().toString().equals("")){
			emailEditText.setError("Email field should not be empty");
			valid = false;
		}
		if (passwordEditText.getText().toString().equals("")){
			passwordEditText.setError("Password field should not be empty");
			valid = false;
		}
		
		if (valid){
			
		}
	}


	public void callApiLogin(){
		new ApiCall(getActivity(),"Login",new Callback()).execute("http://192.168.1.53/adamedia/www/ws/userConnect.php");
	}
	
	public class Callback implements OnApiCallCompleted{
	    @Override
	    public void onApiCallCompleted(String result) {
	        Log.e("onApiCallCompleted", "onApiCallCompleted");
	        Log.e("onApiCallCompleted", ""+result);
	    }
	    @Override
	    public void onApiCallError(String result) {
	        Log.e("onApiCallError", "onApiCallError");
	        Log.e("onApiCallError", ""+result);
	    }
	}
	
}
