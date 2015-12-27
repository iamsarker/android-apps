package com.technovalley21.voicechanger;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.support.v7.app.ActionBarActivity;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends ActionBarActivity {
	
	Integer[] freqset = {11025, 16000, 22050, 44100};
	private ArrayAdapter<Integer> adapter;
	
	Spinner spFrequency;
	Button startRec, stopRec, playBack;
	 
	Boolean recording;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		startRec = (Button)findViewById(R.id.startrec);
        stopRec = (Button)findViewById(R.id.stoprec);
        playBack = (Button)findViewById(R.id.playback);
        
        startRec.setOnClickListener(startRecOnClickListener);
        stopRec.setOnClickListener(stopRecOnClickListener);
        playBack.setOnClickListener(playBackOnClickListener);
        
        spFrequency = (Spinner)findViewById(R.id.frequency);
        adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, freqset);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFrequency.setAdapter(adapter);
	}
	
	OnClickListener startRecOnClickListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Thread recordThread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					recording = true;
				    startRecord();
				}
			});
			recordThread.start();
		}
		
	};
	
	OnClickListener stopRecOnClickListener = new OnClickListener(){
		
		@Override
		public void onClick(View arg0) {
			recording = false;
		}
	};
	  
	OnClickListener playBackOnClickListener = new OnClickListener(){

	   @Override
	   public void onClick(View v) {
		   playRecord();
	   }
	};
	
	private void startRecord(){
		File file = new File(Environment.getExternalStorageDirectory(), "record.pcm");
		int sampleFreq = (Integer)spFrequency.getSelectedItem();
		
		try{
			file.createNewFile();
			
			OutputStream outputStream = new FileOutputStream(file);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
			DataOutputStream dataOutputStream = new DataOutputStream(bufferedOutputStream);
			
			int minBufferedSize = AudioRecord.getMinBufferSize(sampleFreq, AudioFormat.CHANNEL_CONFIGURATION_MONO, AudioFormat.ENCODING_PCM_16BIT);
			
			short[] audioData = new short[minBufferedSize];
			
			AudioRecord audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, sampleFreq, AudioFormat.CHANNEL_CONFIGURATION_MONO, AudioFormat.ENCODING_PCM_16BIT, minBufferedSize);
			audioRecord.startRecording();
			
			while (recording) {
				int numberOfShort = audioRecord.read(audioData, 0, minBufferedSize);
				for(int i = 0; i < numberOfShort; i++){
					dataOutputStream.writeShort(audioData[i]);
				}
			}
			
			audioRecord.stop();
			dataOutputStream.close();
			
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	private void playRecord(){
		File file = new File(Environment.getExternalStorageDirectory(), "record.pcm");
		  
        int shortSizeInBytes = Short.SIZE/Byte.SIZE;
  
        int bufferSizeInBytes = (int)(file.length()/shortSizeInBytes);
        short[] audioData = new short[bufferSizeInBytes];
  
        try {
        	InputStream inputStream = new FileInputStream(file);
        	BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        	DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);
   
        	int i = 0;
        	while(dataInputStream.available() > 0){
        		audioData[i] = dataInputStream.readShort();
        		i++;
        	}
   
        	dataInputStream.close();
        	int sampleFreq = (Integer)spFrequency.getSelectedItem();
        	
        	AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, sampleFreq, AudioFormat.CHANNEL_CONFIGURATION_MONO,
        	     AudioFormat.ENCODING_PCM_16BIT,
        	     bufferSizeInBytes,
        	     AudioTrack.MODE_STREAM);
        	   
        	audioTrack.play();
        	audioTrack.write(audioData, 0, bufferSizeInBytes);

        	   
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        }
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
