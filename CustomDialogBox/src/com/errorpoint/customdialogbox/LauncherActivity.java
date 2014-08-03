package com.errorpoint.customdialogbox;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class LauncherActivity extends Activity {
	Spinner spnrIssue;
	EditText txtRemarks;
	Button btnShowDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		
		btnShowDialog = (Button) findViewById(R.id.btnShowDialog);
		
		btnShowDialog.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showDialogBox(v);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.launcher, menu);
		return true;
	}

	
	
	private void showDialogBox(View dialogView) {
		LayoutInflater layoutInflater = this.getLayoutInflater();
		dialogView = layoutInflater.inflate(
				R.layout.dialogbox_for_complain_management, null);

		spnrIssue = (Spinner) dialogView.findViewById(R.id.spnrIssueType);
		txtRemarks = (EditText) dialogView.findViewById(R.id.txtRemarks);
		

		Button cancel = (Button) dialogView.findViewById(R.id.btnCancel);
		Button add = (Button) dialogView.findViewById(R.id.btnOK);


		final Dialog dialog = new Dialog(this);
		dialog.setContentView(dialogView);
		dialog.setTitle("Custom Dialog Box");

		cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				dialog.dismiss();
			}
		});
		
		spnrIssue.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int pos, long arg3) {
				if( pos == 1 ){
					Toast.makeText(LauncherActivity.this, "First Item is selected", 500).show();
				} else if( pos == 2 ){
					Toast.makeText(LauncherActivity.this, "Second Item is selected", 500).show();
				} else{
					Toast.makeText(LauncherActivity.this, "No Item is selected", 500).show();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				dialog.dismiss();
				Toast.makeText(LauncherActivity.this, "Remark: " + txtRemarks.getText(), Toast.LENGTH_LONG).show();
			}
		});
		dialog.show();
	}
	
}
