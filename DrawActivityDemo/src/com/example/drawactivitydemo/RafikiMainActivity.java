package com.example.drawactivitydemo;



import com.example.PacsMangerPackage.*;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class RafikiMainActivity extends Activity {

	Pac[] pacs;
	PackageManager packageManager;
	GridView drawerGrid;
	LauncherGridAdapter appsDrawer;
	RelativeLayout homeView;
	static boolean appLaunchable = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rafiki_launcher);
		packageManager = getPackageManager();
		drawerGrid = (GridView) findViewById(R.id.content);
		homeView = (RelativeLayout) findViewById(R.id.home_view);
		set_pacs();
		

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
