package com.example.fragmentexample;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends FragmentActivity implements OnClickListener {
	
	Button btnOne, btnTwo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnOne = (Button) findViewById(R.id.btnOne);
		btnTwo = (Button) findViewById(R.id.btnTwo);
		
		btnOne.setOnClickListener(this);
		btnTwo.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		switch(v.getId()){
		case R.id.btnOne:
			FirstFragment ff = new FirstFragment();
			ft.replace(R.id.fragment_container, ff);
			ft.commit();
			
			break;
		case R.id.btnTwo:
			SecondFragment sf = new SecondFragment();
			ft.replace(R.id.fragment_container, sf);
			ft.commit();
			
			break;
		}
		
	}
	

}
