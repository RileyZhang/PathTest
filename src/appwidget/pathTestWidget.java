package appwidget;

import com.example.pathtest.MainActivity;
import com.example.pathtest.R;
import com.example.pathtest.SecondActivity;

import android.app.Application;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import util.GlobalConstant;

public class pathTestWidget extends AppWidgetProvider {

	private Context mContext;
	private AppWidgetManager mAppWidgetManager;
	private ComponentName mComponentName;
	private RemoteViews mRemoteViews;
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		Log.i(GlobalConstant.TAG, "onUpdate() ");
		mContext = context;
		mAppWidgetManager = appWidgetManager;
		mComponentName = new ComponentName(mContext, pathTestWidget.class);
		mRemoteViews = getRemoteViews();
		mAppWidgetManager.updateAppWidget(mComponentName, mRemoteViews);
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}
	
	@Override
	public void onEnabled(Context context) {
		// TODO Auto-generated method stub
		Log.i(GlobalConstant.TAG, "onEnabled() ");
		super.onEnabled(context);
	}
	
	@Override
	public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId,
			Bundle newOptions) {
		// TODO Auto-generated method stub
		Log.i(GlobalConstant.TAG, "onAppWidgetOptionsChanged() newOptions.toString() = " + newOptions.toString());
		super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Log.i(GlobalConstant.TAG, "onReceive() action = " + intent.getAction());
		super.onReceive(context, intent);
	}
	
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		Log.i(GlobalConstant.TAG, "onDeleted() ");
		super.onDeleted(context, appWidgetIds);
	}
	
	private RemoteViews getRemoteViews() {
		RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.path_test_appwidget);
		remoteViews.setTextViewText(R.id.tv_widget_name, mContext.getString(R.string.widget_text2));
		
		
		Intent intent = new Intent();
//		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		intent.putExtra("riley", "test");
//		intent.addCategory(Intent.CATEGORY_LAUNCHER);
//		intent.setAction(Intent.ACTION_MAIN);
		ComponentName component = new ComponentName("com.example.pathtest", "com.example.pathtest.MainActivity");
		intent.setComponent(component);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		remoteViews.setOnClickPendingIntent(R.id.button_widget, pendingIntent);
		return remoteViews;
	}
}
