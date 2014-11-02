package com.errorpoint.audiorecorder;

import java.io.IOException;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private MediaRecorder mediaRecorder;
	private String outputFile = null;
	private Button start,stop,play;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		start = (Button)findViewById(R.id.button1);
		stop = (Button)findViewById(R.id.button2);
		play = (Button)findViewById(R.id.button3);
		
		stop.setEnabled(false);
	    play.setEnabled(false);
	    
	    outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/myRecorded.3gp";
	    mediaRecorder = new MediaRecorder();
	    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
	    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
	    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
	    mediaRecorder.setOutputFile(outputFile);
	    
		
	}
	
	public void start(View view){
		
		try{
			mediaRecorder.prepare();
			mediaRecorder.start();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		start.setEnabled(false);
	    stop.setEnabled(true);
	    Toast.makeText(getApplicationContext(), "Recording started", Toast.LENGTH_LONG).show();
	}
	
	public void stop(View view){
		
		try{
			mediaRecorder.stop();
			mediaRecorder.release();
			mediaRecorder = null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		stop.setEnabled(false);
	    play.setEnabled(true);
	    Toast.makeText(getApplicationContext(), "Audio recorded successfully", Toast.LENGTH_LONG).show();
	}
	
	public void play(View view) throws IllegalArgumentException, SecurityException, IllegalStateException, IOException{
		
		MediaPlayer mPlayer = new MediaPlayer();
		mPlayer.setDataSource(outputFile);
		mPlayer.prepare();
		mPlayer.start();
		
		
		stop.setEnabled(false);
	    play.setEnabled(true);
	    Toast.makeText(getApplicationContext(), "Audio recorded successfully", Toast.LENGTH_LONG).show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
