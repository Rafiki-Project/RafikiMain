package com.example.drawactivitydemo;

import java.util.ArrayList;

import com.example.PacsMangerPackage.Pac;

import android.R.bool;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;

public class DrawCategoryWindow {

	Display display;
	Point size = new Point();

	public static ViewGroup mTopViewCategory;

	public DrawCategoryWindow(WindowManager wm, View sawperView,
			Context serviceContext, ViewGroup mTopViewCategory,
			PackageManager pm, int categoryCode) {

		// get screen size
		display = wm.getDefaultDisplay();
		display.getSize(size);
		int width = size.x;
		int height = size.y;

		int topCategoryWidth = (int) (width * 0.75);

		int m = 300;

		this.mTopViewCategory = mTopViewCategory;
		WindowManager.LayoutParams mWindowParams = new WindowManager.LayoutParams(
				topCategoryWidth, height / 2,
				WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
				WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
						| WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
						| WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
				PixelFormat.TRANSPARENT);

		wm = (WindowManager) serviceContext
				.getSystemService(Context.WINDOW_SERVICE);

		LayoutInflater inflater = (LayoutInflater) serviceContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		this.mTopViewCategory = (ViewGroup) inflater.inflate(
				R.layout.activity_category_layout, null);

		GridView gridCategories = (GridView) this.mTopViewCategory
				.findViewById(R.id.gridcategories);
		ArrayList<Pac> pacs = new CategorySawper().getLayout(gridCategories,
				categoryCode, pm, serviceContext, this.mTopViewCategory);

		gridCategories.setOnItemClickListener(new DrawerClickListener(wm,
				serviceContext, pacs, pm, this.mTopViewCategory, sawperView));

		this.mTopViewCategory.setVisibility(View.VISIBLE);

		wm.addView(this.mTopViewCategory, mWindowParams);

	}

}
