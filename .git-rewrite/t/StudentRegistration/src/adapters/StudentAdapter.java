package adapters;

import java.util.ArrayList;

import com.findingsoft.studentregistration.EnrollCourses;
import com.findingsoft.studentregistration.R;

import utilities.Students;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class StudentAdapter extends ArrayAdapter<Students>{
	
	
	TextView txtRegNo, txtStudentName, txtStudentEmail;
	ImageView enrollCorseImg;

	
	private final ArrayList<Students> aStudentList;
	private Context context;
	
	public StudentAdapter(Context context, ArrayList<Students> aStudentList) {
		super(context, R.layout.controlls_student);
		// TODO Auto-generated constructor stub
		this.aStudentList = aStudentList;
		this.context = context;
	}
	
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowVirw = inflater.inflate(R.layout.controlls_student, parent,	false);
		txtRegNo = (TextView)rowVirw.findViewById(R.id.studentRegNo);
		txtStudentName = (TextView)rowVirw.findViewById(R.id.studentName);
		txtStudentEmail = (TextView)rowVirw.findViewById(R.id.studentEmail);
		
		enrollCorseImg = (ImageView)rowVirw.findViewById(R.id.enrollCourseImg);
		
		txtRegNo.setText(aStudentList.get(position).getRegNo());
		txtStudentName.setText(aStudentList.get(position).getStudentName());
		txtStudentEmail.setText(aStudentList.get(position).getStudentEmail());
		
		final String regNo = aStudentList.get(position).getRegNo();
		final String studentName = aStudentList.get(position).getStudentName();

		
		enrollCorseImg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				enrollCourses(regNo,studentName);
			}
		});
		
		return rowVirw;
	}

	private void enrollCourses(String strCode, String strName) {
		/*
		StudentGateWay sGateW = new StudentGateWay(this.context);
		String str = sGateW.deleteStudent(strCode);
		
		Toast.makeText(this.context, str, Toast.LENGTH_SHORT).show();
		*/
		Intent intent = new Intent(this.context,EnrollCourses.class);
		intent.putExtra("studentRegNo", strCode);
		intent.putExtra("studentName", strName);
		this.context.startActivity(intent);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.aStudentList.size();
	}

	@Override
	public Students getItem(int position) {
		// TODO Auto-generated method stub
		return this.aStudentList.get(position);
	}

	@Override
	public int getPosition(Students item) {
		// TODO Auto-generated method stub
		return super.getPosition(item);
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}


}
