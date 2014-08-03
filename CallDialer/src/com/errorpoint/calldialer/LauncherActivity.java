package com.errorpoint.calldialer;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class LauncherActivity extends Activity implements OnItemClickListener{
	
	ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		
		listView = (ListView) findViewById(R.id.listView);
		
		String[] aList = {"01824880161","01536121323"};
		
		ArrayAdapter<String> adaper = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, aList);
		
		listView.setAdapter(adaper);
		
		listView.setOnItemClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.launcher, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg, View view, int pos, long parent) {
		String num = listView.getItemAtPosition(pos)+"";
		
		Intent callIntent = new Intent(Intent.ACTION_CALL);
		callIntent.setData(Uri.parse("tel:"+num));
		this.startActivity(callIntent);
	}

}
