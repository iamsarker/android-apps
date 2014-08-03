package com.errorpoint.tabhostmenu;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class LauncherActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		
		TabHost tabHost = getTabHost();
		
		TabSpec tab1 = tabHost.newTabSpec("First Tab");
		TabSpec tab2 = tabHost.newTabSpec("Second Tab");
		TabSpec tab3 = tabHost.newTabSpec("Third Tab");
		
		tab1.setIndicator("Tab1",getResources().getDrawable(R.drawable.ic_launcher));
		tab1.setContent(new Intent(this, Tab1Activity.class));
		
		tab2.setIndicator("Tab2",getResources().getDrawable(R.drawable.ic_launcher));
		tab2.setContent(new Intent(this, Tab2Activity.class));
		
		tab3.setIndicator("Tab3",getResources().getDrawable(R.drawable.ic_launcher));
		tab3.setContent(new Intent(this, Tab3Activity.class));
		
		tabHost.addTab(tab1);
		tabHost.addTab(tab2);
		tabHost.addTab(tab3);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.launcher, menu);
		return true;
	}

}
