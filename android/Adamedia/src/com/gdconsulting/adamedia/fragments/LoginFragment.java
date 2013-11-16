package com.gdconsulting.adamedia.fragments;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.gdconsulting.adamedia.DataInterface;
import com.gdconsulting.adamedia.R;
import com.gdconsulting.adamedia.SignupActivity;
import com.gdconsulting.adamedia.apicall.ApiCall;
import com.gdconsulting.adamedia.apicall.ApiResult;
import com.gdconsulting.adamedia.apicall.OnApiCallCompleted;
import com.gdconsulting.adamedia.model.Alert;
import com.gdconsulting.adamedia.model.User;

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
		
		emailEditText.setText("test@test.fr");
		passwordEditText.setText("password");
		
		signinButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				validateFields();
			}
			
		});
		
		
		signupButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				showSignUpActivity();
			}
			
		});
		
		return myFragmentView;
	}

	protected void showSignUpActivity() {
		getActivity().startActivity(new Intent(getActivity(), SignupActivity.class));
	}
	
	
	
	protected void validateFields() {
		
		emailEditText.setError(null);
		passwordEditText.setError(null);
		
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
			
			if (!isValidEmailAddress(emailEditText.getText().toString())){
				emailEditText.setError("Email is incorrect");
				valid = false;
			}
			
			if (valid){
				new ApiCall(getActivity(),"Sign in",new Callback()).execute(DataInterface.getSingletonInstance().WSUrl+"userConnect.php?email="+emailEditText.getText().toString()+"&password="+passwordEditText.getText().toString());
			}
			
		}
	}
	
	
	public class Callback implements OnApiCallCompleted{
	    @Override
	    public void onApiCallCompleted(ApiResult result) {
	        Log.e("onApiCallCompleted", "onApiCallCompleted");
	        Log.e("onApiCallCompleted", ""+result.toString());
	        
	        JSONObject jsonObject = result.getData();
	        
	        try {
	        	jsonObject = jsonObject.getJSONObject("user");
	        	
	        	User user = new User();
	        	user.setFirstname(jsonObject.getString("firstname"));
	        	user.setLasttname(jsonObject.getString("lasttname"));
	        	user.setEmail(jsonObject.getString("email"));
	        	user.setPhone(jsonObject.getString("phone"));
	        	user.setFb_uid(jsonObject.getString("fb_uid"));
	        	
	        	DataInterface.getSingletonInstance().setCurrentUser(user);
	        	DataInterface.getSingletonInstance().setConnected(true);
	        	
	        	Log.e("jsonObject", jsonObject.getString("firstname"));
	        } catch (JSONException e) {
				e.printStackTrace();
			}
	    }
	    @Override
	    public void onApiCallError(ApiResult result) {
	        Log.e("onApiCallError", "onApiCallError");
	        Log.e("onApiCallError", ""+result.toString());
	        
	        switch(result.getStatus()){
		        case ApiResult.CNX_ERROR:
		        	Alert.showAlert(getActivity(), Alert.ALERT_INFO, "Error", "Connection error");
		        	break;
		        case ApiResult.SERVER_ERROR:
		        	Alert.showAlert(getActivity(), Alert.ALERT_INFO, "Error", "Server error");
		        	break;
		        case ApiResult.UNKNOWN_ERROR:
		        	Alert.showAlert(getActivity(), Alert.ALERT_INFO, "Error", "Unknown error");
		        	break;
		        case ApiResult.RESULT_KO:
			        Alert.showAlert(getActivity(), Alert.ALERT_INFO, "Error", result.getErrors());
		        	break;
	        }
	    }
	}
	
	
	public boolean isValidEmailAddress(String email) {
	       java.util.regex.Pattern p = java.util.regex.Pattern.compile(".+@.+\\.[a-z]+");
	       java.util.regex.Matcher m = p.matcher(email);
	       return m.matches();
	}
	
}
