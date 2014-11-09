package com.errorpoint.dynamicspinner;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	Spinner spnView3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		spnView3 = (Spinner) findViewById(R.id.spnView3);
		
		ArrayList<String> aList = new ArrayList<String>();
		aList.add("--Select--");
		aList.add("Md. Shahadat Sarker");
		aList.add("Developer");
		aList.add("ErrrorPoint");
		
		spnView3.setAdapter(new SpinnerAdapter(this, R.layout.spinner_row, aList, aList));
		
		Toast.makeText(this, "Selected: " + spnView3.getSelectedItem(), 500).show();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
