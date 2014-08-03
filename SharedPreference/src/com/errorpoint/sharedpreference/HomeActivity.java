package com.errorpoint.sharedpreference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity{
	
	TextView tvFounder, tvDeveloper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		tvFounder = (TextView) findViewById(R.id.tvFounder);
		tvDeveloper = (TextView) findViewById(R.id.tvDeveloper);
		
		SharedPreferences sharedPreferences = getSharedPreferences("ERRORPOINT_PREFERENCE", Context.MODE_PRIVATE);
		
		String founder = sharedPreferences.getString("Founder", "").trim();
		String developer = sharedPreferences.getString("Developer", "").trim();
		String noPreference = sharedPreferences.getString("ErrorPoint", "errorpoint.com").trim(); // no key is exist named ErrorPoint, So it will return errorpoint.com 
		
		tvFounder.setText("Founder: "+founder);
		tvDeveloper.setText("Developer: "+developer);
		
		Toast.makeText(this, "noPreference: " + noPreference, 2000).show();
	}
}
