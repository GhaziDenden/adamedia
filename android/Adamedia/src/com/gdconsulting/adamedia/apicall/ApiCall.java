package com.gdconsulting.adamedia.apicall;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class ApiCall extends AsyncTask<String, String, ApiResult>{
	 
	protected OnApiCallCompleted listener;
	protected Context context;
	protected String progressName;
	protected ProgressDialog progressDialog;
	
	public ApiCall(Context context, String progressName, OnApiCallCompleted listener) {
		this.context = context;
		this.progressName = progressName;
		this.listener=listener;
	}
	
	@Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context, progressName , "Loading...", true, false);
    }
	
    @Override
    protected ApiResult doInBackground(String... uri) {

        HttpClient httpclient = new DefaultHttpClient();
        ApiResult apiResult = new ApiResult();
        HttpResponse response;
        String responseString = null;
        
        try {
            response = httpclient.execute(new HttpGet(uri[0]));
            StatusLine statusLine = response.getStatusLine();
            
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                out.close();
                responseString = out.toString();

    	        Log.e("responseString", "responseString : "+responseString);
                
            	JSONObject jsonObject;
				try {
					jsonObject = new JSONObject(responseString);
    		        
    		    	String state = jsonObject.getString("state");
    		    	JSONObject data = new JSONObject();
    		    	if (!jsonObject.getString("result").equals("")) data = jsonObject.getJSONObject("result");
    		    	String errors = "";
    		    	if (!jsonObject.getString("errors").equals("")) errors = jsonObject.getString("errors");
    		    	
    		    	if (state.equals("OK")) apiResult.setStatus(ApiResult.RESULT_OK);
    		    	else apiResult.setStatus(ApiResult.RESULT_KO);
    		    	apiResult.setData(data);
    		    	apiResult.setErrors(errors);
    		    	
				} catch (JSONException e) {

	    	        Log.e("JSONException", ""+e.getLocalizedMessage());
					apiResult.setStatus(ApiResult.SERVER_ERROR);
				}
                
            } else{
                //Closes the connection.
                response.getEntity().getContent().close();
                apiResult.setStatus(ApiResult.CNX_ERROR);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            apiResult.setStatus(ApiResult.UNKNOWN_ERROR);
        } catch (IOException e) {
            e.printStackTrace();
            apiResult.setStatus(ApiResult.UNKNOWN_ERROR);
        }
        return apiResult;
    }
 
    @Override
    protected void onPostExecute(ApiResult result) {
        super.onPostExecute(result);
        Log.e("onPostExecute", ""+result);
        if (result.getStatus() == ApiResult.RESULT_OK)
        	listener.onApiCallCompleted(result);
        else listener.onApiCallError(result);
        	
        if (progressDialog.isShowing()) {
        	progressDialog.dismiss();
        }
    }
    
}
