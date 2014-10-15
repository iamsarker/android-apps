package com.errorpoint.customtoast;

import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		View v = findViewById(R.id.btnCustomToast);
		v.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				customToast();
			}
		});
		
		
		v = findViewById(R.id.btnDefaultToast);
		v.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "Md. Shahadat Sarker", Toast.LENGTH_LONG).show();
			}
		});
		
	}
	
	private void customToast(){
		Toast toast = new Toast(this);
		ImageView developerImage;
		TextView tvName;
		
		LayoutInflater inflater = getLayoutInflater();
		View toastLayout = inflater.inflate(R.layout.toast_layout, (ViewGroup)findViewById(R.id.toast_layout_id) );
		
		developerImage = (ImageView) toastLayout.findViewById(R.id.imageView);
		tvName = (TextView) toastLayout.findViewById(R.id.textView);
		
		developerImage.setImageResource(R.drawable.developer);
		tvName.setText("Md. Shahadat Sarker");
		
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 50); // positioned the toast on the screen
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setView(toastLayout);
		toast.show();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
