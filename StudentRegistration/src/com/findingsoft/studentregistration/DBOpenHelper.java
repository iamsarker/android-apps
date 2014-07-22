package com.findingsoft.studentregistration;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

	public static final String TABLE_DEPT = "departments";
	// public static final String DEPT_ID = "_dID"; //Pk
	public static final String DEPT_CODE = "dCode";
	public static final String DEPT_NAME = "dName";

	public static final String TABLE_COURS = "courses";
	// public static final String COURS_ID = "_cID"; //Pk
	public static final String COURS_CODE = "cCode";
	public static final String COURS_NAME = "cName";
	public static final String COURS_DEPT = "cDept";
	// public static final String DEPT_ID = "_dID"; //Fk

	public static final String TABLE_STUDENT = "students";
	// public static final String STUDENT_ID = "_sID"; //Pk
	public static final String STUDENT_REGNO = "sReg";
	public static final String STUDENT_NAME = "sName";
	public static final String STUDENT_EMAIL = "sEmail";
	public static final String STUDENT_DEPT = "sDept";
	// public static final String DEPT_ID = "_dID"; //Fk

	public static final String TABLE_EN_COURS = "enroll_courses";
	// public static final String EN_COURS_ID = "_eID"; //Pk
	public static final String EN_CODE = "cCode"; // Fk
	public static final String EN_REGNO = "sReg"; // Fk
	public static final String EN_COURS_DATE = "eDate";
	public static final String EN_COURS_RESULTS = "eResult";

	private static final String DATABASE_NAME = "RegistrationData.db";
	private static final int DATABASE_VERSION = 1;

	private static final String CREATE_DEPT = "create table " + TABLE_DEPT
			+ "(" + DEPT_CODE + " text unique, " + DEPT_NAME + " text unique);";

	private static final String CREATE_COURS = "create table " + TABLE_COURS
			+ "(" + COURS_CODE + " text unique, " + COURS_NAME
			+ " text unique, " + COURS_DEPT + " text);";

	private static final String CREATE_STUDENT = "create table "
			+ TABLE_STUDENT + "(" + STUDENT_REGNO + " text unique, "
			+ STUDENT_NAME + " text, " + STUDENT_EMAIL + " text unique, "
			+ STUDENT_DEPT + " text);";

	private static final String CREATE_ENROLL_COURS = "create table "
			+ TABLE_EN_COURS + "(" + EN_CODE + " text, " + EN_REGNO + " text, "
			+ EN_COURS_DATE + " text, " + EN_COURS_RESULTS + " text);";

	public DBOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		//TODO Auto-generated constructor stub
		//String DB_NAME = "Registrations.db";
		//context.deleteDatabase(DB_NAME);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_DEPT);
		db.execSQL(CREATE_COURS);
		db.execSQL(CREATE_STUDENT);
		db.execSQL(CREATE_ENROLL_COURS);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		 db.execSQL("DROP TABLE IF EXISTS "+CREATE_ENROLL_COURS);
		 db.execSQL("DROP TABLE IF EXISTS "+CREATE_STUDENT);
		 db.execSQL("DROP TABLE IF EXISTS "+CREATE_COURS);
		 db.execSQL("DROP TABLE IF EXISTS "+CREATE_DEPT);
		onCreate(db);
	}

}
