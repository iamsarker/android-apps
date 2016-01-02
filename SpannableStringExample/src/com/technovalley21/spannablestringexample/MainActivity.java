package com.technovalley21.spannablestringexample;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		SpannableString spannableString = new SpannableString("Please visit my Github.\n\n For more tutorials.\nThanks.\nTechnovalley21.com. hojborolo");
		
		// make the text twice as large
		spannableString.setSpan(new RelativeSizeSpan(2f) , 0, 5, 0); // please
		
		// make text bold
		spannableString.setSpan(new StyleSpan(Typeface.BOLD), 15, 21, 0);
		
		// make text italic
		spannableString.setSpan(new StyleSpan(Typeface.ITALIC), 15, 21, 0);
		
		// make text Underline
		spannableString.setSpan(new UnderlineSpan(), 15, 21, 0);
		
		// text color change
		spannableString.setSpan(new ForegroundColorSpan(Color.GREEN), 15, 21, 0);
		
		// highlight text
		spannableString.setSpan(new BackgroundColorSpan(Color.CYAN), 46, 51, 0);
		
		// superscript
		spannableString.setSpan(new SuperscriptSpan(), 53, 64, 0);
		
		// make the superscript text smaller
		//spannableString.setSpan(new RelativeSizeSpan(0.5f), 72, 83, 0);
		
		// subscript
		spannableString.setSpan(new SubscriptSpan(), 65, 67, 0);
		
		// make the subscript text smaller
		spannableString.setSpan(new RelativeSizeSpan(0.5f), 69, 75, 0);
		
		// set link
		spannableString.setSpan(new URLSpan("https://github.com/iamsarker/android-apps/"), 15, 21, 0);
		
		ClickableSpan clickableSpan = new ClickableSpan() {
			
			@Override
			public void onClick(View widget) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
			}
		};
		
		
		spannableString.setSpan(clickableSpan, 46, 51, 0);
		
		// Give the styled string to a TextView
		TextView textView = new TextView(this);
		
		// this step is mandated for the url and clickable styles.
		textView.setMovementMethod(LinkMovementMethod.getInstance());
		
		// make it neat
		textView.setGravity(Gravity.CENTER);
		textView.setBackgroundColor(Color.WHITE);
		
		textView.setText(spannableString);
		
		setContentView(textView);
		
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
