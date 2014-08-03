package com.errorpoint.smssender.utilities;

import java.util.ArrayList;

import android.telephony.SmsManager;
import android.util.Log;

public class SendSms {
	public static  boolean smsSender(String to, String msg) {
	    SmsManager smsManager = SmsManager.getDefault();
	    try {
	    	   
	    	    int size=155;
			    ArrayList<String> contents = new ArrayList<String>();
	            //int num=(msg.length() + size - 1) / size;
	            
			    for (int start = 0; start < msg.length(); start += size) {
			    	contents.add(msg.substring(start, Math.min(msg.length(), start + size)));
			    }
			    for (String content : contents){
			    	Log.e("SMS : ", content);
			    	Log.e("SMS SIZE: ", content.length()+"");
	                smsManager.sendTextMessage(to, null, content, null, null);
	            }

	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}
