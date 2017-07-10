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
	private Context mContext;
	private NotificationManager myManager;
	private Notification myNotification;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
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
	}
	
	public void shwoCustomNotify(){
		RemoteViews view_custom = new RemoteViews(getPackageName(), R.layout.notify_view);
		view_custom.setTextViewText(R.id.notify_title, "����ͷ��");
		view_custom.setTextViewText(R.id.notify_content, "������ʿ�ٷ���������Ѿ��������˧���-�ܿ�ѷ��������������Ľ����");
		
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
				.setWhen(System.currentTimeMillis())// ֪ͨ������ʱ�䣬����֪ͨ��Ϣ����ʾ
				.setTicker("������Ѷ")
				 //����Ĭ����������  
	            .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
				.setPriority(Notification.PRIORITY_DEFAULT)// ���ø�֪ͨ���ȼ�
				.setOngoing(false)//�������ڽ��е�   trueΪ���ڽ���  Ч����.flagһ��
				.setSmallIcon(R.drawable.ic_launcher);
		Notification notify = mBuilder.build();
		notify.contentView = view_custom;
		mNotificationManager.notify(GlobalConstant.NOTIFICATION_ID_1, notify);
	}
	
	private void showCommonNotify() {
		//3.����һ��PendingIntent�����Notification������һ��Activity  
      PendingIntent pi = PendingIntent.getActivity(
              mContext,  
              100,  
              new Intent(mContext, MainActivity.class),
              PendingIntent.FLAG_CANCEL_CURRENT  
      );

      //2.ͨ��Notification.Builder������֪ͨ  
      Notification.Builder myBuilder = new Notification.Builder(mContext);
      myBuilder.setContentTitle("QQ")
              .setContentText("��������")
              .setSubText("���ǲ���С������")
              .setTicker("���յ��µ���Ϣ")
              //����״̬���е�СͼƬ���ߴ�һ�㽨����24��24�����ͼƬͬ��Ҳ��������״̬��������ʾ  
              .setSmallIcon(R.drawable.ic_launcher)
              //����Ĭ����������  
              .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)  
              .setAutoCancel(true)//�����ȡ��  
              .setWhen(System.currentTimeMillis())//����֪ͨʱ��  
              .setPriority(Notification.PRIORITY_HIGH)//�����ȼ�  
              //android5.0������һ���µ�ģʽNotification����ʾ�ȼ����������֣�  
              //VISIBILITY_PUBLIC  ֻ����û������ʱ����ʾ֪ͨ  
              //VISIBILITY_PRIVATE �κ����������ʾ֪ͨ  
              //VISIBILITY_SECRET  �ڰ�ȫ����û���������������ʾ֪ͨ  
              .setContentIntent(pi);  //3.����PendingIntent  
      myNotification = myBuilder.build();  
      //4.ͨ��֪ͨ������������֪ͨ��ID����֪ͨ  
      myManager.notify(GlobalConstant.NOTIFICATION_ID_1, myNotification);
	}
	
	private void showNotify() {
		Log.i("mtq", "showNotify()");
		PendingIntent pendingIntent= PendingIntent.getActivity(this, 1, new Intent(), PendingIntent.FLAG_CANCEL_CURRENT);
		RemoteViews view_custom = new RemoteViews(getPackageName(), R.layout.notify_view);
		view_custom.setTextViewText(R.id.notify_title, "����ͷ��");
		view_custom.setTextViewText(R.id.notify_content, "������ʿ�ٷ���������Ѿ��������˧���-�ܿ�ѷ��������������Ľ����");
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
		mBuilder.setTicker("����֪ͨ����")//֪ͨ�״γ�����֪ͨ��������������Ч����
				.setContentIntent(pendingIntent)
				.setWhen(System.currentTimeMillis())// ֪ͨ������ʱ�䣬����֪ͨ��Ϣ����ʾ
				.setPriority(Notification.PRIORITY_HIGH)// ���ø�֪ͨ���ȼ�
				.setOngoing(false)//�������ڽ��е�   trueΪ���ڽ���  Ч����.flagһ��
				.setContent(view_custom)
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)//����Ĭ����������  
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
		default:
			break;
		}
	}  
}
