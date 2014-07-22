package com.findingsoft.studentregistration;

import gateways.DepartmentGateWay;

import java.util.ArrayList;

import utilities.Departments;
import adapters.DepartmentAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class ManageDepartment extends Activity {
	
	EditText dCodetxt, dNametxt;
	Button btnCreateD, btnDeleteD, btnUpdateD;
	ListView deptList;
	
	DepartmentGateWay gatewayDept = new DepartmentGateWay(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.manage_departments);
		
		initialControls();
		eventRegister();
		
		proccessListView();
	}
	
	private void initialControls(){
		dCodetxt = (EditText)findViewById(R.id.txtDCode);
		dNametxt = (EditText)findViewById(R.id.txtDName);
		
		btnCreateD = (Button)findViewById(R.id.btnCreateD);
		btnDeleteD = (Button)findViewById(R.id.btnDeleteD);
		btnUpdateD = (Button)findViewById(R.id.btnUpdateD);
		
		deptList = (ListView)findViewById(R.id.deptListAll);
	}
	
	private void eventRegister(){
		btnCreateD.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				saveDept();
			}
		});
		btnDeleteD.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				deleteDept();
			}
		});
		btnUpdateD.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				updateDept();
			}
		});
	}

	
	private void saveDept(){
		if( !dCodetxt.getText().equals("") && !dNametxt.getText().equals("") ){
			Departments aDept = new Departments();
			aDept.setDeptCode(dCodetxt.getText().toString());
			aDept.setDeptName(dNametxt.getText().toString());
			
			String res = gatewayDept.save(aDept);
			showToast(res);
			proccessListView();
		}
	}
	
	private void deleteDept(){
		if( !dCodetxt.getText().equals("") ){
			Departments aDept = new Departments();
			aDept.setDeptCode(dCodetxt.getText().toString());
			aDept.setDeptName(dNametxt.getText().toString());
			
			String res = gatewayDept.deptDelete(dCodetxt.getText().toString());
			showToast(res);
			proccessListView();
		}
	}

	private void updateDept(){
		if( !dCodetxt.getText().equals("") && !dNametxt.getText().equals("") ){
			Departments aDept = new Departments();
			aDept.setDeptCode(dCodetxt.getText().toString());
			aDept.setDeptName(dNametxt.getText().toString());
			
			String res = gatewayDept.deptUpdate(aDept);
			showToast(res);
			proccessListView();
		}		
	}
	
	private void proccessListView(){
		ArrayList<Departments> aDept = new ArrayList<Departments>();
		aDept = gatewayDept.getAll();
		loadListView(aDept);
	}
	
	private void loadListView(ArrayList<Departments> aDept){
		ListView listView = (ListView)findViewById(R.id.deptListAll);
		listView.setAdapter(new DepartmentAdapter(this, aDept));
	}

	private void showToast(String msg) {
		Toast.makeText(getApplicationContext(), msg, 2000).show();
	}

}
