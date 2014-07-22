package adapters;

import gateways.CourseGateWay;
import java.util.ArrayList;

import com.findingsoft.studentregistration.ManageCourses;
import com.findingsoft.studentregistration.R;

import utilities.Courses;
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

public class CourseAdapter extends ArrayAdapter<Courses> {

	private final ArrayList<Courses> aCore;
	private Context context;

	TextView txtCodeC, txtNameC;
	ImageView deleteCorseImg;

	
	public CourseAdapter(Context context, ArrayList<Courses> aCore) {
		super(context, R.layout.controlls_course);
		// TODO Auto-generated constructor stub
		this.aCore = aCore;
		this.context = context;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowVirw = inflater.inflate(R.layout.controlls_course, parent,	false);
		txtCodeC = (TextView)rowVirw.findViewById(R.id.courseTitle);
		txtNameC = (TextView)rowVirw.findViewById(R.id.courseName);
		deleteCorseImg = (ImageView)rowVirw.findViewById(R.id.deleteCourseImg);
		
		txtCodeC.setText(aCore.get(position).getcCode());
		txtNameC.setText(aCore.get(position).getcName());
		
		final String courseCode = aCore.get(position).getcCode();
		final String courseName = aCore.get(position).getcName();

		
		deleteCorseImg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				deleteCourses(courseCode,courseName);
			}
		});
		
		return rowVirw;
	}

	private void deleteCourses(String strCode, String strName) {
		CourseGateWay cGateW = new CourseGateWay(this.context);
		String str = cGateW.deleteCourse(strCode);
		Toast.makeText(this.context, str, Toast.LENGTH_SHORT).show();
		
		ManageCourses aMC = new ManageCourses();
		String dCode = aMC.departmentCode, dName = aMC.departmentName;
		
		Intent intent = new Intent(this.context,ManageCourses.class);
		intent.putExtra("depCode", dCode);
		intent.putExtra("depName", dName);
		this.context.startActivity(intent);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.aCore.size();
	}

	@Override
	public Courses getItem(int position) {
		// TODO Auto-generated method stub
		return this.aCore.get(position);
	}

	@Override
	public int getPosition(Courses item) {
		// TODO Auto-generated method stub
		return super.getPosition(item);
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

}
