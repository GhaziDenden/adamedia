package com.gdconsulting.adamedia.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.gdconsulting.adamedia.R;

public class SignupFragment extends Fragment {

	private EditText firstnameEditText;
	private EditText lastnameEditText;
	private EditText emailEditText;
	private EditText passwordEditText;
	private EditText passwordConfirmationEditText;
	private EditText phoneEditText;

	private Button signupButton;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		getActivity().setTitle("Signup");
		
		View myFragmentView = inflater.inflate(R.layout.signupfragment,
				container, false);

		firstnameEditText = (EditText)myFragmentView.findViewById(R.id.firstname);
		lastnameEditText = (EditText)myFragmentView.findViewById(R.id.lastname);
		emailEditText = (EditText)myFragmentView.findViewById(R.id.email);
		passwordEditText = (EditText)myFragmentView.findViewById(R.id.password);
		passwordConfirmationEditText = (EditText)myFragmentView.findViewById(R.id.password_confirmation);
		phoneEditText = (EditText)myFragmentView.findViewById(R.id.phone);

		signupButton = (Button)myFragmentView.findViewById(R.id.signup);
		signupButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				validateFields();
			}
			
		});
		
		return myFragmentView;
	}

protected void validateFields() {
	
		firstnameEditText.setError(null);
		lastnameEditText.setError(null);
		emailEditText.setError(null);
		passwordEditText.setError(null);
		passwordConfirmationEditText.setError(null);
			
		boolean valid = true;
		if (firstnameEditText.getText().toString().equals("")){
			firstnameEditText.setError("Firstname field should not be empty");
			valid = false;
		}
		if (lastnameEditText.getText().toString().equals("")){
			lastnameEditText.setError("Lastname field should not be empty");
			valid = false;
		}
		if (emailEditText.getText().toString().equals("")){
			emailEditText.setError("Email field should not be empty");
			valid = false;
		}
		if (passwordEditText.getText().toString().equals("")){
			passwordEditText.setError("Password field should not be empty");
			valid = false;
		}
		if (passwordConfirmationEditText.getText().toString().equals("")){
			passwordConfirmationEditText.setError("Password confirmation field should not be empty");
			valid = false;
		}
		
		if (valid){
			
			if (!passwordConfirmationEditText.getText().toString().equals(passwordEditText.getText().toString())){
				passwordConfirmationEditText.setError("Please verify your password confirmation");
				valid = false;
			}
			
			if (!isValidEmailAddress(emailEditText.getText().toString())){
				emailEditText.setError("Email is incorrect");
				valid = false;
			}
			
			if (valid){
				
			}
			
		}
	}


	public boolean isValidEmailAddress(String email) {
	    java.util.regex.Pattern p = java.util.regex.Pattern.compile(".+@.+\\.[a-z]+");
	    java.util.regex.Matcher m = p.matcher(email);
	    return m.matches();
	}
	
}
