package com.errorpoint.customlistview.adapter;

import java.text.BreakIterator;
import java.util.ArrayList;

import com.errorpoint.customlistview.R;
import com.errorpoint.customlistview.entity.Doctor;
import com.errorpoint.customlistview.global.DoctorVisit;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.DropBoxManager;
import android.provider.Settings.Global;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;



public class DoctorPlanAdapter extends ArrayAdapter<Doctor> implements OnClickListener {
	
	Activity context;

	public DoctorPlanAdapter(Activity context, ArrayList<Doctor> doctors)  {
		super(context, R.layout.list_doctor_plan, doctors);
		this.context = context;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		try {
			ViewHolder holder = new ViewHolder();
			Doctor doctor = DoctorVisit.DOCTOR_PLAN_LIST.get(position);
			if (view == null) {
				holder = new ViewHolder();
				LayoutInflater layoutInflater = context.getLayoutInflater();
				view = layoutInflater.inflate(R.layout.list_doctor_plan, null,
						false);
                holder.llDoctorPotential=(LinearLayout) view
						.findViewById(R.id.llDoctorPotential);
				holder.textViewDoctorPotential=(TextView) view
						.findViewById(R.id.textViewDoctorPotential);
				holder.textViewDoctorName = (TextView) view
						.findViewById(R.id.textViewDoctorName);
				holder.textViewDoctorId = (TextView) view
						.findViewById(R.id.textViewDoctorId);
				holder.cbMorning = (CheckBox) view
						.findViewById(R.id.cbMorning);
				holder.cbMorning.setOnClickListener(this);

				holder.cbEvening = (CheckBox) view
						.findViewById(R.id.cbEvning);
				
				holder.cbEvening.setOnClickListener(this);
			
				
				view.setTag(holder);

			} else {
				holder = (ViewHolder) view.getTag();
			}
			 
			holder.textViewDoctorPotential.setText(""+ doctor.getFrequency());
			if( doctor.getFrequency().equalsIgnoreCase("A")){
				holder.llDoctorPotential.setBackgroundColor(Color.rgb(0, 100,0));
				
			}else if (doctor.getFrequency().equalsIgnoreCase("B")){

				holder.llDoctorPotential.setBackgroundColor(Color.rgb(255, 140,0));
				
			}else if(doctor.getFrequency().equalsIgnoreCase("C")){

				holder.llDoctorPotential.setBackgroundColor(Color.rgb(238, 238,0));
			}
			holder.textViewDoctorName.setText("" + doctor.getName());
			holder.textViewDoctorId.setText("" + doctor.getCode());
			if (doctor.isSelected()) {
				if (doctor.getShift().equalsIgnoreCase("1")) {
					holder.cbEvening.setChecked(true);
					holder.cbMorning.setChecked(false);
				} else if(doctor.getShift().equalsIgnoreCase("0")){
					holder.cbMorning.setChecked(true);
					holder.cbEvening.setChecked(false);
				}else{
					holder.cbEvening.setChecked(false);
					holder.cbMorning.setChecked(false);
				}

			} else {
				
				holder.cbEvening.setChecked(false);
				holder.cbMorning.setChecked(false);
			}
			holder.cbEvening.setTag(doctor);
			holder.cbMorning.setTag(doctor);

		} catch (Exception e) {

		}
		
		
		if(position % 2 == 0)
		{
			view.setBackgroundColor(Color.rgb(118,131,231));
		}
		else{
			view.setBackgroundColor(Color.rgb(240,240,240));
		}
		
		return view;
	}

	static class ViewHolder {
		LinearLayout llDoctorPotential;
		TextView textViewDoctorName;
		TextView textViewDoctorId;
		TextView textViewDoctorPotential;
		CheckBox cbMorning;
		CheckBox cbEvening;
	}

	@Override
	public void onClick(View view) {
		
		
		CheckBox cbF = (CheckBox) view;
		Doctor doctorF =(Doctor)cbF.getTag();
		
//		LinearLayout llLayoutP = (LinearLayout) cbF.getParent(); // get Check box parent layout
//		LinearLayout llLayoutPL = (LinearLayout) llLayoutP.getParent(); // get xml file parent layout
//		LinearLayout llLayoutF = (LinearLayout) llLayoutPL.getChildAt(0); // get first linear layout of xml file
//		
//		TextView tvDoctorId = (TextView) llLayoutF.getChildAt(1);
		
		String doctorId = doctorF.getCode(), freq = doctorF.getFrequency();
		   
		//Toast.makeText(context, doctorId + "", 500).show();
		
		//if( DoctorCallPlanCheck.isDoctorAvailable(doctorId,freq) )
		{
		
			if(view instanceof CheckBox ){
				CheckBox cb = (CheckBox) view;
				 Doctor doctor =(Doctor)cb.getTag();
				   if(cb.isChecked()){
					  
					   if(cb.getId()==R.id.cbEvning){
						   doctor.setShift("1");
					   }else if(cb.getId()==R.id.cbMorning) {
						   doctor.setShift("0");
					   }
					   
					   //Toast.makeText(context, doctor.getName() + "", 500).show();
					   
					   doctor.setSelected(true);
					   LinearLayout llLayout = (LinearLayout) cb.getParent();
					   
					   for(int i=0; i<((ViewGroup)llLayout).getChildCount(); ++i) {
						    View nextChild = ((ViewGroup)llLayout).getChildAt(i);
						    if(nextChild instanceof CheckBox && nextChild.getId()==cb.getId() ){
						    	
						    	
						    }else if (nextChild instanceof CheckBox && nextChild.getId()!=cb.getId() ){
						    	
						    	CheckBox cb2=(CheckBox) nextChild;
						    	cb2.setChecked(false);
						    	
				            }
						}
				   }else{
					   doctor.setShift("EVENING");
					   doctor.setSelected(false);
				   }
				
			}
		} 
//		else
//		{
//			CheckBox cb = (CheckBox) view;
//			Doctor doctor =(Doctor)cb.getTag();
//			doctor.setSelected(false);
//			
//			 LinearLayout llLayout = (LinearLayout) cb.getParent();
//			   
//			   for(int i=0; i<((ViewGroup)llLayout).getChildCount(); ++i) {
//				    View nextChild = ((ViewGroup)llLayout).getChildAt(i);
//				    if(nextChild instanceof CheckBox && nextChild.getId()==cb.getId() ){
//				    	CheckBox cb2=(CheckBox) nextChild;
//				    	cb2.setChecked(false);
//				    }else if (nextChild instanceof CheckBox && nextChild.getId()!=cb.getId() ){
//				    	CheckBox cb2=(CheckBox) nextChild;
//				    	cb2.setChecked(false);
//				    	
//		            }
//				}
//			
//			Toast.makeText(context, "You have already cross max plan/call limit", 700).show();
//		}
		
	}
}
