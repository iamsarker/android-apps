package gateways;

import java.util.ArrayList;

import utilities.Courses;
import utilities.Students;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.findingsoft.studentregistration.DBOpenHelper;

public class StudentGateWay {
	private SQLiteDatabase sqLiteDB;
	private DBOpenHelper dbOpenHelper;
	private Context context;

	String[] allColumns = { DBOpenHelper.STUDENT_REGNO,
			DBOpenHelper.STUDENT_NAME, DBOpenHelper.STUDENT_EMAIL,
			DBOpenHelper.STUDENT_DEPT };

	public StudentGateWay(Context context) {
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

	public String saveStudent(Students aStudent) {
		ContentValues contentValues = new ContentValues();

		open();

		contentValues.put(DBOpenHelper.STUDENT_REGNO, aStudent.getRegNo());
		contentValues.put(DBOpenHelper.STUDENT_NAME, aStudent.getStudentName());
		contentValues.put(DBOpenHelper.STUDENT_EMAIL,
				aStudent.getStudentEmail());
		contentValues.put(DBOpenHelper.STUDENT_DEPT, aStudent.getStudentDept());

		open();
		long res = sqLiteDB.insert(DBOpenHelper.TABLE_STUDENT, null,
				contentValues);
		close();

		if (res > 0) {
			return "Student Added with: " + aStudent.getRegNo();
		} else {
			return "Duplicate Entry with: " + aStudent.getRegNo();
		}

	}

	public String deleteStudent(String regNo) {
		open();

		long res = sqLiteDB.delete(DBOpenHelper.TABLE_STUDENT,
				DBOpenHelper.STUDENT_REGNO + "=" + "'" + regNo + "'", null);
		close();
		if (res > 0) {
			return "Student deleted with: " + regNo;
		} else {
			return "Error for deleting with: " + regNo;
		}
	}

	public String updateStudent(Students aStudent) {
		open();
		String msg = "Sorry!! Error for Updating";

		try {
			ContentValues contentValues = new ContentValues();
			contentValues.put(DBOpenHelper.STUDENT_REGNO, aStudent.getRegNo());
			contentValues.put(DBOpenHelper.STUDENT_NAME,
					aStudent.getStudentName());
			contentValues.put(DBOpenHelper.STUDENT_EMAIL,
					aStudent.getStudentEmail());
			contentValues.put(DBOpenHelper.STUDENT_DEPT,
					aStudent.getStudentEmail());

			sqLiteDB.update(
					DBOpenHelper.TABLE_STUDENT,
					contentValues,
					" " + DBOpenHelper.STUDENT_REGNO + "=" + "'"
							+ aStudent.getRegNo() + "'", null);

			msg = "Successfully Updated with: " + aStudent.getRegNo();
		} catch (Exception e) {
			msg = "Sorry!! Error for Updating with: " + aStudent.getRegNo();
		}
		close();
		return msg;
	}

	public ArrayList<Students> getAll(String deptCode) {
		open();
		ArrayList<Students> aStudentList = new ArrayList<Students>();

		try {
			Cursor cursor = sqLiteDB.query(DBOpenHelper.TABLE_STUDENT,
					allColumns, DBOpenHelper.STUDENT_DEPT + "=" + "'"
							+ deptCode + "'", null, null, null,
					DBOpenHelper.STUDENT_REGNO);

			// Cursor cursor = sqLiteDB.query(DBOpenHelper.TABLE_STUDENT,
			// allColumns, null, null, null, null, DBOpenHelper.STUDENT_REGNO);

			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				Students aStudent = new Students();
				aStudent.setRegNo(cursor.getString(0));
				aStudent.setStudentName(cursor.getString(1));
				aStudent.setStudentEmail(cursor.getString(2));
				aStudent.setStudentDept(cursor.getString(3));
				aStudentList.add(aStudent);
				cursor.moveToNext();
			}
			cursor.close();
		} catch (Exception e) {
		}
		close();

		return aStudentList;
	}

}
