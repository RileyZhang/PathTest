package util;

import android.content.Context;

public class GlobalConstant {

	public static String TAG = "path_test";
	public static String OPERATE_TYPE = "operate_type";
	public static final int NOTIFICATION_ID_1 = 100;
	private static Context mContext = null;
	
	public static void setApplicationContext(Context context) {
		mContext = context;
	}
	
	public static Context getApplicationContext() {
		return mContext;
	}
}
