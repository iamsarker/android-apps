package com.errorpoint.externaldatabase;

import com.errorpoint.externaldatabase.storage.AppDataBaseManager;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class LauncherActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		
		TextView tvView = (TextView) findViewById(R.id.tvView);
		
		if(!AppDataBaseManager.isExtSDCardPresent())
		{
			Toast.makeText(this, "SD Card not Present", Toast.LENGTH_SHORT).show();
			finish();
			return;
		} else{
			AppDataBaseManager aDB = new AppDataBaseManager(this);
			if( aDB.initializeDatabase() ){
				String aInfo = aDB.getAppInfo();
				tvView.setText(aInfo);
			}
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.launcher, menu);
		return true;
	}

}
