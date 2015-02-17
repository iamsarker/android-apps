package com.errorpoint.horizontalsliding;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	
	LinearLayout ll_container;
	HorizontalScrollView autoScrollView;
	ImageView imageViewlarge, first;
	int totalMiliInFuture;
	int childWidth;
	int tickNo = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		autoScrollView = (HorizontalScrollView) findViewById(R.id.autoScrollView);
		ll_container = (LinearLayout) findViewById(R.id.ll_container);
		imageViewlarge = (ImageView) findViewById(R.id.imageViewlarge);
		
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus){
		
		int numberOfChild = ll_container.getChildCount();
		int screenWidth = ll_container.getMeasuredWidth();
		
		childWidth = ll_container.getChildAt(0).getMeasuredWidth();
		
		totalMiliInFuture = numberOfChild * 500;
		engineRunner();
	}
	
	private void engineRunner(){
		new CountDownTimer(totalMiliInFuture, 500) {
			
			@Override
			public void onTick(long millisUntilFinished) {
				autoScrolling();
				tickNo++;
			}
			
			@Override
			public void onFinish() {
				tickNo = 1;
				engineRunner();
			}
		}.start();
	}
	
	private void autoScrolling(){
		Log.e("X", ": " + (tickNo*childWidth) );
		autoScrollView.smoothScrollTo( (tickNo*childWidth), 0);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
