package gateways;

import java.util.ArrayList;
import java.util.List;

import utilities.EnrollCourse;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.findingsoft.studentregistration.DBOpenHelper;

public class EnrollCourseGateWay {
	private SQLiteDatabase sqLiteDB;
	private DBOpenHelper dbOpenHelper;
	private Context context;

	String[] allColumns = { DBOpenHelper.EN_CODE, DBOpenHelper.EN_REGNO,
			DBOpenHelper.EN_COURS_DATE, DBOpenHelper.EN_COURS_RESULTS };

	String[] columnDept = { DBOpenHelper.STUDENT_DEPT };
	String[] columnCourseCode = { DBOpenHelper.COURS_CODE };

	public EnrollCourseGateWay(Context context) {
		dbOpenHelper = new DBOpenHelper(context);
		this.context = context;
	}

	public void open() {
		try {
			sqLiteDB = dbOpenHelper.getWritableDatabase();
		} catch (SQLException s) {
			new Exception("Error with DB Open");
		}
	}

	public void close() {
		sqLiteDB.close();
	}

	public String enrollCours(EnrollCourse enrollCours) {
		ContentValues contentValues = new ContentValues();

		open();

		contentValues.put(DBOpenHelper.EN_CODE, enrollCours.geteCode());
		contentValues.put(DBOpenHelper.EN_REGNO, enrollCours.geteRegNo());
		contentValues.put(DBOpenHelper.EN_COURS_DATE, enrollCours.geteDate());
		contentValues.put(DBOpenHelper.EN_COURS_RESULTS,
				enrollCours.geteResult());

		open();
		long res = sqLiteDB.insert(DBOpenHelper.TABLE_EN_COURS, null,
				contentValues);
		close();

		if (res > 0) {
			return "Student Added with: " + enrollCours.geteCode();
		} else {
			return "Duplicate Entry with: " + enrollCours.geteCode();
		}
	}

	public String deleteEnroll(String courseCode, String regNo) {
		open();
		long res = sqLiteDB
				.delete(DBOpenHelper.TABLE_EN_COURS, DBOpenHelper.EN_CODE + "="
						+ "'" + courseCode + "' and " + DBOpenHelper.EN_REGNO
						+ "=" + "'" + regNo + "'", null);
		close();

		if (res > 0) {
			return "Enroll Course deleted with: " + regNo;
		} else {
			return "Error for deleting with: " + regNo;
		}
	}

	public String editResult(String courseCode, String regNo, String results,String enrollDate) {
		open();
		String msg;
		try {
			ContentValues contentValues = new ContentValues();
			contentValues.put(DBOpenHelper.EN_CODE, courseCode);
			contentValues.put(DBOpenHelper.EN_REGNO, regNo);
			contentValues.put(DBOpenHelper.EN_COURS_DATE, enrollDate);
			contentValues.put(DBOpenHelper.EN_COURS_RESULTS, results);

			sqLiteDB.update(DBOpenHelper.TABLE_EN_COURS, contentValues, " "
					+ DBOpenHelper.EN_CODE + "=" + "'" + courseCode + "' and "
					+ DBOpenHelper.EN_REGNO + "=" + "'" + regNo + "'", null);
			msg = "Result Successfully Updated with: " + courseCode + " and "
					+ regNo;
		} catch (Exception e) {
			msg = "Error for Result Updating with " + courseCode + " and "
					+ regNo;
		}

		close();

		return msg;
	}
	
	public ArrayList<EnrollCourse> getAll() { // get all courses those are already taken by the student
		open();
		ArrayList<EnrollCourse> anEnrollList = new ArrayList<EnrollCourse>();

		try {
			Cursor cursor = sqLiteDB.query(DBOpenHelper.TABLE_EN_COURS,
					allColumns, null, null, null, null, DBOpenHelper.EN_CODE);
			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				EnrollCourse enrollCourse = new EnrollCourse();
				enrollCourse.seteCode(cursor.getString(0));
				enrollCourse.seteRegNo(cursor.getString(1));
				enrollCourse.seteDate(cursor.getString(2));
				enrollCourse.seteResult(cursor.getString(3));
				anEnrollList.add(enrollCourse);
				cursor.moveToNext();
			}
			cursor.close();
		} catch (Exception e) {
			EnrollCourse enrollCourse = new EnrollCourse();
			enrollCourse.seteCode("EMPTY");
			enrollCourse.seteRegNo("EMPTY");
			enrollCourse.seteDate("EMPTY");
			enrollCourse.seteResult("EMPTY");
			anEnrollList.add(enrollCourse);
		}
		close();

		return anEnrollList;
	}

	public List<String> getAllCourse(String studentRegNo) { //get all course of the student's department
		List<String> courseList = new ArrayList<String>();
		String strDept = "";
		try {
			open();

			Cursor cursor = sqLiteDB.query(DBOpenHelper.TABLE_STUDENT,
					columnDept, DBOpenHelper.STUDENT_REGNO + "=" + "'"
							+ studentRegNo + "'", null, null, null, null);
			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				strDept = cursor.getString(0); // get department code from student
				cursor.moveToNext();
			}
			cursor.close();

			if (!(strDept.equals(""))) {
				cursor = sqLiteDB.query(DBOpenHelper.TABLE_COURS,
						columnCourseCode, DBOpenHelper.COURS_DEPT + "=" + "'"
								+ strDept + "'", null, null, null,
						DBOpenHelper.COURS_CODE);
				cursor.moveToFirst();
				while (!cursor.isAfterLast()) {
					String strCourCode = cursor.getString(0);
					courseList.add(strCourCode);
					cursor.moveToNext();
				}
				cursor.close();
			} else {
				String strCourCode = "None";
				courseList.add(strCourCode);
			}

			close();
		} catch (Exception e) {
			String strCourCode = "None";
			courseList.add(strCourCode);

		}
		return courseList;
	}

}
