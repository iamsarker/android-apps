package com.errorpoint.menubarinactionbar;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class LauncherActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.launcher, menu);
		
		return super.onCreateOptionsMenu(menu);
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case R.id.action_search:
			Toast.makeText(this, "Searching", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.action_record:
			Toast.makeText(this, "Recording", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.action_record2:
			Toast.makeText(this, "Recording", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.action_save:
			Toast.makeText(this, "Saving", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.action_save2:
			Toast.makeText(this, "Saving", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.action_play:
			Toast.makeText(this, "Playing", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.action_play2:
			Toast.makeText(this, "Playing", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.action_label:
			Toast.makeText(this, "Adding Label", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.action_label2:
			Toast.makeText(this, "Adding Label", Toast.LENGTH_SHORT).show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
}
