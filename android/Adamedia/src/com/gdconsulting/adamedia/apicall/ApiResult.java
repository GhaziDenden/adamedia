package com.gdconsulting.adamedia.apicall;

import org.json.JSONObject;

public class ApiResult {

	public static final int CNX_ERROR 		= 1;
	public static final int SERVER_ERROR 	= 2;
	public static final int RESULT_OK 		= 3;
	public static final int RESULT_KO 		= 4;
	public static final int UNKNOWN_ERROR 	= 5;
	
	private int status;
	private JSONObject data;
	private String errors;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public JSONObject getData() {
		return data;
	}
	public void setData(JSONObject data) {
		this.data = data;
	}
	public String getErrors() {
		return errors;
	}
	public void setErrors(String errors) {
		this.errors = errors;
	}
	

	@Override
	public String toString() {
		return "ApiResult [status=" + status + ", data=" + data + ", errors="
				+ errors + "]";
	}
	
}
