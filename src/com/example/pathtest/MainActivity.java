package com.example.pathtest;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;
import util.GlobalConstant;

public class MainActivity extends Activity implements OnClickListener{

	private TextView mTest;
	private Button mCommonButton;
	private Button mCustomButton;
	private Button mNextButton;
	private Context mContext;
	private NotificationManager myManager;
	private Notification myNotification;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Log.i(GlobalConstant.TAG, "main onCreate()");
		mContext = this;
		setContentView(R.layout.activity_main);
		mTest = (TextView) findViewById(R.id.test);
		DisplayMetrics dm = this.getResources().getDisplayMetrics();
		int height = dm.heightPixels;
		int width = dm.widthPixels;
		float desity = dm.density;
		int desityDpi = dm.densityDpi;
		String src = "height = " + height + " width = " + width + " desity" + desity + " desityDpi = " + desityDpi;
		mTest.setText(src);
		
		myManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		mCommonButton = (Button) findViewById(R.id.common_notify);
		mCommonButton.setOnClickListener(this);
		mCustomButton = (Button) findViewById(R.id.custom_notify);
		mCustomButton.setOnClickListener(this);
		mNextButton = (Button) findViewById(R.id.enter_next_activity);
		mNextButton.setOnClickListener(this);
		
		GlobalConstant.setApplicationContext(getApplicationContext());
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		Log.i(GlobalConstant.TAG, "main onResume()");
		super.onResume();
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		Log.i(GlobalConstant.TAG, "main onStop()");
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		Log.i(GlobalConstant.TAG, "main onDestroy()");
		super.onDestroy();
	}
	
	public void shwoCustomNotify(){
		RemoteViews view_custom = new RemoteViews(getPackageName(), R.layout.notify_view);
		view_custom.setTextViewText(R.id.notify_title, "今日头条");
		view_custom.setTextViewText(R.id.notify_content, "金州勇士官方宣布球队已经解雇了主帅马克-杰克逊，随后宣布了最后的结果。");
		
		Intent intent = new Intent(mContext, NotifyService.class);
		intent.putExtra(GlobalConstant.OPERATE_TYPE, 0);
		PendingIntent pendingIntent = PendingIntent.getService(mContext, 0, intent,  PendingIntent.FLAG_CANCEL_CURRENT);
		view_custom.setOnClickPendingIntent(R.id.cancel_botton, pendingIntent);
		
		Intent intent2 = new Intent(mContext, SecondActivity.class);
		PendingIntent pendingIntent2 = PendingIntent.getActivity(mContext,1, intent2, PendingIntent.FLAG_CANCEL_CURRENT);
		view_custom.setOnClickPendingIntent(R.id.new_activity_button, pendingIntent2);
		
		NotificationManager mNotificationManager = (NotificationManager) mContext.getSystemService(mContext.NOTIFICATION_SERVICE);
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext);
		mBuilder.setContent(view_custom)
				.setContentIntent(getDefalutIntent(Notification.FLAG_AUTO_CANCEL))
				.setWhen(System.currentTimeMillis())// 通知产生的时间，会在通知信息里显示
				.setTicker("有新资讯")
				 //设置默认声音和震动  
	            .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
				.setPriority(Notification.PRIORITY_DEFAULT)// 设置该通知优先级
				.setOngoing(false)//不是正在进行的   true为正在进行  效果和.flag一样
				.setSmallIcon(R.drawable.ic_launcher);
		Notification notify = mBuilder.build();
		notify.contentView = view_custom;
		mNotificationManager.notify(GlobalConstant.NOTIFICATION_ID_1, notify);
	}
	
	private void showCommonNotify() {
		//3.定义一个PendingIntent，点击Notification后启动一个Activity  
      PendingIntent pi = PendingIntent.getActivity(
              mContext,  
              100,  
              new Intent(mContext, MainActivity.class),
              PendingIntent.FLAG_CANCEL_CURRENT  
      );

      //2.通过Notification.Builder来创建通知  
      Notification.Builder myBuilder = new Notification.Builder(mContext);
      myBuilder.setContentTitle("QQ")
              .setContentText("这是内容")
              .setSubText("这是补充小行内容")
              .setTicker("您收到新的消息")
              //设置状态栏中的小图片，尺寸一般建议在24×24，这个图片同样也是在下拉状态栏中所显示  
              .setSmallIcon(R.drawable.ic_launcher)
              //设置默认声音和震动  
              .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)  
              .setAutoCancel(true)//点击后取消  
              .setWhen(System.currentTimeMillis())//设置通知时间  
              .setPriority(Notification.PRIORITY_HIGH)//高优先级  
              //android5.0加入了一种新的模式Notification的显示等级，共有三种：  
              //VISIBILITY_PUBLIC  只有在没有锁屏时会显示通知  
              //VISIBILITY_PRIVATE 任何情况都会显示通知  
              //VISIBILITY_SECRET  在安全锁和没有锁屏的情况下显示通知  
              .setContentIntent(pi);  //3.关联PendingIntent  
      myNotification = myBuilder.build();  
      //4.通过通知管理器来发起通知，ID区分通知  
      myManager.notify(GlobalConstant.NOTIFICATION_ID_1, myNotification);
	}
	
	private void showNotify() {
		Log.i("mtq", "showNotify()");
		PendingIntent pendingIntent= PendingIntent.getActivity(this, 1, new Intent(), PendingIntent.FLAG_CANCEL_CURRENT);
		RemoteViews view_custom = new RemoteViews(getPackageName(), R.layout.notify_view);
		view_custom.setTextViewText(R.id.notify_title, "今日头条");
		view_custom.setTextViewText(R.id.notify_content, "金州勇士官方宣布球队已经解雇了主帅马克-杰克逊，随后宣布了最后的结果。");
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
		mBuilder.setTicker("测试通知来啦")//通知首次出现在通知栏，带上升动画效果的
				.setContentIntent(pendingIntent)
				.setWhen(System.currentTimeMillis())// 通知产生的时间，会在通知信息里显示
				.setPriority(Notification.PRIORITY_HIGH)// 设置该通知优先级
				.setOngoing(false)//不是正在进行的   true为正在进行  效果和.flag一样
				.setContent(view_custom)
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)//设置默认声音和震动  
				.setSmallIcon(R.drawable.ic_launcher);
		Notification notify = mBuilder.build();
		notify.contentView = view_custom;
		mNotificationManager.notify(GlobalConstant.NOTIFICATION_ID_1, notify);
	}
	
    public PendingIntent getDefalutIntent(int flags){
        PendingIntent pendingIntent= PendingIntent.getActivity(this, 1, new Intent(), flags);  
        return pendingIntent;  
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.common_notify:
			showCommonNotify();
			break;
		case R.id.custom_notify:
			shwoCustomNotify();
			break;
		case R.id.enter_next_activity:
			Intent intent = new Intent(mContext, SecondActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}  
}
