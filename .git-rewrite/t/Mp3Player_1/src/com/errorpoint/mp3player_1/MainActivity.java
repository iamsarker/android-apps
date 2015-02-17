package com.errorpoint.mp3player_1;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	Button btnPlay1, btnPlay2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnPlay1 = (Button) findViewById(R.id.playCode1);
		btnPlay2 = (Button) findViewById(R.id.playCode2);
		
		
		final String filePath = Environment.getExternalStorageDirectory().getPath() + "/media/audio/Aami Sudhu Cheyechi Tomay Title[BDLove24.Com].mp3";
		
		
		btnPlay1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MediaPlayer mPlayer = new MediaPlayer();
				mPlayer = MediaPlayer.create(MainActivity.this, Uri.parse(filePath));
				mPlayer.setLooping(true);
				
				try{
					mPlayer.start();
				} catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		
		btnPlay2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MediaPlayer mPlayer = new MediaPlayer();
				
				try{
					mPlayer.setDataSource(filePath);
					mPlayer.prepare();
					mPlayer.start();
				} catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
