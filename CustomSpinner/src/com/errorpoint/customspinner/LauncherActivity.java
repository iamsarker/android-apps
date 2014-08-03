package com.errorpoint.customspinner;

import java.util.ArrayList;

import com.errorpoint.customspinner.adapter.CustomSpinnerAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class LauncherActivity extends Activity {
	
	Spinner spnView1,spnView2,spnView3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		
		spnView1 = (Spinner) findViewById(R.id.spnView1);
		spnView2 = (Spinner) findViewById(R.id.spnView2);
		spnView3 = (Spinner) findViewById(R.id.spnView3);
		
		ArrayList<String> aList = new ArrayList<String>();
		aList.add("Md. Shahadat");
		aList.add("Sarker");
		aList.add("Developer");
		aList.add("ErrrorPoint");
		
		
		ArrayAdapter<CharSequence> spnViewAdapter1 = ArrayAdapter.createFromResource(this, R.array.res_array_list, R.layout.spinner_item);
		spnViewAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnView1.setAdapter(spnViewAdapter1);
		
		
		ArrayAdapter<String> spnViewAdapter2 = new ArrayAdapter<String>(this, R.layout.spinner_item, aList);
		spnViewAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnView2.setAdapter(spnViewAdapter2);
		
		String[] iName = {"Md. Shahadat Sarker","Android","Mobile"};
		String[] iAbout = {"The Developer","This is an OS","A Device"};
		Integer[] iICon = {R.drawable.sarker,R.drawable.ic_launcher,R.drawable.ic_launcher};
		
		spnView3.setAdapter(new CustomSpinnerAdapter(this, R.layout.spinner_row, iName, iName, iAbout, iICon));
		
		Toast.makeText(this, "Selected: " + spnView3.getSelectedItem(), 500).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.launcher, menu);
		return true;
	}
	
}
