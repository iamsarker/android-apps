package com.errorpoint.customgridview;

import java.util.ArrayList;

import com.errorpoint.customgridview.adapter.GridViewAdapter;
import com.errorpoint.customgridview.entity.GridViewDetails;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.GridView;

public class LauncherActivity extends Activity {
	GridView customGridView;
	ArrayList<GridViewDetails> gridViewDetailsList;
	GridViewDetails gridViewDetails;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		
		customGridView = (GridView) findViewById(R.id.customGridView);
		
		gridViewDetailsList = new ArrayList<GridViewDetails>();
		
		
		gridViewDetails = new GridViewDetails();
		gridViewDetails.setItemName("Md. Shahadat");
		gridViewDetails.setItemIcon(R.drawable.sarker);
		gridViewDetails.setSysTime("12:30 AM");
		gridViewDetailsList.add(gridViewDetails);
		
		gridViewDetails = new GridViewDetails();
		gridViewDetails.setItemName("Imran");
		gridViewDetails.setItemIcon(R.drawable.item_icon);
		gridViewDetails.setSysTime("12:30 AM");
		gridViewDetailsList.add(gridViewDetails);
		
		gridViewDetails = new GridViewDetails();
		gridViewDetails.setItemName("Arif Ovi");
		gridViewDetails.setItemIcon(R.drawable.item_icon);
		gridViewDetails.setSysTime("12:30 AM");
		gridViewDetailsList.add(gridViewDetails);
		
		gridViewDetails = new GridViewDetails();
		gridViewDetails.setItemName("Kaysarul");
		gridViewDetails.setItemIcon(R.drawable.item_icon);
		gridViewDetails.setSysTime("12:30 AM");
		gridViewDetailsList.add(gridViewDetails);
		
		gridViewDetails = new GridViewDetails();
		gridViewDetails.setItemName("Ashim");
		gridViewDetails.setItemIcon(R.drawable.item_icon);
		gridViewDetails.setSysTime("12:30 AM");
		gridViewDetailsList.add(gridViewDetails);
		
		gridViewDetails = new GridViewDetails();
		gridViewDetails.setItemName("Zobayer");
		gridViewDetails.setItemIcon(R.drawable.item_icon);
		gridViewDetails.setSysTime("12:30 AM");
		gridViewDetailsList.add(gridViewDetails);
		
		gridViewDetails = new GridViewDetails();
		gridViewDetails.setItemName("Rehan");
		gridViewDetails.setItemIcon(R.drawable.item_icon);
		gridViewDetails.setSysTime("12:30 AM");
		gridViewDetailsList.add(gridViewDetails);
		
		gridViewDetails = new GridViewDetails();
		gridViewDetails.setItemName("Tanim");
		gridViewDetails.setItemIcon(R.drawable.customer_list);
		gridViewDetails.setSysTime("12:30 AM");
		gridViewDetailsList.add(gridViewDetails);
		
		customGridView.setAdapter(new GridViewAdapter(this,R.layout.gridview_items,gridViewDetailsList));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.launcher, menu);
		return true;
	}

}
