package com.errorpoint.customspinner.adapter;

import com.errorpoint.customspinner.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomSpinnerAdapter extends ArrayAdapter<String> {
	Context context;
	String[] iName;
	String[] iAbout;
	Integer[] iIcon;
	
	TextView spnItemName,spnItemAbout;
	ImageView spnItemIcon;
	
	public CustomSpinnerAdapter(Context context, int textViewResourceId, String[] objects, String[] iName,String[] iAbout,Integer[] iIcon){
		super(context,textViewResourceId,objects);
		this.context = context;
		this.iName = iName;
		this.iAbout = iAbout;
		this.iIcon = iIcon;
	}
	
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent){
		return getCustomView(position, convertView, parent);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		return getCustomView(position, convertView, parent);
	}
	
	public View getCustomView(final int position, View convertView, ViewGroup parent){
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		View rowView =  inflater.inflate(R.layout.spinner_row, parent, false);
		
		spnItemName = (TextView) rowView.findViewById(R.id.spnItemName);
		spnItemAbout = (TextView) rowView.findViewById(R.id.spnItemAbout);
		spnItemIcon = (ImageView) rowView.findViewById(R.id.spnItemIcon);
		
		
		spnItemName.setText(iName[position]);
		spnItemAbout.setText(iAbout[position]);
		spnItemIcon.setImageResource(iIcon[position]);
		return rowView;
	}
}
