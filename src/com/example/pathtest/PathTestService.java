package com.example.pathtest;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import util.GlobalConstant;

public class PathTestService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		Log.i(GlobalConstant.TAG, "PathTestService onCreate() ######");
		super.onCreate();
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		Log.i(GlobalConstant.TAG, "PathTestService onStart() ######");
		super.onStart(intent, startId);
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.i(GlobalConstant.TAG, "PathTestService onStartCommand() ######");
		Intent intent2 = new Intent();
		ComponentName component = new ComponentName("com.riley.threadpoolapplication", "com.riley.threadpoolapplication.MainActivity");
		intent2.setComponent(component);
		intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent2.addCategory(Intent.CATEGORY_LAUNCHER);
		intent2.setAction(Intent.ACTION_MAIN);
		startActivity(intent2);
//		Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
//		intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		startActivity(intent2);
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.i(GlobalConstant.TAG, "PathTestService onDestroy() ######");
		super.onDestroy();
	}
}
