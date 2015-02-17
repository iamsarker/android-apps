package com.errorpoint.splashscreen;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class LauncherActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		
		Thread splashThread = new Thread(){
			@Override
			public void run(){
				try{
					sleep(3000);
				} catch(InterruptedException e){
					
				} finally{
					Intent i = new Intent();
					i.setClassName("com.errorpoint.splashscreen", "com.errorpoint.splashscreen.HomeActivity");
					startActivity(i);
				}
			}
		};
		splashThread.start();
		
	}

}
