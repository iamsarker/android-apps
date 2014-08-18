package com.errorpoint.startupapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class RebootReceiving extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "Reboot completed! Starting your app!!!.", Toast.LENGTH_LONG).show();
		Intent i = new Intent(context, StartupActivity.class);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(i);
		
		//all works is done in manifest file
	}

}
