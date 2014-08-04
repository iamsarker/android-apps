package com.errorpoint.bundleonsavedinstance;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;

public class LauncherActivity extends Activity {
	EditText txtValue;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		txtValue = (EditText) findViewById(R.id.txtValue);
		
		String txt="";
		if(savedInstanceState!=null){
			txt = savedInstanceState.getString("text_val");
		}
		
		if(txt!=null){
			txtValue.setText(txt);
		}
	}
	
	
	
	protected void onsavedInstanceState(Bundle valueCather){
		valueCather.putString("text_val", txtValue.getText().toString());
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.launcher, menu);
		return true;
	}

}
