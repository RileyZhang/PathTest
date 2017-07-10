package appwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import util.GlobalConstant;

public class pathTestWidget extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		Log.i(GlobalConstant.TAG, "onUpdate() ");
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}
	
	@Override
	public void onEnabled(Context context) {
		// TODO Auto-generated method stub
		Log.i(GlobalConstant.TAG, "onUpdate() ");
		super.onEnabled(context);
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Log.i(GlobalConstant.TAG, "onUpdate() ");
		super.onReceive(context, intent);
	}
	
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		Log.i(GlobalConstant.TAG, "onUpdate() ");
		super.onDeleted(context, appWidgetIds);
	}
}
