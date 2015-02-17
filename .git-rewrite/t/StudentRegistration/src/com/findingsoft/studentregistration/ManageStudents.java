package com.findingsoft.studentregistration;

import gateways.StudentGateWay;

import java.util.ArrayList;
import utilities.Students;
import adapters.StudentAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ManageStudents extends Activity{
	
	String departmentCode = "None", departmentName = "None";
	
	TextView departCode, departName;
	EditText txtRegNo, txtStudentName, txtEmail;
	Button btnAddS, btnUpdateS, btnDeleteS;
	
	StudentGateWay gatewayStudent = new StudentGateWay(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.manage_students);
		
		initialControls();
		eventRegister();
		proccessListView();
	}
	
	private void initialControls(){
		departCode = (TextView)findViewById(R.id.viewCodeD);
		departName = (TextView)findViewById(R.id.viewNameD);
		
		txtRegNo = (EditText)findViewById(R.id.txtRegNo);
		txtStudentName = (EditText)findViewById(R.id.txtNameS);
		txtEmail = (EditText)findViewById(R.id.txtEmail);
		
		btnAddS = (Button)findViewById(R.id.addStudent);
		btnUpdateS = (Button)findViewById(R.id.updateStudent);
		btnDeleteS = (Button)findViewById(R.id.delStudent);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			departmentCode = extras.getString("depCode");
			departmentName = extras.getString("depName");
			departCode.setText(departmentCode);
			departName.setText(departmentName);
		}
		
	}
	
	private void eventRegister(){
		btnAddS.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				addStudent();
			}
		});
		btnUpdateS.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				updateStudent();
			}
		});
		btnDeleteS.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				deleteStudent();
			}
		});
	}
	
	
	
	private void addStudent(){
		if (!txtRegNo.getText().equals("") && !txtStudentName.getText().equals("") && !txtEmail.getText().equals("") ) {
			
			Students aStudnet = new Students();
			aStudnet.setRegNo(txtRegNo.getText().toString());
			aStudnet.setStudentName(txtStudentName.getText().toString());
			aStudnet.setStudentEmail(txtEmail.getText().toString());
			aStudnet.setStudentDept(departCode.getText().toString());

			String res = gatewayStudent.saveStudent(aStudnet);
			showToast(res);
			proccessListView();
		}

	}
	private void updateStudent(){
		if (!txtRegNo.getText().equals("") && !txtStudentName.getText().equals("") && !txtEmail.getText().equals("") ) {
			
			Students aStudnet = new Students();
			aStudnet.setRegNo(txtRegNo.getText().toString());
			aStudnet.setStudentName(txtStudentName.getText().toString());
			aStudnet.setStudentEmail(txtEmail.getText().toString());
			aStudnet.setStudentDept(departCode.getText().toString());

			String res = gatewayStudent.updateStudent(aStudnet);
			showToast(res);
			proccessListView();
		}

	}
	private void deleteStudent(){
		if (!txtRegNo.getText().equals("") ) {

			String res = gatewayStudent.deleteStudent(txtRegNo.getText().toString());
			showToast(res);
			proccessListView();
		}
		
	}

	public void proccessListView() {
		ArrayList<Students> aStudentList = new ArrayList<Students>();
		aStudentList = gatewayStudent.getAll(departmentCode);
		loadListView(aStudentList);
	}

	private void loadListView(ArrayList<Students> aStudentList) {
		ListView listView = (ListView) findViewById(R.id.studentListAll);
		
		listView.setAdapter(new StudentAdapter(this,aStudentList));
	}

	private void showToast(String msg) {
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
	}

}
