package net.iecl.sinorise.doctorvisit.custom.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.iecl.sinorise.R;
import net.iecl.sinorise.doctorvisit.entity.PromoMaterials;
import net.iecl.sinorise.global.DoctorVisit;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class PromoMaterialsListAdapter extends BaseExpandableListAdapter{
	
	private Context _context;
	private final List<String> _listDataHeader; // header titles
	private final HashMap<String, ArrayList<PromoMaterials>> _listDataChild;
	
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
		return Long.parseLong(this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosition).getSubCategoryId()+"");
	}
	
	@Override
	public View getChildView(final int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		
		
		ChildHolder holder = new ChildHolder();
		
		if (convertView == null) {
			
			holder = new ChildHolder();
			LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.list_promo_items, null);
			
			holder.productIdView = (TextView) convertView.findViewById(R.id.promoProductId);
			holder.productNameView = (TextView) convertView.findViewById(R.id.promoProductName);
			holder.tvBudget = (TextView) convertView.findViewById(R.id.tvTeritoryBudget);
			holder.tvConfirmQty = (TextView) convertView.findViewById(R.id.tvConfirmQty);
			holder.chkGotAll = (CheckBox) convertView.findViewById(R.id.chkGetAll);
			
			
			holder.chkGotAll.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View view) {
					
					final CheckBox chkBox = (CheckBox) view;
					PromoMaterials pMaterials=(PromoMaterials)view.getTag();
					final TableRow tr = (TableRow) chkBox.getParent().getParent();
					final EditText et = (EditText) tr.getChildAt(1);
					final TextView tbudget = (TextView) tr.getChildAt(0);
					
					if( chkBox.isChecked() ){
						et.setFocusable(false);
						et.setFocusableInTouchMode(false);
						et.setText(tbudget.getText());
						
						try{
							pMaterials.setSelectd(true);
						} catch(Exception e){
							Log.e("Error Selection1", e.getMessage() + "");
						}
						
						try{

							pMaterials.setConfirmQty( Integer.parseInt(tbudget.getText().toString()) );
							
							
						} catch(Exception e){
							//Toast.makeText(_context, groupPosition + ":" + childPosition, 500).show();
						}
						
						
					} else{
						et.setFocusable(true);
						et.setFocusableInTouchMode(true);
						et.setText("");
						try{
							pMaterials.setSelectd(false);
						} catch(Exception e){
							Log.e("Error Selection2", e.getMessage() + "");
						}
					}
				}
			});
			
	
			CustomTextWatcher tw=new CustomTextWatcher(holder.tvConfirmQty);
			
			holder.tvConfirmQty.setFocusable(true);
			holder.tvConfirmQty.setFocusableInTouchMode(true);
			
			holder.tvConfirmQty.addTextChangedListener(tw);
			
			convertView.setTag(holder);
		} else{
			holder = (ChildHolder) convertView.getTag();
		}
		
	    PromoMaterials pMaterials = DoctorVisit.GLOBAL_CHILD_DATA_LIST.get(_listDataHeader.get(groupPosition)).get(childPosition);
		
		holder.productIdView.setText(pMaterials.getProductId()+"");
		holder.productNameView.setText(pMaterials.getSubCategoryName());
		holder.tvBudget.setText(pMaterials.getTeritoryBudget() + "");
		holder.chkGotAll.setTag(pMaterials);
		holder.tvConfirmQty.setTag(pMaterials);
		
		if( pMaterials.isSelectd() ){
		
			holder.chkGotAll.setChecked(true);
			//holder.tvConfirmQty.setText( pMaterials.getConfirmQty() + "" );
		} else{
			holder.chkGotAll.setChecked(false);
			//holder.tvConfirmQty.setText("");
		}  
		
		holder.tvConfirmQty.setText( pMaterials.getConfirmQty() + "" );
		
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
		final String headerTitle = (String) getGroup(groupPosition);
		
		GroupHolder holder = new GroupHolder();
		
		if (convertView == null ) {
			holder = new GroupHolder();
			
			LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.list_promo_header, null);
			
			holder.lblHeaderText = (TextView) convertView.findViewById(R.id.lblListHeader);
			holder.lblHeaderText.setTypeface(null, Typeface.BOLD);
			
			convertView.setTag(holder);
		} else{
			holder = (GroupHolder) convertView.getTag();
		}
		int n = 0;
		try{
			n = _listDataChild.get(headerTitle).size();
		} catch(Exception e){
			n = 0;
		}
		
		holder.lblHeaderText.setText(headerTitle+" : "+n);
		
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
	
	class CustomTextWatcher implements TextWatcher {
		private TextView  _editText; 
		
		public CustomTextWatcher(TextView editText) {
			this._editText=editText;
			// TODO Auto-generated constructor stub
		}

		@Override
		public void afterTextChanged(Editable value) {

			PromoMaterials promoMaterials =(PromoMaterials)_editText.getTag();
			try {
				promoMaterials.setConfirmQty( Integer.parseInt( value.toString()) );
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			// TODO Auto-generated method stub
			
		}

	}
	
}
