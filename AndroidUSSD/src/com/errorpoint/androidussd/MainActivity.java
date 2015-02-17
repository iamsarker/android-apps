package com.errorpoint.androidussd;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	Button btnIMEI;
	TextView txtView;
	EditText etNumber;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnIMEI = (Button) findViewById(R.id.btnIMEI);
		txtView  = (TextView) findViewById(R.id.txtView);
		etNumber = (EditText) findViewById(R.id.etNumber);
		
		btnIMEI.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String encodedHash = Uri.encode("#");
				String dNum = etNumber.getText().toString();
				if( dNum.length() > 0 ){
					startActivityForResult(new Intent("android.intent.action.CALL", Uri.parse("tel:" + "*" + dNum + encodedHash)), 1);
				} else{
					Toast.makeText(MainActivity.this, "Type any number to run ussd code", Toast.LENGTH_LONG).show();
				}
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode,Intent data) {
		//txtView.setText("USSD: " + requestCode + " " + resultCode + " " + data);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
