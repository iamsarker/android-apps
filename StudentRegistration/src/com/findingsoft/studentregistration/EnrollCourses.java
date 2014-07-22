package com.findingsoft.studentregistration;

import gateways.EnrollCourseGateWay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import utilities.EnrollCourse;
import adapters.EnrollAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class EnrollCourses extends Activity {

	public String departmentCode = "None", departmentName = "None";

	EditText sRegNo, sName;
	Button btnEnroll, btnDelEnroll;
	Spinner spnCourses;

	EnrollCourseGateWay eGateWay = new EnrollCourseGateWay(this);
	
	
	//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.manage_enrolls);

		initialControls();
		eventRegister();
		proccessListView();
	}

	private void initialControls() {
		sRegNo = (EditText) findViewById(R.id.txtEnrollReg);
		sName = (EditText) findViewById(R.id.txtEnrollName);

		btnEnroll = (Button) findViewById(R.id.btnEnroll);
		btnDelEnroll = (Button) findViewById(R.id.btnEnrollDel);

		spnCourses = (Spinner) findViewById(R.id.spnSelectCourse);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			departmentCode = extras.getString("studentRegNo");
			departmentName = extras.getString("studentName");
		}
		sRegNo.setText(departmentCode);
		sName.setText(departmentName);

	}

	private void eventRegister() {
		btnEnroll.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				enrollCour();
			}
		});
		btnDelEnroll.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				enrollDelCour();
			}
		});

	}

	private void enrollCour() {
		
		Date date = new Date();
		String enrollDate = dateFormat.format(date);

		
		EnrollCourse enrollCou = new EnrollCourse();
		enrollCou.seteCode(spnCourses.getSelectedItem().toString());
		enrollCou.seteRegNo(sRegNo.getText().toString());
		enrollCou.seteDate(enrollDate);
		enrollCou.seteResult("0");
		
		String res = eGateWay.enrollCours(enrollCou);
		showToast(res);
		
		//showToast(spnCourses.getSelectedItem().toString());
		
		proccessListView();
	}

	private void enrollDelCour() {
		String res = eGateWay.deleteEnroll(spnCourses.getSelectedItem().toString(), departmentCode);
		showToast(res);
		proccessListView();
	}

	public void proccessListView() {
		ArrayList<EnrollCourse> enrollList = new ArrayList<EnrollCourse>();
		
		loadSpinnerView();
		
		enrollList = eGateWay.getAll();
		loadListView(enrollList);
	}
	
	private void loadSpinnerView(){
		List<String> list = new ArrayList<String>();
		list = eGateWay.getAllCourse(departmentCode);
		ArrayAdapter<String> adpGender = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);

		adpGender
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spnCourses.setAdapter(adpGender);
		
	}

	private void loadListView(ArrayList<EnrollCourse> enrollList) {
		
		ListView listView = (ListView) findViewById(R.id.enrollListAll);
				
		listView.setAdapter(new EnrollAdapter(this, enrollList));
	}

	private void showToast(String msg) {
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
	}

}