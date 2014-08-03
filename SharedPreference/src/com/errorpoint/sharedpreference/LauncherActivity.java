package com.errorpoint.sharedpreference;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;

public class LauncherActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		
		// Android SharedPreference act as cookie of HTML
		
		SharedPreferences sharedPreferences = this.getSharedPreferences("ERRORPOINT_PREFERENCE", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString("Founder", "MD. Shahadat Sarker");
		editor.putString("Developer", "MD. Shahadat Sarker");
		
		if( editor.commit() ){
			Intent i = new Intent(this, HomeActivity.class);
			startActivity(i);
			finish();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.launcher, menu);
		return true;
	}

}
