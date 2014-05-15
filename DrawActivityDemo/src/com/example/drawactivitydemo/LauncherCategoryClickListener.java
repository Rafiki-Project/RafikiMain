package com.example.drawactivitydemo;
import com.example.PacsMangerPackage.Pac;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class LauncherCategoryClickListener implements OnItemClickListener {
	Context mainActivityContext;
	Pac[] pacsForAdapter;
	PackageManager pmForListener;

	public LauncherCategoryClickListener(Context context, Pac[] pacs,
			PackageManager pm) {

		this.mainActivityContext = context;
		this.pacsForAdapter = pacs;
		this.pmForListener = pm;

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
		
			Intent launchIntent = pmForListener
					.getLaunchIntentForPackage(pacsForAdapter[pos].getName());
			mainActivityContext.startActivity(launchIntent);
		

	}


}
