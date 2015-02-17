package com.errorpoint.smssender;

import java.util.ArrayList;

import com.errorpoint.smssender.utilities.SendSms;

import android.os.Bundle;
import android.app.Activity;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LauncherActivity extends Activity implements OnClickListener{
	
	EditText smsNo,smsText;
	Button singleMessage,multipleMessage;
	SmsManager smsManager = SmsManager.getDefault();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		
		smsNo = (EditText) findViewById(R.id.smsNo);
		smsText = (EditText) findViewById(R.id.smsText);
		
		singleMessage = (Button) findViewById(R.id.singleMessage);
		multipleMessage = (Button) findViewById(R.id.multipleMessage);
		
		singleMessage.setOnClickListener(this);
		multipleMessage.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(isNumberAvailable()){
			switch(v.getId()){
			case R.id.singleMessage:
				try{
					smsManager.sendTextMessage(smsNo.getText().toString(), null, smsText.getText().toString(), null, null);
					Toast.makeText(this, "Sent message successfully", Toast.LENGTH_SHORT).show();
				} catch(Exception e){
					Toast.makeText(this, "Message does not sent", Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.multipleMessage:
				
				String txt = smsText.getText().toString(), toNum = smsNo.getText().toString();
				
				/*-------------------------------------------------------------------------*/
				
				try{
					String[] part = txt.split(" ", 158);
					ArrayList<String> parts = new ArrayList<String>();
					for(String p:part){
						parts.add(p);
					}
					smsManager.sendMultipartTextMessage(toNum, null, parts, null, null);
					Toast.makeText(this, "Sent message successfully", Toast.LENGTH_SHORT).show();
				} catch(Exception e){
					Toast.makeText(this, "Message does not sent", Toast.LENGTH_SHORT).show();
				}
				
				// but some times sendMultipartTextMessage() method does not work properly
				/*-------------------------------------------------------------------------*/
				
				boolean  b = SendSms.smsSender(toNum, txt);
				if(b){
					Toast.makeText(this, "Sent message successfully", Toast.LENGTH_SHORT).show();
				} else{
					Toast.makeText(this, "Message does not sent", Toast.LENGTH_SHORT).show();
				}
				
				break;
			}
		} else{
			Toast.makeText(this, "No Mobile/Phone number is available!", Toast.LENGTH_LONG).show();
		}
	}

	private boolean isNumberAvailable(){
		if(smsNo.getText().toString()!=""){
			return true;
		}
		return false;
	}
}
