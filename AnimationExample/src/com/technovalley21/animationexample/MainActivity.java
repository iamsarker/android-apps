package com.technovalley21.animationexample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView fadeIn, fadeOut, slideDown, slideUp, blinking, move, zoomIn, zoomOut, rotate, bounce;
	Animation animFadeIn, animFadeOut, animMove, animBlinking, animZoomIn, animZoomOut, animRotate, animSlideDown, animSlideUp, animBounce;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
		animFadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
		animBlinking = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking);
		animZoomIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
		animZoomOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_out);
		animRotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
		animMove = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
		animSlideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
		animSlideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
		animBounce = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
		
		fadeIn = (TextView) findViewById(R.id.fadeIn);
		fadeOut = (TextView) findViewById(R.id.fadeOut);
		slideDown = (TextView) findViewById(R.id.slideDown);
		slideUp = (TextView) findViewById(R.id.slideUp);
		blinking = (TextView) findViewById(R.id.blinking);
		move = (TextView) findViewById(R.id.move);
		zoomIn = (TextView) findViewById(R.id.zoomIn);
		zoomOut = (TextView) findViewById(R.id.zoomOut);
		rotate = (TextView) findViewById(R.id.rotate);
		bounce = (TextView) findViewById(R.id.bounce);
		
		fadeIn.startAnimation(animFadeIn);
		fadeOut.startAnimation(animFadeOut);
		blinking.startAnimation(animBlinking);
		zoomIn.startAnimation(animZoomIn);
		zoomOut.startAnimation(animZoomOut);
		rotate.startAnimation(animRotate);
		move.startAnimation(animMove);
		slideDown.startAnimation(animSlideDown);
		slideUp.startAnimation(animSlideUp);
		bounce.startAnimation(animBounce);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
