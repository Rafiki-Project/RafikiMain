package com.example.drawactivitydemo;

import java.util.ArrayList;
import java.util.List;

import com.example.SettingPackage.SettingActivity;

import android.R.bool;
import android.app.ActivityManager;
import android.app.Service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PixelFormat;

import android.os.Bundle;
import android.os.IBinder;

import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView.FindListener;
import android.widget.Button;

import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

public class SwaperService extends Service {

	private ViewGroup linearLayout;
	private ImageButton imgButtonFavorite;
	private ImageButton imgButtonSocial;
	private ImageButton imgButtonMedia;
	private ImageButton imgButtonApp;
	private ImageButton imgButtonSettings;
	private ToggleButton toggleButton;
	private WindowManager wm;

	private Intent social;
	private Intent media;
	private Intent app;
	private Intent settings;
	private Intent favorite;
	private View mTopView;
	private ViewGroup mTopViewCategory;
	private Display display;
	public static boolean isStarted = false;
	public static boolean isDrawn = false;

	SwaperService service = this;
	WindowManager.LayoutParams params1;
	WindowManager.LayoutParams params;
	private float screenWidth;
	private int screenWidth1;

	public static boolean settingFlag = false;
	public static boolean categoryflag = false;

	public SwaperService() {

	}

	@Override
	public IBinder onBind(Intent intent) {

		// TODO: Return the communication channel to the service.

		throw new UnsupportedOperationException("Not yet implemented");

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		if (isStarted) {

			return START_STICKY;

		}

		else {
			isStarted = true;

			params = new WindowManager.LayoutParams(
					WindowManager.LayoutParams.MATCH_PARENT,
					WindowManager.LayoutParams.WRAP_CONTENT,
					WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
					WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
					PixelFormat.TRANSPARENT);

			params1 = new WindowManager.LayoutParams(
					WindowManager.LayoutParams.WRAP_CONTENT,
					WindowManager.LayoutParams.WRAP_CONTENT,
					WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
					WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
					PixelFormat.TRANSPARENT);

			params1.gravity = Gravity.CENTER | Gravity.LEFT;
			params.gravity = Gravity.TOP | Gravity.CENTER;

			wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);

			display = wm.getDefaultDisplay();

			int width = wm.getDefaultDisplay().getWidth();
			int height = wm.getDefaultDisplay().getHeight();
			Toast.makeText(this, " width  " + width, Toast.LENGTH_LONG).show();
			Toast.makeText(this, " height  " + height, Toast.LENGTH_LONG)
					.show();

			LayoutInflater inflater1 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			mTopView = (View) inflater1.inflate(R.layout.activity_rafiki_main,
					null);

			linearLayout = (ViewGroup) mTopView.findViewById(R.id.content);

			// double halfScreenWidth=printSecreenInfo();
			double halfScreenWidth = height;
			// double halfScreenWidth = printSecreenInfo();
			// int pixels = (int) (dps * halfScreenWidth + 0.5f);
			double sizeForOneIcon = halfScreenWidth / 5;
			TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 65,
					getResources().getDisplayMetrics());
			toggleButton = new ToggleButton(this);
			toggleButton.setBackground(getResources().getDrawable(R.drawable.appswappertoggle));
			// toggleButton.setBackground(getResources().getDrawable(
			// R.drawable.ic_launcher));
			imgButtonFavorite = (ImageButton) mTopView
					.findViewById(R.id.imageButtonFavorite);
			imgButtonSocial = (ImageButton) mTopView
					.findViewById(R.id.imageButtonSocial);
			imgButtonMedia = (ImageButton) mTopView
					.findViewById(R.id.imageButtonMedia);
			imgButtonApp = (ImageButton) mTopView
					.findViewById(R.id.imageButtonApp);
			imgButtonSettings = (ImageButton) mTopView
					.findViewById(R.id.imageButtonSettings);

			imgButtonFavorite.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					if (categoryflag
							& (DrawCategoryWindow.mTopViewCategory != null)) {
						wm.removeView(DrawCategoryWindow.mTopViewCategory);
					}
					// check for setting actvity
					if (isForeground("")) {

						finishSettingActivity();
					}
					new DrawCategoryWindow(wm, mTopView, SwaperService.this,
							mTopViewCategory, getPackageManager(), 4);
					categoryflag = true;
				}
			});

			imgButtonSocial.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					if (categoryflag
							& (DrawCategoryWindow.mTopViewCategory != null)) {
						wm.removeView(DrawCategoryWindow.mTopViewCategory);
					}
					if (isForeground("")) {

						finishSettingActivity();
					}

					new DrawCategoryWindow(wm, mTopView, SwaperService.this,
							mTopViewCategory, getPackageManager(), 2);
					categoryflag = true;
				}
			});

			imgButtonMedia.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub

					if (categoryflag
							& (DrawCategoryWindow.mTopViewCategory != null)) {
						wm.removeView(DrawCategoryWindow.mTopViewCategory);
					}

					if (isForeground("")) {

						finishSettingActivity();
					}
					new DrawCategoryWindow(wm, mTopView, SwaperService.this,
							mTopViewCategory, getPackageManager(), 1);
					categoryflag = true;
				}
			});

			imgButtonApp.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub

					if (categoryflag
							& (DrawCategoryWindow.mTopViewCategory != null)) {
						wm.removeView(DrawCategoryWindow.mTopViewCategory);
					}

					if (isForeground("")) {

						finishSettingActivity();
					}

					new DrawCategoryWindow(wm, mTopView,SwaperService.this,
							mTopViewCategory, getPackageManager(), 3);
					categoryflag = true;
				}

			});

			imgButtonSettings.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					if (categoryflag
							& (DrawCategoryWindow.mTopViewCategory != null)) {
						wm.removeView(DrawCategoryWindow.mTopViewCategory);
					}

					if (!settingFlag && (!isForeground(""))) {
						Intent intent = new Intent(SwaperService.this,
								SettingActivity.class);

						Bundle myKillerBundle = new Bundle();
						myKillerBundle.putInt("kill", 0);
						intent.putExtras(myKillerBundle);

						intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
						intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

						startActivity(intent);

						settingFlag = true;
					}
					categoryflag = false;

				}
			});

			int sizeOfChildIcons = mTopView.getWidth() / 5;

			imgButtonSettings.getLayoutParams().width = (int) sizeForOneIcon;
			imgButtonApp.getLayoutParams().width = (int) sizeForOneIcon;
			imgButtonMedia.getLayoutParams().width = (int) sizeForOneIcon;
			imgButtonFavorite.getLayoutParams().width = (int) sizeForOneIcon;
			imgButtonSocial.getLayoutParams().width = (int) sizeForOneIcon;

			// //

			imgButtonSettings.getLayoutParams().height = (int) sizeForOneIcon;
			imgButtonApp.getLayoutParams().height = (int) sizeForOneIcon;
			imgButtonMedia.getLayoutParams().height = (int) sizeForOneIcon;
			imgButtonFavorite.getLayoutParams().height = (int) sizeForOneIcon;
			imgButtonSocial.getLayoutParams().height = (int) sizeForOneIcon;
int sizeForOneImage= (int) sizeForOneIcon;
			
			Bitmap apps = BitmapFactory.decodeResource(SwaperService.this.getResources(), R.drawable.ic_rafiki_apps) ;
			Bitmap favorite = BitmapFactory.decodeResource(SwaperService.this.getResources(), R.drawable.ic_rafiki_favorites) ;
			Bitmap social = BitmapFactory.decodeResource(SwaperService.this.getResources(), R.drawable.ic_rafiki_social) ;
			Bitmap music = BitmapFactory.decodeResource(SwaperService.this.getResources(), R.drawable.ic_rafiki_media) ;
			Bitmap settings = BitmapFactory.decodeResource(SwaperService.this.getResources(), R.drawable.ic_rafiki_settings) ;
			
			
		
			Bitmap resizedApps = Bitmap.createScaledBitmap(apps,
					(int) (sizeForOneImage), (int) (sizeForOneImage), true);
			
			Bitmap resizedFavorites = Bitmap.createScaledBitmap(favorite,
					(int) (sizeForOneImage), (int) (sizeForOneImage), true);
			
			Bitmap resizedSocial = Bitmap.createScaledBitmap(social,
					(int) (sizeForOneImage), (int) (sizeForOneImage), true);
			
			Bitmap resizedMusic = Bitmap.createScaledBitmap(music,
					(int) (sizeForOneImage), (int) (sizeForOneImage), true);
			
			Bitmap resizedSettings = Bitmap.createScaledBitmap(settings,
					(int) (sizeForOneImage), (int) (sizeForOneImage), true);
			
			imgButtonApp.setImageBitmap(resizedApps); 
			imgButtonFavorite.setImageBitmap(resizedFavorites); 
			imgButtonMedia.setImageBitmap(resizedMusic); 
			imgButtonSettings.setImageBitmap(resizedSettings); 
			imgButtonSocial.setImageBitmap(resizedSocial); 
			

			toggleButton
					.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							if (isChecked) {
								// The toggle is enabled
								Animation up = AnimationUtils.loadAnimation(
										service.getApplicationContext(),
										R.anim.swapperanimationdown);
								linearLayout.startAnimation(up);
								if (isDrawn) {
								}

								else {
									isDrawn = true;

									wm.addView(mTopView, params);

								}

							} else {
								// The toggle is disabled
								Animation down = AnimationUtils.loadAnimation(
										service.getApplicationContext(),
										R.anim.swapperanimationup);
								linearLayout.startAnimation(down);
								if (isDrawn) {
									isDrawn = false;

									wm.removeView(mTopView);

								}

								else {

								}

							}
						}
					});

			if (isDrawn) {
			}

			else {
				isDrawn = true;
				wm.addView(toggleButton, params1);
				wm.addView(mTopView, params);

			}

			return START_STICKY;
		}
	}

	@Override
	public void onCreate() {

		// For time consuming an long tasks you can launch a new thread here...

		Toast.makeText(this, " Service Created", Toast.LENGTH_LONG).show();

	}

	@Override
	public void onDestroy() {

		Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();

	}

	double printSecreenInfo() {

		DisplayMetrics metrics = new DisplayMetrics();
		display.getMetrics(metrics);

		Log.d("one", "density :" + metrics.density);

		// density interms of dpi
		Log.d("one", "D density :" + metrics.densityDpi);

		// horizontal pixel resolution
		Log.d("one", "width pix :" + metrics.widthPixels);
		screenWidth = metrics.widthPixels;

		// horizontal pixel resolution
		Log.d("one", "height pix :" + metrics.heightPixels);
		screenWidth1 = metrics.heightPixels;

		// actual horizontal dpi
		Log.d("one", "xdpi :" + metrics.xdpi);
		screenWidth = metrics.xdpi;
		// actual vertical dpi
		Log.d("one", "ydpi :" + metrics.ydpi);

		return screenWidth1;

	}

	public boolean isForeground(String PackageName) {
		// Get the Activity Manager
		ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);

		// Get a list of running tasks, we are only interested in the last one,
		// the top most so we give a 1 as parameter so we only get the topmost.
		List<ActivityManager.RunningTaskInfo> task = manager.getRunningTasks(1);

		// Get the info we need for comparison.
		ComponentName componentInfo = task.get(0).topActivity;

		Toast toast = Toast.makeText(SwaperService.this,
				componentInfo.getClassName(), Toast.LENGTH_LONG);
		toast.setGravity(Gravity.BOTTOM | Gravity.LEFT, 0, 0);
		toast.show();

		// Check if it matches our package name.
		// if(componentInfo.getPackageName().equals(PackageName)) return true;
		if (componentInfo.getClassName().equals(
				"com.example.SettingPackage.SettingActivity"))
			return true;
		// If not then our app is not on the foreground.
		return false;
	}

	private void finishSettingActivity() {
		// TODO Auto-generated method stub
		Intent myIntent = new Intent(SwaperService.this, SettingActivity.class);
		myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
				| Intent.FLAG_ACTIVITY_CLEAR_TASK);
		Bundle myKillerBundle = new Bundle();
		myKillerBundle.putInt("kill", 1);
		myIntent.putExtras(myKillerBundle);
		getApplication().startActivity(myIntent);
	}

}
