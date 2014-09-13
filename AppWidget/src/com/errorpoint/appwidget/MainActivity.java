package com.errorpoint.appwidget;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.drm.DrmStore.Action;
import android.view.Menu;
import android.widget.RemoteViews;
import android.widget.Toast;

public class MainActivity extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds){
		
		for(int i=0 ; i<appWidgetIds.length; i++){
			int currentWidgetsId = appWidgetIds[i];
			String url = "https://github.com/iamsarker/";
			
			Intent intent = new Intent(Intent.ACTION_VIEW);
			
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.setData(Uri.parse(url));
			PendingIntent pending = PendingIntent.getActivity(context, 0, intent, 0);
			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.activity_main);
			views.setOnClickPendingIntent(R.id.button1, pending);
			appWidgetManager.updateAppWidget(currentWidgetsId, views);
			Toast.makeText(context, "widget added", Toast.LENGTH_SHORT).show();
		}
		
	}
}
