package com.errorpoint.externaldatabase.storage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

public class AppDataBaseManager extends SQLiteOpenHelper{
	
	public static final int VERSION = 1;
	public static final CursorFactory FACTORY = null;
	private static String DB_NAME = "external_database.sqlite";
	private String DB_PATH = "";
	private SQLiteDatabase db;
	private Context context;
	
	public AppDataBaseManager(Context context) {
		super(context, DB_NAME, FACTORY, VERSION);
		this.context = context;
		DB_PATH = getAppDataDir();
		
		if( !checkDataBase() ){
			try{
				copyDataBase();
			} catch(IOException e){
				e.printStackTrace();
			}
		}
		
	}
	
	private void copyDataBase() throws IOException {
		// Open your local db as the input stream
		InputStream myInput = context.getAssets().open(DB_NAME);
		
		// Path to the just created empty db
		String outFileName = DB_PATH + "/" + DB_NAME;
		
		// Open the empty db as the output stream
		new File(outFileName).createNewFile();
		OutputStream myOutput = new FileOutputStream(outFileName);
		
		// transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}
		
		// Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();
	}
	
	
	public boolean initializeDatabase() {
		if (DB_PATH == null)
			return false;

		try {
			db = SQLiteDatabase.openDatabase(DB_PATH + "/" + DB_NAME, null,
					SQLiteDatabase.OPEN_READWRITE
							| SQLiteDatabase.NO_LOCALIZED_COLLATORS);
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

		return true;
	}
	
	private boolean checkDataBase() {
		String myPath = DB_PATH + "/" + DB_NAME;
		if (new File(myPath).exists())
			return true;
		else
			return false;
	}
	
	public static boolean isExtSDCardPresent()
	{
		return Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED) && !Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED_READ_ONLY);
	}
	
	private String getAppDataDir()
	{
		if(isExtSDCardPresent())
		{
			String filePath =  Environment.getExternalStorageDirectory().getAbsolutePath() + "/EDB_EP";
			File file = new File(filePath);
			if(!file.exists())
				file.mkdirs();
			
			return filePath;
		}
		else 
			return null;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public String getAppInfo(){
		String sql = "Select * from " + DB_TABLE.APP_INFO_TABLE;
		String app_info = "";
		Cursor c = db.rawQuery(sql, null);
		if(c.moveToFirst()){
			app_info = "App Name: " + c.getString(c.getColumnIndex(DB_TABLE.APP_INFO_APP_NAME)) + "; Developer: " + c.getString(c.getColumnIndex(DB_TABLE.APP_INFO_APP_DEV));
		}
		return app_info;
	}
	
}
