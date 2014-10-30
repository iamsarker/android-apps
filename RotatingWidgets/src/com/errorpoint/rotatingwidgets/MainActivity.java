package com.errorpoint.rotatingwidgets;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	TextView tv1, tv2, tv3, tv4;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tv1 = (TextView) findViewById(R.id.tv1);
		tv2 = (TextView) findViewById(R.id.tv2);
		tv3 = (TextView) findViewById(R.id.tv3);
		tv4 = (TextView) findViewById(R.id.tv4);
		
		
		if( Build.VERSION.SDK_INT < 11 ){
			RotateAnimation ra = new RotateAnimation(0, 90);
			ra.setDuration(100);
			ra.setFillAfter(true);
			
			tv1.startAnimation(ra);
			tv2.startAnimation(ra);
			tv3.startAnimation(ra);
			tv4.startAnimation(ra);
			
		} else{
			try{
				tv1.setRotation(90);
				tv2.setRotation(45);
				tv3.setRotation(180);
				tv4.setRotation(-90);
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
