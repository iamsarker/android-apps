package com.errorpoint.customlistview;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import com.errorpoint.customlistview.adapter.DoctorPlanAdapter;
import com.errorpoint.customlistview.demodata.DemoData;
import com.errorpoint.customlistview.entity.DoctorCall;
import com.errorpoint.customlistview.global.DoctorVisit;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class LauncherActivity extends Activity{
	
	ListView listView;
	RadioGroup radioGroupShift;
	TextView lblDate;
	RadioButton radioShift;
	Button btnSavePlan;
	ArrayList<DoctorCall> doctorCalls = null;
	long planDate = 0;
	 String planText="";
	final Calendar myCalendar = Calendar.getInstance();
	
	final Calendar aCalendar = Calendar.getInstance();
	
	private DoctorPlanAdapter doctorPlanAdapter;
	DatePickerDialog.OnDateSetListener plannedDate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		
		listView = (ListView) findViewById(R.id.doctorPlanList);
		lblDate = (TextView) findViewById(R.id.tvDoctorPlanDate);
		btnSavePlan = (Button) findViewById(R.id.btnSavePlan);
		//btnSavePlan.setOnClickListener(this);
		// radioGroupShift = (RadioGroup)
		// rootView.findViewById(R.id.radioShift);

		String myFormat = "dd/MM/yyyy EEEE"; // In which you need put here
		SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
		lblDate.setText(sdf.format(myCalendar.getTime()));
		planDate = getCurrentMillisecond(myCalendar);
		
		DoctorVisit.DOCTOR_PLAN_LIST = DemoData.getDoctorSortList();
		
		doctorPlanAdapter = new DoctorPlanAdapter(this,
				DoctorVisit.DOCTOR_PLAN_LIST);
		listView.setAdapter(doctorPlanAdapter);
		plannedDate = new DatePickerDialog.OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				// TODO Auto-generated method stub
				myCalendar.set(Calendar.YEAR, year);
				myCalendar.set(Calendar.MONTH, monthOfYear);
				myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
				updateLabel();
				doctorPlanAdapter.notifyDataSetChanged();
			}
		};

		//lblDate.setOnClickListener(this);
		configFirstnLastDate();

		
	}
	
	private void configFirstnLastDate(){
		String[] dats = lblDate.getText().toString().split("/");
		int cmonth = Integer.parseInt(dats[1]);
		if(cmonth>0) cmonth -= 1;
		
		int cmonth_cal = aCalendar.get(Calendar.MONTH);
		aCalendar.add(Calendar.MONTH, (cmonth_cal*-1));
		aCalendar.add(Calendar.MONTH, cmonth);
		
		aCalendar.set(Calendar.DATE, 1);
		
		long currentMonthFirstDay = getCurrentMillisecond(aCalendar);
		aCalendar.set(Calendar.DATE, aCalendar.getActualMaximum(aCalendar.DAY_OF_MONTH));
		long currentMonthLastDay = getCurrentMillisecond(aCalendar);
		
		DoctorVisit.FIRST_DATE = currentMonthFirstDay;
		DoctorVisit.LAST_DATE = currentMonthLastDay;
		
		//Toast.makeText(FragmentDoctorPlan.this.getSherlockActivity(), cmonth_cal + " : " + curDayc + " : " + DoctorVisit.FIRST_DATE + " : " + DoctorVisit.LAST_DATE, Toast.LENGTH_LONG).show();
	}

	private void updateLabel() {

		String myFormat = "dd/MM/yyyy EEEE"; // In which you need put here
		SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
		lblDate.setText(sdf.format(myCalendar.getTime()));
		planDate = getCurrentMillisecond(myCalendar);
		doctorCalls = null;
		configFirstnLastDate();
	}
	private long getCurrentMillisecond(Calendar calendar) {

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();

	}

//	@Override
//	public void onClick(View v) {
//		switch (v.getId()) {
//		case R.id.tvDoctorPlanDate:
//			new DatePickerDialog(this,
//					plannedDate, myCalendar.get(Calendar.YEAR),
//					myCalendar.get(Calendar.MONTH),
//					myCalendar.get(Calendar.DAY_OF_MONTH)).show();
//
//			break;
//
//		}
//
//	}

}
