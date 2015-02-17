package com.errorpoint.seekbar;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class LauncherActivity extends Activity implements OnSeekBarChangeListener {
	
	TextView tvSeekBar;
	SeekBar sb1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		
		tvSeekBar = (TextView) findViewById(R.id.tvSeekBar);
		sb1 = (SeekBar) findViewById(R.id.sb1);
		sb1.setMax(100); // maximum value for seekbar from 0
		sb1.setProgress(5); // from where seekbar will start
		
		sb1.setOnSeekBarChangeListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.launcher, menu);
		return true;
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		tvSeekBar.setText(progress+"");
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Tracking Start with: " + seekBar.getProgress(), Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Tracking End with: " + seekBar.getProgress(), Toast.LENGTH_SHORT).show();
	}

}
