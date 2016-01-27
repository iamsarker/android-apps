package com.technovalley21.installassetapk;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	Button btn1, btn2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				installApk();
			}
		});
		
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try{
					Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.technovalley21.helloworld");
					startActivity(launchIntent);
				} catch(Exception e){
					Toast.makeText(MainActivity.this, "Install Apk first", Toast.LENGTH_LONG).show();
				}
				
			}
		});
	}
	
	private void installApk(){
		AssetManager assetManager = getAssets();
		
		InputStream in = null;
		OutputStream out = null;
		
		try{
			
			in = assetManager.open("HelloWorld.apk");
		    out = new FileOutputStream("/sdcard/HelloWorld.apk");
		    
		    byte[] buffer = new byte[1024];
		    int read;
		    while((read = in.read(buffer)) != -1) {
		        out.write(buffer, 0, read);
		    }

		    in.close();
		    in = null;

		    out.flush();
		    out.close();
		    out = null;
		    
		    Intent intent = new Intent(Intent.ACTION_VIEW);
		    
		    intent.setDataAndType(Uri.fromFile(new File("/sdcard/HelloWorld.apk")),
		            "application/vnd.android.package-archive");
		    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		    
		    startActivity(intent);
		    
		    Toast.makeText(this, "Successfully Installed!", Toast.LENGTH_LONG).show();
			
		} catch(Exception e){
			Toast.makeText(this, "Unable to Install!", Toast.LENGTH_LONG).show();
		}
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
}
