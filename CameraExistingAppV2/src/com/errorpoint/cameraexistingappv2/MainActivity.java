package com.errorpoint.cameraexistingappv2;

import java.io.File;
import java.io.FileOutputStream;


import com.errorpoint.cameraexistingappv2.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	ImageView imgLaunchCamera;
	Button btnSaveImage;
	
	Intent IMG_DATA;
	Bitmap finalBitMap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnSaveImage = (Button)findViewById(R.id.btnSaveImage);
		imgLaunchCamera = (ImageView)findViewById(R.id.imgLaunchCamera);
		imgLaunchCamera.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				openExistingCamera();
			}
		});
		
		btnSaveImage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				saveImageToSDCard();
			}
		});
	}
	
	private void saveImageToSDCard(){
		final String strPath = android.os.Environment.getExternalStorageDirectory().toString();
		String DIR_NAME = "CameraAppV2";
		
		File makeDir = new File( strPath + "/" + DIR_NAME);
		
		if( !makeDir.exists() ){
			makeDir.mkdirs();
		}
		
		String fileName = "IMG_" + System.currentTimeMillis() + ".jpg";
		File imageFile = new File(makeDir, fileName);
		
		try{
			FileOutputStream outStream = new FileOutputStream(imageFile);
			finalBitMap.compress(Bitmap.CompressFormat.JPEG, 98, outStream);
			outStream.flush();
			outStream.close();
			
			Toast.makeText(MainActivity.this, "Your Image is saved into '" + DIR_NAME + "' directory", Toast.LENGTH_LONG).show();
			
		} catch(Exception e){
			e.printStackTrace();
			Toast.makeText(MainActivity.this, e.getMessage() + "", Toast.LENGTH_LONG).show();
		}
		
	}
	
	private void openExistingCamera(){
		Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, 0);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		IMG_DATA = data;
		super.onActivityResult(requestCode, resultCode, data);
		finalBitMap = (Bitmap) data.getExtras().get("data");
		imgLaunchCamera.setImageBitmap(finalBitMap);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
