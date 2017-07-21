package com.example.pathtest;

import java.io.IOException;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.TextView;
import util.GlobalConstant;

public class SecondActivity extends Activity {

	private MediaPlayer mMediaPlayer;
	private Context mContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.i(GlobalConstant.TAG, "onCreate()##########");
		super.onCreate(savedInstanceState);
		mMediaPlayer = new MediaPlayer();
		mContext = this;
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
				Intent intent = new Intent(getApplicationContext(), RceyleViewActivity.class);
				startActivity(intent);
			}
		});
		
		Button thirdButton = (Button) findViewById(R.id.third_button);
		thirdButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					mMediaPlayer.reset();
					AssetFileDescriptor fileDescriptor = mContext.getAssets().openFd("task_message_remind.wav");
					mMediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(),fileDescriptor.getStartOffset(), fileDescriptor.getLength());
					mMediaPlayer.prepare();
					mMediaPlayer.start();
					mMediaPlayer.setLooping(true);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		Button fourthButton = (Button) findViewById(R.id.fourth_button);
		fourthButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					mMediaPlayer.stop();
					mMediaPlayer.reset();
					AssetFileDescriptor fileDescriptor = mContext.getAssets().openFd("dispatch_message_remind.wav");
					mMediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(),fileDescriptor.getStartOffset(), fileDescriptor.getLength());
					mMediaPlayer.prepare();
					mMediaPlayer.start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		fourthButton.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				
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
