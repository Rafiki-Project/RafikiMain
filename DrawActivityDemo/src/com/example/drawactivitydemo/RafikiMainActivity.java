package com.example.drawactivitydemo;



import com.example.PacsMangerPackage.*;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class RafikiMainActivity extends Activity {
	private WindowManager wm;
	Pac[] pacs;
	PackageManager packageManager;
	GridView drawerGrid;
	LauncherGridAdapter appsDrawer;
	RelativeLayout homeView;
	static boolean appLaunchable = true;
	private View mTopView;
	private Button imgButtonFavorite;
	private Button imgButtonSocial;
	private Button imgButtonMedia;
	private Button imgButtonApp;
	private Button imgButtonSettings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rafiki_launcher);
		packageManager = getPackageManager();
		wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
		
		
		int width = wm.getDefaultDisplay().getWidth();
		int height = wm.getDefaultDisplay().getHeight();
		LayoutInflater inflater1 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		mTopView = (View) inflater1.inflate(R.layout.activity_rafiki_launcher,
				null);
		
		
		imgButtonFavorite = (Button) mTopView
				.findViewById(R.id.buttonFav);
		imgButtonSocial = (Button) mTopView
				.findViewById(R.id.buttonSocial);
		imgButtonMedia = (Button) mTopView
				.findViewById(R.id.buttonMedia);
		imgButtonApp = (Button) mTopView
				.findViewById(R.id.buttonApps);
		imgButtonSettings = (Button) mTopView
				.findViewById(R.id.buttonSettings);
		
		double halfScreenWidth = height;
		
		double sizeForOneIcon = halfScreenWidth / 5;

		imgButtonSettings.getLayoutParams().width = (int) sizeForOneIcon;
		imgButtonApp.getLayoutParams().width = (int) sizeForOneIcon;
		imgButtonMedia.getLayoutParams().width = (int) sizeForOneIcon;
		imgButtonFavorite.getLayoutParams().width = (int) sizeForOneIcon;
		imgButtonSocial.getLayoutParams().width = (int) sizeForOneIcon;


		imgButtonSettings.getLayoutParams().height = (int) sizeForOneIcon;
		imgButtonApp.getLayoutParams().height = (int) sizeForOneIcon;
		imgButtonMedia.getLayoutParams().height = (int) sizeForOneIcon;
		imgButtonFavorite.getLayoutParams().height = (int) sizeForOneIcon;
		imgButtonSocial.getLayoutParams().height = (int) sizeForOneIcon;
int sizeForOneImage= (int) sizeForOneIcon;
		
		Bitmap apps = BitmapFactory.decodeResource(RafikiMainActivity.this.getResources(), R.drawable.ic_rafiki_apps) ;
		Bitmap favorite = BitmapFactory.decodeResource(RafikiMainActivity.this.getResources(), R.drawable.ic_rafiki_favorites) ;
		Bitmap social = BitmapFactory.decodeResource(RafikiMainActivity.this.getResources(), R.drawable.ic_rafiki_social) ;
		Bitmap music = BitmapFactory.decodeResource(RafikiMainActivity.this.getResources(), R.drawable.ic_rafiki_media) ;
		Bitmap settings = BitmapFactory.decodeResource(RafikiMainActivity.this.getResources(), R.drawable.ic_rafiki_settings) ;
		
		
	
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

		
		Drawable dApp = new BitmapDrawable(getResources(),resizedApps);
		
		Drawable dFav = new BitmapDrawable(getResources(),resizedFavorites);
		
		Drawable dSoc = new BitmapDrawable(getResources(),resizedSocial);
		
		Drawable dMus = new BitmapDrawable(getResources(),resizedMusic);
		
		Drawable dSet = new BitmapDrawable(getResources(),resizedSettings);
		
		
		imgButtonApp.setCompoundDrawablesWithIntrinsicBounds(null, dApp, null, null);

		imgButtonFavorite.setCompoundDrawablesWithIntrinsicBounds(null, dFav, null, null);

		imgButtonMedia.setCompoundDrawablesWithIntrinsicBounds(null, dMus, null, null);

		imgButtonSettings.setCompoundDrawablesWithIntrinsicBounds(null, dSet, null, null);

		imgButtonSocial.setCompoundDrawablesWithIntrinsicBounds(null, dSoc, null, null);

//		drawerGrid = new GridView(getApplicationContext());
		
//		set_pacs();
		

        Toast.makeText(this, "start new service", Toast.LENGTH_LONG).show();
        startService(new Intent(this, SwaperService.class));
		
	}

	public void set_pacs() {

		pacs = CategoryLoader.getCategoryLoader().getMainCategoryApps(packageManager);
		appsDrawer = new LauncherGridAdapter(getApplicationContext(), pacs);
		drawerGrid.setAdapter(appsDrawer);
		drawerGrid.setOnItemClickListener(new LauncherCategoryClickListener(
				getApplicationContext(), pacs, packageManager));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public void onBackPressed() {
	
	}

	public class PacReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			set_pacs();
		}

	}

}
