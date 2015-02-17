package com.example.newtest;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.ToggleButton;

public class MainActivity extends Activity  implements OnClickListener{
	ToggleButton tb1, tb2, tb3, tb4, tb5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
				
		tb1 = (ToggleButton) findViewById(R.id.number_one);
		tb2 = (ToggleButton) findViewById(R.id.number_two);
		tb3 = (ToggleButton) findViewById(R.id.number_three);
		tb4 = (ToggleButton) findViewById(R.id.number_four);
		tb5 = (ToggleButton) findViewById(R.id.number_five);
		
		tb1.setOnClickListener(this);
		tb2.setOnClickListener(this);
		tb3.setOnClickListener(this);
		tb4.setOnClickListener(this);
		tb5.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		ToggleButton tb = (ToggleButton) v;
		
		
		RadioGroup llLayout = (RadioGroup) tb.getParent();
		
        for(int i=0; i<((ViewGroup)llLayout).getChildCount(); ++i) {
            View nextChild = ((ViewGroup)llLayout).getChildAt(i);
            
            ToggleButton cb2=(ToggleButton) nextChild;
            
            if(nextChild instanceof ToggleButton && nextChild.getId()==tb.getId() ){
            	//cb2.setChecked(false);
            }else if (nextChild instanceof ToggleButton && nextChild.getId()!=tb.getId() ){
                cb2.setChecked(false);
            }
        }
		
	}
}