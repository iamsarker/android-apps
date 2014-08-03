package com.errorpoint.customgridview.adapter;

import java.util.ArrayList;
import java.util.List;

import com.errorpoint.customgridview.R;
import com.errorpoint.customgridview.entity.GridViewDetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridViewAdapter extends ArrayAdapter<GridViewDetails>{
	
	private Context context;
	private final ArrayList<GridViewDetails> gridViewDetailsList;
	
    TextView textView;
    ImageView imgView;
	
	public GridViewAdapter(Context context, int resource, List<GridViewDetails> gridViewDetailsList) {
		super(context, resource, gridViewDetailsList);
		this.context = context;
		this.gridViewDetailsList = (ArrayList<GridViewDetails>) gridViewDetailsList;
	}
	
	@Override
	public View getView(final int position,View convertView, ViewGroup parent){
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.gridview_items, parent, false);
		
		GridViewDetails gridViewDetails = gridViewDetailsList.get(position);
		
		final TextView sysTime = (TextView) rowView.findViewById(R.id.sysTime);
		final TextView itemName = (TextView) rowView.findViewById(R.id.itemName);
		final ImageView itemIcon = (ImageView) rowView.findViewById(R.id.itemIcon);
		
		sysTime.setText(gridViewDetails.getSysTime());
		itemName.setText(gridViewDetails.getItemName());
		itemIcon.setImageResource(gridViewDetails.getItemIcon());
		
		return rowView;
	}
	
	private void setBackgroundImage(int pos){
		//bitmap = BitmapFactory.decodeResource(getResources(), iconHolder.get(pos).getIconResourceId()); 
	}
	
	@Override
    public int getCount() {
        return gridViewDetailsList.size();
    }

    @Override
    public GridViewDetails getItem(int position) {

        return gridViewDetailsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
