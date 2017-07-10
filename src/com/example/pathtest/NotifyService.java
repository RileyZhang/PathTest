package com.example.pathtest;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import util.GlobalConstant;

public class NotifyService extends Service {

	private Context mContext;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		Log.i("riley", "NotifyService onCreate()");
		super.onCreate();
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		mContext = getApplicationContext();
		Log.i("riley", "NotifyService onStart()");
		int operateType = intent.getIntExtra(GlobalConstant.OPERATE_TYPE, -1);
		doOperate(operateType);
		super.onStart(intent, startId);
	}
	
	private void doOperate(int operateType) {
		switch(operateType) {
		case 0:
			NotificationManager mNotificationManager = (NotificationManager) mContext.getSystemService(mContext.NOTIFICATION_SERVICE);
			mNotificationManager.cancel(GlobalConstant.NOTIFICATION_ID_1);
			break;
		case 1:
			
			break;
		default:
			break;
		}
	}
}
