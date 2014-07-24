package com.errorpoint.customactionbar;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LauncherActivity extends Activity implements OnClickListener{
	
	ActionBar actionbar;
	Button btnClick;
	ImageView appIcon;
	TextView appName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		
		actionbar = getActionBar();
		actionbar.setDisplayShowCustomEnabled(true);
		actionbar.setCustomView(R.layout.action_bar_layout);
		
		btnClick = (Button) findViewById(R.id.btnClick);
		appIcon = (ImageView) findViewById(R.id.appIcon);
		appName = (TextView) findViewById(R.id.appName);
		
		appName.setOnClickListener(this);
		appIcon.setOnClickListener(this);
		btnClick.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.appName:
			Toast.makeText(LauncherActivity.this, "You Clicked on App Name", Toast.LENGTH_LONG).show();
			break;
		case R.id.appIcon:
			Toast.makeText(LauncherActivity.this, "You Clicked on App Icon", Toast.LENGTH_LONG).show();
			break;
		case R.id.btnClick:
			Toast.makeText(LauncherActivity.this, "You Clicked on Button", Toast.LENGTH_LONG).show();
			break;
		}
	}

}
