package com.errorpoint.expendedlistview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.errorpoint.expendedlistview.adapter.PromoMaterialsListAdapter;
import com.errorpoint.expendedlistview.demodata.DemoData;
import com.errorpoint.expendedlistview.entity.PromoMaterials;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ExpandableListView;

public class LauncherActivity extends Activity {
	
	ExpandableListView promoExpandableListView;
	
	PromoMaterialsListAdapter promoMaterialsListAdapter;
	List<String> headerDataList;
	ArrayList<PromoMaterials> padList = new ArrayList<PromoMaterials>();
	ArrayList<PromoMaterials> gimList = new ArrayList<PromoMaterials>();
	ArrayList<PromoMaterials> samList = new ArrayList<PromoMaterials>();
	
	HashMap<String, ArrayList<PromoMaterials>> childDataList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		
		promoExpandableListView = (ExpandableListView) findViewById(R.id.expandPromoList);
		prepareItemListData();
		
		try{
			promoMaterialsListAdapter = new PromoMaterialsListAdapter(this , headerDataList, childDataList);
			promoExpandableListView.setAdapter(promoMaterialsListAdapter);
		} catch(Exception e){}
	}

	
	private void prepareItemListData(){
		headerDataList = new ArrayList<String>();
		childDataList = new HashMap<String, ArrayList<PromoMaterials>>();
		//DoctorVisit.globalChildDataList = new HashMap<String, ArrayList<PromoMaterials>>();
		
		padList = DemoData.getPromoProductList(1);
		gimList = DemoData.getPromoProductList(2);
		samList = DemoData.getPromoProductList(3);
		
		headerDataList.add("Pad");
		headerDataList.add("Gimmick");
		headerDataList.add("Sample");
		
		childDataList.put(headerDataList.get(0), padList);
		childDataList.put(headerDataList.get(1), gimList);
		childDataList.put(headerDataList.get(2), samList);
		
	}
}
