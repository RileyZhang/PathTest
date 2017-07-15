package com.example.pathtest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import util.GlobalConstant;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.i(GlobalConstant.TAG, "onCreate()##########");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		TextView textView = (TextView) findViewById(R.id.second_text);
		textView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				Intent intent = new Intent();
				ComponentName component = new ComponentName("com.riley.threadpoolapplication", "com.riley.threadpoolapplication.MainActivity");
				intent.setComponent(component);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//				intent.addCategory(Intent.CATEGORY_LAUNCHER);
//				intent.setAction(Intent.ACTION_MAIN);
				startActivity(intent);
			}
		});
		Button firstButton = (Button) findViewById(R.id.first_button);
		firstButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Intent intent = new Intent();
//				ComponentName component = new ComponentName("com.riley.threadpoolapplication", "com.riley.threadpoolapplication.MainActivity");
//				intent.setComponent(component);
//				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//				startActivity(intent);
				
				Intent serviceIntent = new Intent(getApplicationContext(), PathTestService.class);
				startService(serviceIntent);
			}
		});
		Button secondButton = (Button) findViewById(R.id.second_button);
		secondButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Intent intent = new Intent();
//				ComponentName component = new ComponentName("com.riley.threadpoolapplication", "com.riley.threadpoolapplication.MainActivity");
//				intent.setComponent(component);
//				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//				intent.addCategory(Intent.CATEGORY_LAUNCHER);
//				intent.setAction(Intent.ACTION_MAIN);
//				startActivity(intent);
			}
		});
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		Log.i(GlobalConstant.TAG, "onStart()##########");
		super.onStart();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		String name = getIntent().getStringExtra("riley");
		Log.i(GlobalConstant.TAG, "onResume() name = " + name);
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		Log.i(GlobalConstant.TAG, "onPause()##########");
		super.onPause();
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		Log.i(GlobalConstant.TAG, "onStop()##########");
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		Log.i(GlobalConstant.TAG, "onDestroy()##########");
		super.onDestroy();
	}
}
