package com.errorpoint.alarmmanagerapp;

import java.util.GregorianCalendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	EditText alarmTime;
	Button btnAlarmOnce, btnAlarmRepeat;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		alarmTime = (EditText) findViewById(R.id.milisecondTime);
		btnAlarmOnce = (Button) findViewById(R.id.alarmOnce);
		btnAlarmRepeat = (Button) findViewById(R.id.alarmRepeat);
		
		
		btnAlarmOnce.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setAlarm(v);
			}
		});
		
		btnAlarmRepeat.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setRepeatAlarm(v);
			}
		});
	}
	
	private void setAlarm(View v){
		String etAlarmTime = alarmTime.getText().toString();
		int interval = Integer.parseInt(etAlarmTime);
		
		long time = new GregorianCalendar().getTimeInMillis()+(interval*1000);
		
		Intent alarmIntent = new Intent(this, AlarmReceiver.class);
		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		
		alarmManager.set(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(this, 1, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT));
	}
	
	private void setRepeatAlarm(View v){
		String etAlarmTime = alarmTime.getText().toString();
		int interval = Integer.parseInt(etAlarmTime);
		
		long time = new GregorianCalendar().getTimeInMillis()+(interval*1000);
		
		Intent alarmIntent = new Intent(this, AlarmReceiver.class);
		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, interval*1000, PendingIntent.getBroadcast(this, 2, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT));
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
