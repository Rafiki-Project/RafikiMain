package com.example.drawactivitydemo;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

public class CategoryDisplayService extends Service {
	ViewGroup mTopView;
	private String x;

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub

		WindowManager.LayoutParams mWindowParams = new WindowManager.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
				PixelFormat.TRANSPARENT);

		WindowManager wm = (WindowManager) this
				.getSystemService(Context.WINDOW_SERVICE);

		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		mTopView = (ViewGroup) inflater.inflate(R.layout.show, null);
		mTopView.setVisibility(View.VISIBLE);

		// getWindow().setAttributes(mWindowParams);
		// getWindow().setWindowAnimations(resId);
		wm.addView(mTopView, mWindowParams);

		return START_STICKY;
	}

}
