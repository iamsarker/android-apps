package adapters;

import gateways.EnrollCourseGateWay;

import java.util.ArrayList;

import com.findingsoft.studentregistration.EnrollCourses;
import com.findingsoft.studentregistration.R;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import utilities.EnrollCourse;

public class EnrollAdapter extends ArrayAdapter<EnrollCourse>{

	private final ArrayList<EnrollCourse> enrollList;
	private Context context;
	
	TextView txtRegNo, txtStudentName, txtenCourseTitle, txtenCourseName, txtDeptCode;
	
	//ImageView enrollEditImg;
	//Button enrollEditImg;
	ImageButton enrollEditImg;

	
	public EnrollAdapter(Context context, ArrayList<EnrollCourse> enrollList) {
		super(context, R.layout.controll_enroll);
		// TODO Auto-generated constructor stub
		this.enrollList = enrollList;
		this.context = context;
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		final EditText txtResult;
		
		View rowVirw = inflater.inflate(R.layout.controll_enroll, parent, false);
		txtenCourseTitle = (TextView)rowVirw.findViewById(R.id.enCourseTitle);
		txtenCourseName = (TextView)rowVirw.findViewById(R.id.enCourseName);
		txtDeptCode = (TextView)rowVirw.findViewById(R.id.enDeptCode);
		
		txtResult = (EditText)rowVirw.findViewById(R.id.enCourseResult);
		
		enrollEditImg = (ImageButton)rowVirw.findViewById(R.id.enEditResult);
		
		txtenCourseTitle.setText(enrollList.get(position).geteCode());
		txtenCourseName.setText(enrollList.get(position).geteDate());
		txtDeptCode.setText(enrollList.get(position).geteRegNo());
		txtResult.setText(enrollList.get(position).geteResult());
		
		
		
		final String enCode = enrollList.get(position).geteCode();
		final String enRegNo = enrollList.get(position).geteRegNo();
		
		final String enDate = enrollList.get(position).geteDate();
		
		
		enrollEditImg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				enrollEditRes(enCode,enRegNo,txtResult.getText().toString(),enDate);
			}
		});
		
		return rowVirw;
	}
	
	private void enrollEditRes(String enCode,String enRegNo, String enRes, String eDates){
		
		EnrollCourseGateWay eGateWay = new EnrollCourseGateWay(this.context);
		
		String res = eGateWay.editResult(enCode, enRegNo, enRes,eDates);
		
		//Toast.makeText(this.context, enRegNo+"      "+enRes , 2000).show();
		
		Intent intent = new Intent(this.context,EnrollCourses.class);
		intent.putExtra("studentRegNo", enRegNo);
		intent.putExtra("studentName", "");
		this.context.startActivity(intent);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.enrollList.size();
	}

	@Override
	public EnrollCourse getItem(int position) {
		// TODO Auto-generated method stub
		return this.enrollList.get(position);
	}

	@Override
	public int getPosition(EnrollCourse item) {
		// TODO Auto-generated method stub
		return super.getPosition(item);
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

}
