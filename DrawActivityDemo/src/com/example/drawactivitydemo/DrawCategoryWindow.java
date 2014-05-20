package com.example.drawactivitydemo;

import java.util.ArrayList;

import com.example.PacsMangerPackage.Pac;

import android.R.bool;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.RelativeLayout;

public class DrawCategoryWindow {

	Display display;
	Point size = new Point();

	RafikiContext rafikiContext;
	private ViewGroup category_view;

	
	public  View mTopView;
	public static View mTopViewCategory;

	 private Context sawperServiceContext;
	private View grid_view;
	
	public DrawCategoryWindow(WindowManager wm, View sawperView,
			Context serviceContext, ViewGroup mTopViewCategory1,
			PackageManager pm, int categoryCode) {

	
		
		 rafikiContext = (RafikiContext) serviceContext.getApplicationContext();
		
		
		// get screen size
		display = wm.getDefaultDisplay();
		display.getSize(size);
		int width = size.x;
		int height = size.y;

		int topCategoryWidth = (int) (width * 0.75);

		int m = 300;

	
		WindowManager.LayoutParams mWindowParams = new WindowManager.LayoutParams(
				topCategoryWidth, height / 2,
				WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
				WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
						| WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
				PixelFormat.TRANSPARENT);

		
		
		
		
		sawperServiceContext = serviceContext;
//		wm = (WindowManager) serviceContext
//				.getSystemService(Context.WINDOW_SERVICE);

		LayoutInflater inflater = (LayoutInflater) serviceContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		this.mTopViewCategory = (View) inflater.inflate(
				R.layout.activity_category_layout, null);

		//this.mTopViewCategory = (ViewGroup) mTopView.findViewById(R.id.category_view);		
		
		category_view =  (ViewGroup) this.mTopViewCategory.findViewById(R.id.categoriescontent);
		
		grid_view=this.mTopViewCategory.findViewById(R.id.gridcategories);

		
//		this.mTopViewCategory =  rafikiContext.getmTopViewCategory();
		
		
		GridView gridCategories = (GridView) this.mTopViewCategory
				.findViewById(R.id.gridcategories);
		ArrayList<Pac> pacs = new CategorySawper().getLayout(gridCategories,
				categoryCode, pm, serviceContext, this.mTopViewCategory);

		gridCategories.setOnItemClickListener(new DrawerClickListener(wm,
				serviceContext, pacs, pm, this.mTopViewCategory, sawperView));

	
		
		this.mTopViewCategory.setVisibility(View.VISIBLE);

		
		this.mTopViewCategory.setFocusableInTouchMode(true);
		
		this.mTopViewCategory.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub\
				
				Log.d("back action ", "12");
                if (keyCode == KeyEvent.KEYCODE_BACK)
                {
                	Animation down = AnimationUtils.loadAnimation(sawperServiceContext.getApplicationContext(),R.anim.slide_up);
            		
            		rafikiContext.setmTopViewCategory(mTopViewCategory);

            		category_view.startAnimation(down);

                	
                    rafikiContext.getWindowManager().removeView(mTopViewCategory); // This I need to hide my menu
                    
                    SwaperService.categoryflag = false;
                }
				return false;
			}
		});
		
	
		Animation up = AnimationUtils.loadAnimation(serviceContext.getApplicationContext(),R.anim.slide_up);
		Animation gridIn = AnimationUtils.loadAnimation(serviceContext,R.anim.grid_anim_in);
		
		rafikiContext.setmTopViewCategory(this.mTopViewCategory);

		category_view.startAnimation(up);
		gridCategories.startAnimation(gridIn);
		
		
		wm.addView(this.mTopViewCategory, mWindowParams);

		//	wm.addView(rafikiContext.getmTopViewCategory(), mWindowParams);
	}

}
