package com.example.SettingPackage;

import com.example.drawactivitydemo.*;
import com.example.drawactivitydemo.R;

import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Gravity;
import android.view.Menu;
import android.widget.Toast;

public class SettingActivity extends PreferenceActivity implements
		OnPreferenceClickListener {

	public static int state = 0;
	
	
	int requestCode =0 ;
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		state = 2;
		showState();
		super.onPause();
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
//		MyService.settingFlag = false;
		state = 3;
		showState();
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		SwaperService.settingFlag = false;
		state = 4;
		showState();
		super.onDestroy();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		state = 1;
		showState();
		
		if(this.getIntent().getExtras().getInt("kill")==1){
		    finish();
		}
		
		addPreferencesFromResource(R.xml.prefs);
		// setContentView(R.layout.activity_main);
		
		
		Preference editFav = findPreference("editFav");
		editFav.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(Preference preference) {
				Intent intent = new Intent(getApplicationContext(),
						FavoriteSettings.class);
				
				startActivityForResult(intent, requestCode );
				
				//startActivity(intent);
				return true;
			}
		});

		
		Preference clearFav = findPreference("clearFav");
		clearFav.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(Preference preference) {

				SharedPreferences sharedPref = getApplicationContext()
						.getSharedPreferences("FavApp", 0);
				Editor editor = sharedPref.edit();
				editor.remove("favorite");
				editor.commit();

				return true;
			}
		});

		
		Preference mediaVloume = (Preference) findPreference("mediaVloumeKey");

        mediaVloume.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(Preference preference) {
				// TODO Auto-generated method stub
				int progress = 1;
				 AudioManager aManager = (AudioManager) getSystemService(AUDIO_SERVICE);
		            aManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, AudioManager.FLAG_SHOW_UI + AudioManager.FLAG_PLAY_SOUND);	
				return false;
			}
		});
       
        Preference systemVloume = (Preference) findPreference("systemVloumeKey");
        systemVloume.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(Preference preference) {
				// TODO Auto-generated method stub
				int progress = 1;
				 AudioManager aManager = (AudioManager) getSystemService(AUDIO_SERVICE);
		            aManager.setStreamVolume(AudioManager.STREAM_SYSTEM, progress, AudioManager.FLAG_SHOW_UI + AudioManager.FLAG_PLAY_SOUND);	
				return false;
			}
		});
        
		Preference wifi = findPreference("wifi");
		wifi.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(Preference preference) {

				startActivity(new Intent(WifiManager.ACTION_PICK_WIFI_NETWORK));

				return true;
			}
		});

		Preference bluetooth = findPreference("bluetooth");
		bluetooth.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(Preference preference) {

				Intent intentBluetooth = new Intent();
				intentBluetooth
						.setAction(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS);
				startActivity(intentBluetooth);

				return true;
			}
		});

		Preference dateAndTime = findPreference("dateAndTime");
		dateAndTime
				.setOnPreferenceClickListener(new OnPreferenceClickListener() {
					@Override
					public boolean onPreferenceClick(Preference preference) {
						startActivity(new Intent(
								android.provider.Settings.ACTION_DATE_SETTINGS));

						return true;
					}
				});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onPreferenceClick(Preference preference) {
		// TODO Auto-generated method stub
		int progress = 1;
		if (preference.getKey().equals("volPref")) {

			AudioManager aManager = (AudioManager) getSystemService(AUDIO_SERVICE);
			aManager.setStreamVolume(AudioManager.STREAM_RING, progress,
					AudioManager.FLAG_SHOW_UI + AudioManager.FLAG_PLAY_SOUND);

		}
		return true;
	}

	private void showState(){
		Toast toast = Toast.makeText(SettingActivity.this,"state : "+state,Toast.LENGTH_LONG);
		toast.setGravity(Gravity.BOTTOM | Gravity.RIGHT,0, 0);
		toast.show();
	}
	
}
