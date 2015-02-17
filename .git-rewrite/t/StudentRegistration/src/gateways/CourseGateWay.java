package gateways;

import java.util.ArrayList;

import utilities.Courses;
import utilities.Departments;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TableLayout;

import com.findingsoft.studentregistration.DBOpenHelper;

public class CourseGateWay {
	private SQLiteDatabase sqLiteDB;
	private DBOpenHelper dbOpenHelper;
	private Context context;

	String[] allColumns = { DBOpenHelper.COURS_CODE, DBOpenHelper.COURS_NAME,
			DBOpenHelper.COURS_DEPT };

	public CourseGateWay(Context context) {
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

	public String saveCourse(Courses aCore) {
		ContentValues contentValues = new ContentValues();

		open();

		contentValues.put(DBOpenHelper.COURS_CODE, aCore.getcCode());
		contentValues.put(DBOpenHelper.COURS_NAME, aCore.getcName());
		contentValues.put(DBOpenHelper.COURS_DEPT, aCore.getdCode());

		open();
		long res = sqLiteDB.insert(DBOpenHelper.TABLE_COURS, null,
				contentValues);
		close();

		if (res > 0) {
			return "Course Added with: " + aCore.getcCode();
		} else {
			return "Duplicate Entry with: " + aCore.getcName();
		}

	}

	public String deleteCourse(String courseCode) {
		open();

		long res = sqLiteDB.delete(DBOpenHelper.TABLE_COURS,
				DBOpenHelper.COURS_CODE + "=" + "'" + courseCode + "'", null);
		close();
		if (res > 0) {
			return "Course deleted with: " + courseCode;
		} else {
			return "Error for deleting with: " + courseCode;
		}

	}

	public String updateCourse(Courses aCore) {
		open();
		String msg = "Sorry!! Error for Updating";
		try {
			ContentValues cv = new ContentValues();
			cv.put(DBOpenHelper.COURS_CODE, aCore.getcCode());
			cv.put(DBOpenHelper.COURS_NAME, aCore.getcName());
			cv.put(DBOpenHelper.COURS_DEPT, aCore.getdCode());

			sqLiteDB.update(DBOpenHelper.TABLE_COURS, cv, " "
					+ DBOpenHelper.COURS_CODE + "=" + "'" + aCore.getcCode()
					+ "'", null);

			msg = "Successfully Updated with: " + aCore.getcCode();
		} catch (Exception e) {
			msg = "Sorry!! Error for Updating with: " + aCore.getcCode();
		}
		close();
		return msg;
	}

	public ArrayList<Courses> getAll(String deptCode) {
		open();
		ArrayList<Courses> aCoreList = new ArrayList<Courses>();

		try {
			Cursor cursor = sqLiteDB.query(DBOpenHelper.TABLE_COURS,
					allColumns, DBOpenHelper.COURS_DEPT + "=" + "'" + deptCode
							+ "'", null, null, null, DBOpenHelper.COURS_CODE);

			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				Courses aCore = new Courses();
				aCore.setcCode(cursor.getString(0));
				aCore.setcName(cursor.getString(1));
				aCoreList.add(aCore);
				cursor.moveToNext();
			}
			cursor.close();
		} catch (Exception e) {

		}
		close();

		return aCoreList;
	}

}
