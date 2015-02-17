package gateways;

import java.util.ArrayList;

import com.findingsoft.studentregistration.DBOpenHelper;

import utilities.Departments;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DepartmentGateWay {
	private SQLiteDatabase sqLiteDB;
	private DBOpenHelper dbOpenHelper;

	String[] allColumns = { DBOpenHelper.DEPT_CODE, DBOpenHelper.DEPT_NAME };

	public DepartmentGateWay(Context context) {
		dbOpenHelper = new DBOpenHelper(context);
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

	public String save(Departments aDept) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(DBOpenHelper.DEPT_CODE, aDept.getDeptCode());
		contentValues.put(DBOpenHelper.DEPT_NAME, aDept.getDeptName());

		open();
		long res = sqLiteDB
				.insert(DBOpenHelper.TABLE_DEPT, null, contentValues);
		close();

		if (res > 0) {
			return "Department Created with: " + aDept.getDeptCode();
		} else {
			return "Duplicate Entry with: " + aDept.getDeptName();
		}
	}

	public String deptUpdate(Departments aDept) {
		open();
		String msg = "Sorry!! Error for Updating";
		try {
			ContentValues cv = new ContentValues();
			cv.put(DBOpenHelper.DEPT_CODE, aDept.getDeptCode());
			cv.put(DBOpenHelper.DEPT_NAME, aDept.getDeptName());

			sqLiteDB.update(DBOpenHelper.TABLE_DEPT, cv, " "
					+ DBOpenHelper.DEPT_CODE + "=" + "'" + aDept.getDeptCode()
					+ "'", null);

			msg = "Successfully Updated with: " + aDept.getDeptCode();
		} catch (Exception e) {
			msg = "Sorry!! Error for Updating with: " + aDept.getDeptCode();
		}
		close();
		return msg;
	}

	public String deptDelete(String deptCode) {
		open();

		long res = sqLiteDB.delete(DBOpenHelper.TABLE_DEPT,
				DBOpenHelper.DEPT_CODE + "=" + "'" + deptCode + "'", null);
		close();
		if (res > 0) {
			return "Department deleted with: " + deptCode;
		} else {
			return "Error for deleting with: " + deptCode;
		}

	}

	public ArrayList<Departments> getAll() {
		ArrayList<Departments> aDeptList = new ArrayList<Departments>();

		try {
			open();
			Cursor cursor = sqLiteDB.query(DBOpenHelper.TABLE_DEPT, allColumns,
					null, null, null, null, DBOpenHelper.DEPT_CODE);
			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				Departments aDept = new Departments();
				aDept.setDeptCode(cursor.getString(0));
				aDept.setDeptName(cursor.getString(1));
				aDeptList.add(aDept);
				cursor.moveToNext();
			}
			cursor.close();
			close();

		} catch (Exception e) {

		}

		return aDeptList;
	}

}
