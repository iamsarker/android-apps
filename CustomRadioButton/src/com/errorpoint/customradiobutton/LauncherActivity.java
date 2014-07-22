package com.errorpoint.customradiobutton;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class LauncherActivity extends Activity implements  OnClickListener {
	
	RadioGroup rdoGroup;
	RadioButton rdoMale, rdoFemale;
	Button btnShowToast;
	TextView resText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		initializeControls();
		eventRegister();
	}
	
	private void initializeControls(){
		rdoGroup = (RadioGroup) findViewById(R.id.rdoGroup);
		rdoMale = (RadioButton) findViewById(R.id.rdoMale);
		rdoFemale = (RadioButton) findViewById(R.id.rdoFemale);
		resText = (TextView) findViewById(R.id.resText);
		btnShowToast = (Button) findViewById(R.id.btnToast);
	}
	
	private void eventRegister(){
		btnShowToast.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.launcher, menu);
		return true;
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch(view.getId()){
		case R.id.btnToast:
			if(rdoMale.isChecked()){
				resText.setText("Hi friend, How are you?");
			}
			if(rdoFemale.isChecked()){
				resText.setText("Hi darling, I miss you...");
			}
			
			break;
		}
	}

}
