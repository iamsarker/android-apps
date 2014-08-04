package com.errorpoint.gestureoverlaydraw;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

public class LauncherActivity extends Activity implements OnGesturePerformedListener {
	
	GestureLibrary gLibrary;
	GestureOverlayView gView;
	EditText gestureText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		gestureText = (EditText) findViewById(R.id.gestureText);
		gLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);
		
		if(gLibrary!=null){
			if(!gLibrary.load()){
				Log.e("GestureSample", "Gesture library was not loaded…");
       	     	finish();
			} else{
				gView = (GestureOverlayView) findViewById(R.id.gestures);
				gView.addOnGesturePerformedListener(this);
			}
		}
		
	}

	@Override
	public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
		// TODO Auto-generated method stub
		ArrayList<Prediction> predictions = gLibrary.recognize(gesture);
		if(predictions.size()>0){
			Prediction prediction = predictions.get(0);
			
			
			
			if(prediction.score>1.0){
				Toast.makeText(this, prediction.name,Toast.LENGTH_SHORT).show();
				gestureText.setText(gestureText.getText() + "" + prediction.name);
				
			} else{
				Toast.makeText(this, "Unavailable yet",Toast.LENGTH_SHORT).show();
			}
		}
	}


}
