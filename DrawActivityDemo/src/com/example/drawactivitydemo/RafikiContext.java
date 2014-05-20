package com.example.drawactivitydemo;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

public class RafikiContext extends Application {

	private WindowManager windowManager ;
	LayoutInflater inflater ;

	private View mTopView ;
	private View mTopViewCategory;


	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		windowManager =  (WindowManager) getSystemService(Context.WINDOW_SERVICE);
	
		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		mTopView  = (View) inflater.inflate(R.layout.activity_rafiki_main,null);
		
		mTopViewCategory = (ViewGroup) inflater.inflate(R.layout.activity_category_layout, null);
	}
	
	public WindowManager getWindowManager() {
		return windowManager;
	}

	public void setWindowManager(WindowManager windowManager) {
		this.windowManager = windowManager;
	}

	public View getmTopView() {
		return mTopView;
	}

	public void setmTopView(View mTopView) {
		this.mTopView = mTopView;
	}

	public View getmTopViewCategory() {
		return mTopViewCategory;
	}

	public void setmTopViewCategory(View mTopViewCategory2) {
		this.mTopViewCategory = mTopViewCategory2;
		
	}

}
