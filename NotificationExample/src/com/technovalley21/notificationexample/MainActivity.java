package com.technovalley21.notificationexample;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private NotificationManager notificationManager;
	private int notificationID = 100;
	private int numMessages = 0;
	
	TextView cancelNotification;
	   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		cancelNotification = (TextView) findViewById(R.id.cancelNotification);
		cancelNotification.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				cancelNotification();
			}
		});
		
		NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this);
		nBuilder.setSmallIcon(R.drawable.ic_action_chat);
		nBuilder.setContentTitle("Sample Notification");
		nBuilder.setContentText("Welcome your app is running");
		nBuilder.setTicker("Alert!!!");
		nBuilder.setNumber(++numMessages);
		
		Intent intentResult = new Intent(this, NotificationView.class);
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		stackBuilder.addParentStack(NotificationView.class);
		
		
		stackBuilder.addNextIntent(intentResult);
		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
		nBuilder.setContentIntent(resultPendingIntent);
		
		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(notificationID, nBuilder.build());
	}
	
	protected void cancelNotification(){
		Log.i("Cancel", "notification");
		notificationManager.cancel(notificationID);
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
