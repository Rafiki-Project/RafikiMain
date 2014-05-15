package com.example.SettingPackage;

import com.example.PacsMangerPackage.CategoryLoader;
import com.example.PacsMangerPackage.Pac;
import com.example.drawactivitydemo.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class FavoriteSettings extends Activity {


	Button saveButton ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.favorite_activity);

		
		
		saveButton = (Button) findViewById(R.id.save_button);
		saveButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				
				// send back  to setting activity  bel result
				// override finfish to send data in it
				finish();
				
			}
		});
		
		Pac[] pacs;
		pacs = CategoryLoader.getCategoryLoader()
				.getAllInstalledApplications(getPackageManager());

		ListView favAppListView = (ListView) findViewById(R.id.favoritelistview);
		
		favAppListView.setAdapter(new FavoriteAdapter(getApplicationContext(),
				pacs));

	}
	
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		
		// send data back to setting activity
		setResult(RESULT_OK);
		
		/////////// or
		
		/*
		  if need to send data back from fav activity to setting activity
		  Prepare data intent 
		  
		  Intent data = new Intent();
		  data.putExtra("returnKey1", "Swinging on a star. ");
		  data.putExtra("returnKey2", "You could be better then you are. ");
		  setResult(RESULT_OK,data);
		*/
		
		
		super.finish();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
			
		if (resultCode == RESULT_OK && requestCode == 1 ) {
		
			Toast.makeText(this,"requst code : "+ requestCode,Toast.LENGTH_LONG).show();
		}
		
	}
	
}
