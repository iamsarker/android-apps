package com.errorpoint.fonttypeface;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.Menu;
import android.widget.TextView;

public class LauncherActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		
		TextView customFont = (TextView) findViewById(R.id.customFont);
		TextView customFont2 = (TextView) findViewById(R.id.customFont2);
		
		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/SUTOM___.TTF");
		Typeface tf2 = Typeface.createFromAsset(getAssets(), "fonts/scriptbold.ttf");
		
		customFont.setTypeface(tf);
		customFont2.setTypeface(tf2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.launcher, menu);
		return true;
	}

}
