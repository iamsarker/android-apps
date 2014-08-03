package com.errorpoint.expendedlistview.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.errorpoint.expendedlistview.R;
import com.errorpoint.expendedlistview.entity.PromoMaterials;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class PromoMaterialsListAdapter extends BaseExpandableListAdapter{
	
	private Context _context;
	private List<String> _listDataHeader; // header titles
	private HashMap<String, ArrayList<PromoMaterials>> _listDataChild;
	
	public PromoMaterialsListAdapter(Context context, List<String> listDataHeader,
			HashMap<String, ArrayList<PromoMaterials>> listChildData) {
		this._context = context;
		this._listDataHeader = listDataHeader;
		this._listDataChild = listChildData;
	}
	
	
	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosititon).getPromoProductName();
	}
	
	public int getProductBudget(int groupPosition, int childPosititon) {
		return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosititon).getTeritoryBudget();
	}
	
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return Long.parseLong(this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosition).getCategoryId()+"");
	}
	
	@Override
	public View getChildView(final int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		
		final String productName = (String) getChild(groupPosition, childPosition);
		long productId = getChildId(groupPosition, childPosition);
		int budget = getProductBudget(groupPosition, childPosition);
		
		ChildHolder holder = new ChildHolder();
		
		if (convertView == null) {
			
			holder = new ChildHolder();
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.list_promo_items, null);
			
			holder.productIdView = (TextView) convertView.findViewById(R.id.promoProductId);
			holder.productNameView = (TextView) convertView.findViewById(R.id.promoProductName);
			holder.tvBudget = (TextView) convertView.findViewById(R.id.tvTeritoryBudget);
			holder.tvConfirmQty = (TextView) convertView.findViewById(R.id.tvConfirmQty);
			holder.chkGotAll = (CheckBox) convertView.findViewById(R.id.chkGetAll);
			
			convertView.setTag(holder);
		} else{
			holder =(ChildHolder) convertView.getTag();
		}
		
		holder.productIdView.setText(productId+"");
		holder.productNameView.setText(productName);
		holder.tvBudget.setText(budget + "");
		
		if(holder.tvConfirmQty.equals("") || holder.tvConfirmQty == null){
			holder.tvConfirmQty.setText(budget + "");
		}
		
		holder.tvConfirmQty.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable value) {
				
				try{					
					//Toast.makeText(_context, DoctorVisit.GLOBAL_CHILD_DATA_LIST.get(_listDataHeader.get(groupPosition)).get(childPosition).getConfirmQty() + "", 500).show();
				} catch(Exception e){
					Toast.makeText(_context, e.getMessage() + ":" + groupPosition + ":" + childPosition, Toast.LENGTH_SHORT).show();
				}
				
			}
		});
		
		holder.chkGotAll.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				CheckBox chkBox = (CheckBox) view;
				
				TableRow tr = (TableRow) chkBox.getParent().getParent();
				EditText et = (EditText) tr.getChildAt(1);
				TextView tbudget = (TextView) tr.getChildAt(0);
				
				if( chkBox.isChecked() ){
					et.setFocusable(false);
					et.setFocusableInTouchMode(false);
					et.setText(tbudget.getText());
					
					try{
						//Toast.makeText(_context, DoctorVisit.GLOBAL_CHILD_DATA_LIST.get(_listDataHeader.get(groupPosition)).get(childPosition).getConfirmQty() + "", 500).show();
					} catch(Exception e){
						//Toast.makeText(_context, groupPosition + ":" + childPosition, 500).show();
					}
					
					
				} else{
					et.setFocusable(true);
					et.setFocusableInTouchMode(true);
				}
			}
		});
		
		convertView.setBackgroundColor(Color.rgb(118,131,231));
		
//		if(childPosition % 2 == 0)
//		{
//			convertView.setBackgroundColor(Color.rgb(118,131,231));
//		} else {
//			convertView.setBackgroundColor(Color.rgb(240,240,240));
//		}
		
		return convertView;
	}
	
	@Override
	public int getChildrenCount(int groupPosition) {
		if( this._listDataChild.get(this._listDataHeader.get(groupPosition)) != null ){
			return this._listDataChild.get(this._listDataHeader.get(groupPosition)).size();
		} else{
			return 0;
		}
	}
	
	@Override
	public Object getGroup(int groupPosition) {
		return this._listDataHeader.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		if( this._listDataHeader != null ){
			return this._listDataHeader.size();
		} else{
			return 0;
		}
	}
	
	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}
	
	@Override
	public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		String headerTitle = (String) getGroup(groupPosition);
		
		GroupHolder holder = new GroupHolder();
		
		if (convertView == null) {
			holder = new GroupHolder();
			
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.list_promo_header, null);
			
			holder.lblHeaderText = (TextView) convertView
					.findViewById(R.id.lblListHeader);
			holder.lblHeaderText.setTypeface(null, Typeface.BOLD);
			holder.lblHeaderText.setText(headerTitle);
			
			convertView.setTag(holder);
		} else{
			holder = (GroupHolder) convertView.getTag();
		}
		return convertView;
	}
	
	static class GroupHolder{
		TextView lblHeaderText;
	}
	
	static class ChildHolder{
		TextView productIdView;
		TextView productNameView;
		TextView tvBudget;
		TextView tvConfirmQty;
		CheckBox chkGotAll;
	}
	
	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
