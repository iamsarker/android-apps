package com.errorpoint.smsreceiver;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ReplySmsActivity extends Activity{
	EditText etNumber, etMessage, etReply;
	String number, body;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reply_sms);
		etNumber = (EditText) findViewById(R.id.etNumber);
		etMessage = (EditText) findViewById(R.id.etMessage);
		etReply = (EditText) findViewById(R.id.etReply);
		number = getIntent().getStringExtra("number");
		body = getIntent().getStringExtra("body");
		etNumber.setText(number);
		etMessage.setText(body);
	}

	public void sendSMS(View v) {
		String replyStr = etReply.getText().toString();
		if (replyStr.equals("")) {
			Toast.makeText(getApplicationContext(), "Write some message", Toast.LENGTH_LONG).show();
		} else {
			// send message
			SmsManager manager = SmsManager.getDefault();
			manager.sendTextMessage(number, null, replyStr, null, null);
			Toast.makeText(getApplicationContext(), "Reply sent", Toast.LENGTH_LONG).show();
		}

	}
}
