package com.errorpoint.customalertbox;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class LauncherActivity extends Activity implements OnClickListener {
	
	Button btnConfirm, btnWarning;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		btnConfirm = (Button) findViewById(R.id.btnConfirm);
		btnWarning = (Button) findViewById(R.id.btnWarning);
		
		btnConfirm.setOnClickListener(this);
		btnWarning.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.btnConfirm:
				AlertDialog.Builder alertbox = new AlertDialog.Builder(LauncherActivity.this);
				alertbox.setTitle("Are you sure?");
				alertbox.setPositiveButton("YES", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(LauncherActivity.this, "You Choose Yes!!", Toast.LENGTH_LONG).show();
					}
				});
				alertbox.setNegativeButton("NO", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(LauncherActivity.this, "You Choose Nooo!!", Toast.LENGTH_LONG).show();
					}
				});
				alertbox.show();
			break;
		case R.id.btnWarning:
			showDialogBox(v);
			break;
		}
	}
	
	private void showDialogBox(View dialogView) {
		LayoutInflater layoutInflater = this.getLayoutInflater();
		dialogView = layoutInflater.inflate(
				R.layout.dialogbox_layout, null);
		
		
		Button cancel = (Button) dialogView.findViewById(R.id.btnCancel);
		Button add = (Button) dialogView.findViewById(R.id.btnOK);
		
		final Dialog dialog = new Dialog(this);
		dialog.setContentView(dialogView);
		dialog.setTitle("Confirmation Alert Box");
		
		cancel.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View arg0) {
				Toast.makeText(LauncherActivity.this, "You Choose Cancel!!", Toast.LENGTH_LONG).show();
				dialog.dismiss();
			}
		});
		
		add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(LauncherActivity.this, "You Choose Continue", Toast.LENGTH_LONG).show();
				dialog.dismiss();
			}
		});
		dialog.show();
	}
	
}
