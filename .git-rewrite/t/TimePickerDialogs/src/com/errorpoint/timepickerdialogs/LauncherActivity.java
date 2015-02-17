package com.errorpoint.timepickerdialogs;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.TimePicker;

public class LauncherActivity extends Activity {
	
	TextView defaultTimePicker24, defaultTimePicker12;
	Calendar myCalendar = Calendar.getInstance();
	TimePickerDialog.OnTimeSetListener default12Time, default24Time;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		
		defaultTimePicker24 = (TextView) findViewById(R.id.customTimePicker);
		defaultTimePicker12 = (TextView) findViewById(R.id.defaultTimePicker);
		
		default12Time = new OnTimeSetListener() {
			
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				// TODO Auto-generated method stub
				myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
				myCalendar.set(Calendar.MINUTE, minute);
				updateLabel(1);
			}
		};
		default24Time = new OnTimeSetListener() {
			
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				// TODO Auto-generated method stub
				myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
				myCalendar.set(Calendar.MINUTE, minute);
				updateLabel(2);
			}
		};
		
		defaultTimePicker12.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new TimePickerDialog(LauncherActivity.this, default12Time, myCalendar.get(Calendar.HOUR), myCalendar.get(Calendar.HOUR_OF_DAY),true).show();
			}
		});
		
		defaultTimePicker24.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new TimePickerDialog(LauncherActivity.this, default24Time, myCalendar.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.HOUR_OF_DAY),true).show();
			}
		});
	}
	
	private void updateLabel(int n){
		String myFormat="";
		if(n==1){
			myFormat = "h:m a"; // In which you need put here
			SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);		
			defaultTimePicker12.setText(sdf.format(myCalendar.getTime()));
		} else{
			myFormat = "H:m"; // In which you need put here
			SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);		
			defaultTimePicker24.setText(sdf.format(myCalendar.getTime()));
		}
		
	}

}
