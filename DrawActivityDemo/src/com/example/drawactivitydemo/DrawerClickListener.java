package com.example.drawactivitydemo;

import java.util.ArrayList;

import com.example.PacsMangerPackage.Pac;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class DrawerClickListener implements OnItemClickListener {

	private Context mainActivityContext;
	private Pac[] pacsForAdapter;
	private PackageManager pmForListener;
	private ArrayList<Pac> pacsList = null;
	private View mTopView ;
	private View sawperView;
	
	private WindowManager windowManger;
	public DrawerClickListener(Context context, Pac[] pacs, PackageManager pm) {

		this.mainActivityContext = context;
		this.pacsForAdapter = pacs;
		this.pmForListener = pm;
		

	}

	public DrawerClickListener(WindowManager windowManger,Context context, ArrayList<Pac> pacs,
			PackageManager pm,View mTopView, View sawperView) {

		this.mainActivityContext = context;
		this.pacsList = pacs;
		this.pmForListener = pm;
		this.windowManger = windowManger;
		this.mTopView = mTopView;
		this.sawperView=sawperView;

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
		Intent launchIntent;
		if (pacsList != null) {
			launchIntent = pmForListener.getLaunchIntentForPackage(pacsList
					.get(pos).getName());
		} else {
			launchIntent = pmForListener
					.getLaunchIntentForPackage(pacsForAdapter[pos].getName());
			
		}
//
		
		SwaperService.isDrawn  = false;
		SwaperService.categoryflag = false;
		windowManger.removeView(mTopView);
		windowManger.removeView(sawperView);
		mainActivityContext.startActivity(launchIntent);
//		mTopView.setVisibility(View.GONE);
		
		
	}

}
