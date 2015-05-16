package com.technovalley21.barcodeqr;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;


public class MainActivity extends Activity {
	Button btnScan, btnCreate;
	TextView textViewFormat, textViewData;
	ImageView scannedBitmap;
	EditText etString;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnScan = (Button) findViewById(R.id.btnScan);
        btnCreate = (Button) findViewById(R.id.btnCreate);
        
        textViewFormat = (TextView) findViewById(R.id.textViewFormat);
        textViewData = (TextView) findViewById(R.id.textViewData);
        
        etString = (EditText) findViewById(R.id.etString);
        
        scannedBitmap = (ImageView) findViewById(R.id.scannedBitmap);
        
        btnScan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				try{
					Intent intent = new Intent("com.google.zxing.client.android.SCAN");
					intent.putExtra("SCAN_MODE", "QR_CODE_MODE,PRODUCT_MODE");
					startActivityForResult(intent, 0);
				} catch(Exception e){
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "ERROR:" + e, Toast.LENGTH_LONG).show();
				}
			}
		});
        
        btnCreate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				createBarcodeBitmap();
			}
		});
        
    }
    
    public void createBarcodeBitmap(){
    	String barcode_content = etString.getText().toString();
    	QRCodeWriter qrWriter = new QRCodeWriter();
		
		try {
			BitMatrix bitMatrix = qrWriter.encode(barcode_content, BarcodeFormat.QR_CODE, 150, 150);
			Bitmap bitmap = toBitmap(bitMatrix);
			scannedBitmap.setImageBitmap(bitmap);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	super.onActivityResult(requestCode, resultCode, intent);
    	if (requestCode == 0) {

	    	if (resultCode == RESULT_OK) {
	    		textViewFormat.setText(intent.getStringExtra("SCAN_RESULT_FORMAT"));
	    		textViewData.setText(intent.getStringExtra("SCAN_RESULT"));
	    		
	    		Uri imageURI = intent.getData();
	    		Bitmap bitmap;
	    		try{
	    			bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageURI);
	    			scannedBitmap.setImageBitmap(bitmap);
	    		} catch(Exception e){
	    			e.printStackTrace();
	    		}
	    		
	    		//Toast.makeText(getApplicationContext(), intent.getStringExtra("SCAN_RESULT_FORMAT") + ":" + intent.getStringExtra("SCAN_RESULT"), 5000).show();
		    } else if (resultCode == RESULT_CANCELED) {
		    	textViewFormat.setText("");
		    	textViewData.setText("Cancelled By user");
	    	}
	    	
    	}
    }
    
    
    /**
     * This method used for converting BitMatrix to BitMap
     * @param matrix
     * @return bitmap
     */
    public static Bitmap toBitmap(BitMatrix bitMatrix){
        int height = bitMatrix.getHeight();
        int width = bitMatrix.getWidth();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                bmp.setPixel(x, y, bitMatrix.get(x,y) ? Color.BLACK : Color.WHITE);
            }
        }
        return bmp;
    }

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
