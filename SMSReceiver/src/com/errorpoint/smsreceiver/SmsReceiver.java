package com.errorpoint.smsreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "Received SMS Intent: " + intent.getAction(), Toast.LENGTH_LONG).show();
		
		if(intent.getAction().equalsIgnoreCase("android.provider.Telephony.SMS_Received") ){
			Bundle bundle = intent.getExtras();
			SmsMessage[] smsChunks = null;
			String body = "";
			String fromNumber = "";
			
			if( bundle != null ){
				Object[] pdus = (Object[]) bundle.get("pdus");
				smsChunks = new SmsMessage[pdus.length];
				
				for(int i=0; i<pdus.length ; i++){
					smsChunks[i] = SmsMessage.createFromPdu( (byte[]) pdus[i] );
					body += smsChunks[i].getMessageBody().toString();
					fromNumber += smsChunks[i].getOriginatingAddress();
				}
				Toast.makeText(context, "Number: " + fromNumber + " Body: " + body, Toast.LENGTH_LONG).show();
				
				Intent i = new Intent(context, ReplySmsActivity.class);
				i.putExtra("number", fromNumber);
				i.putExtra("body", body);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(i);
			}
		}
		
	}

}
