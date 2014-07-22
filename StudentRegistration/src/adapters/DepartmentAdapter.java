package adapters;

import java.util.ArrayList;

import com.findingsoft.studentregistration.ManageCourses;
import com.findingsoft.studentregistration.ManageStudents;
import com.findingsoft.studentregistration.R;

import utilities.Departments;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DepartmentAdapter extends ArrayAdapter<Departments> {

	private final ArrayList<Departments> aDept;
	private Context context;

	TextView txtDCode, txtDName;
	ImageView addCorseImg, addStudedtImg;

	public DepartmentAdapter(Context context, ArrayList<Departments> aDept) {
		super(context, R.layout.controlls_departement);
		// TODO Auto-generated constructor stub
		this.aDept = aDept;
		this.context = context;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowVirw = inflater.inflate(R.layout.controlls_departement, parent,
				false);
		txtDCode = (TextView) rowVirw.findViewById(R.id.courseTitle);
		txtDName = (TextView) rowVirw.findViewById(R.id.courseName);

		addCorseImg = (ImageView) rowVirw.findViewById(R.id.addCourseImg);
		addStudedtImg = (ImageView) rowVirw.findViewById(R.id.addStudentImg);
		
		final String deptCode = aDept.get(position).getDeptCode();
		final String deptName = aDept.get(position).getDeptName();
		
		addCorseImg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				addCourses(deptCode,deptName);
			}
		});
		addStudedtImg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				addStudents(deptCode,deptName);
			}
		});

		txtDCode.setText(aDept.get(position).getDeptCode());
		txtDName.setText(aDept.get(position).getDeptName());

		return rowVirw;
	}
	
	private void addCourses(String strCode, String strName){
		Intent intent = new Intent(this.context, ManageCourses.class);
		intent.putExtra("depCode", strCode);
		intent.putExtra("depName", strName);
		this.context.startActivity(intent);
	}
	
	private void addStudents(String strCode, String strName){
		Intent intent = new Intent(this.context, ManageStudents.class);
		intent.putExtra("depCode", strCode);
		intent.putExtra("depName", strName);
		this.context.startActivity(intent);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.aDept.size();
	}

	@Override
	public Departments getItem(int position) {
		// TODO Auto-generated method stub
		return this.aDept.get(position);
	}

	@Override
	public int getPosition(Departments item) {
		// TODO Auto-generated method stub
		return super.getPosition(item);
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

}
