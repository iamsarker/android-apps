package com.errorpoint.ratingbar;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

public class LauncherActivity extends Activity {
	
	TextView tvRatingValue;
	RatingBar ratingBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		
		tvRatingValue = (TextView) findViewById(R.id.tvRatingValue);
		ratingBar = (RatingBar) findViewById(R.id.ratingBar);
		
		tvRatingValue.setText(ratingBar.getRating()+"");
		
		ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				tvRatingValue.setText(rating+"");
			}
		});
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.launcher, menu);
		return true;
	}
	
}
