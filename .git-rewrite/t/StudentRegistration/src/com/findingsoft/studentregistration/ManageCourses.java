package com.findingsoft.studentregistration;

import gateways.CourseGateWay;
import java.util.ArrayList;
import utilities.Courses;
import adapters.CourseAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ManageCourses extends Activity {

	public String departmentCode = "None", departmentName = "None";

	TextView departCode, departName;
	EditText txtCourseTitle, txtCourseName;
	Button btnAddC, btnUpdateC;

	CourseGateWay gatewayCourse = new CourseGateWay(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.manage_courses);

		initialControls();
		eventRegister();
		proccessListView();
	}

	private void initialControls() {
		departCode = (TextView) findViewById(R.id.viewCodeD);
		departName = (TextView) findViewById(R.id.viewNameD);

		txtCourseTitle = (EditText) findViewById(R.id.txtCodeC);
		txtCourseName = (EditText) findViewById(R.id.txtNameC);

		btnAddC = (Button) findViewById(R.id.addCourse);
		btnUpdateC = (Button) findViewById(R.id.updateCourse);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			departmentCode = extras.getString("depCode");
			departmentName = extras.getString("depName");
		}
		departCode.setText(departmentCode);
		departName.setText(departmentName);

	}

	private void eventRegister() {
		btnAddC.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				addCourse();
				proccessListView();
			}
		});
		btnUpdateC.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				updateCourse();
				proccessListView();
			}
		});
	}

	private void addCourse() {
		if (!txtCourseTitle.getText().equals("")
				&& !txtCourseName.getText().equals("")) {
			Courses aCore = new Courses();
			aCore.setcCode(txtCourseTitle.getText().toString());
			aCore.setcName(txtCourseName.getText().toString());
			aCore.setdCode(departCode.getText().toString());

			String res = gatewayCourse.saveCourse(aCore);
			showToast(res);
			proccessListView();
		}

	}

	private void updateCourse() {
		if (!txtCourseTitle.getText().equals("")
				&& !txtCourseName.getText().equals("")) {
			Courses aCore = new Courses();
			aCore.setcCode(txtCourseTitle.getText().toString());
			aCore.setcName(txtCourseName.getText().toString());
			aCore.setdCode(departCode.getText().toString());

			String res = gatewayCourse.updateCourse(aCore);
			showToast(res);
			proccessListView();
		}

	}

	public void proccessListView() {
		ArrayList<Courses> aCore = new ArrayList<Courses>();
		aCore = gatewayCourse.getAll(departmentCode);
		loadListView(aCore);
	}

	private void loadListView(ArrayList<Courses> aCore) {
		ListView listView = (ListView) findViewById(R.id.courseListAll);
		listView.setAdapter(new CourseAdapter(this, aCore));
	}

	private void showToast(String msg) {
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
	}

}
