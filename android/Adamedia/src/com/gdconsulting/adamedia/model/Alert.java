package com.gdconsulting.adamedia.model;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Alert {
	
	public static final int ALERT_INFO 			= 1;
	public static final int ALERT_CONFIRMATION 	= 2;
	
	public static void showAlert(Context context, int type, String title, String text) {
	
		switch(type){
			case ALERT_INFO:
				showInfoDialog(context, title, text);
				break;
			case ALERT_CONFIRMATION:
				showConfirmationDialog(context, title, text);
				break;
			default:
				break;
			
		}
		
	}
	
	
	public static void showInfoDialog(Context context, String title, String text){
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.setTitle(title);
		alertDialog.setMessage(text);
		alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL,"OK", new DialogInterface.OnClickListener() {

		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		        dialog.dismiss();
			}
		    
		});
		alertDialog.show();
	}
	
	public static void showConfirmationDialog(Context context, String title, String text){
		
	}

}
