package com.findingsoft.studentregistration;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class LauncherActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		
		Button btnGO = (Button)findViewById(R.id.btnGODept);
		
		btnGO.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				gotoNewActivity();
			}
		});
		
	}
	
	private void gotoNewActivity(){
		Intent intent = new Intent(getApplicationContext(),ManageDepartment.class);
		startActivity(intent);
	}

}
