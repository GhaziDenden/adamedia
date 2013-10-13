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

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class ApiCall extends AsyncTask<String, String, String>{
	 
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
    protected String doInBackground(String... uri) {

        HttpClient httpclient = new DefaultHttpClient();
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
            } else{
                //Closes the connection.
                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseString;
    }
 
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.e("onPostExecute", ""+result);
        listener.onApiCallCompleted(result);
        
        if (progressDialog.isShowing()) {
        	progressDialog.dismiss();
        }
    }
}
