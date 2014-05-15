package com.example.drawactivitydemo;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class LauncherBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent arg1) {
		Toast.makeText(context, "", Toast.LENGTH_LONG).show();

		context.startService(new Intent(context, SwaperService.class));
		
		
		
//		
//
//        Toast.makeText(context, "start new service", Toast.LENGTH_LONG).show();
//        startService(new Intent(context, SawperService.class));
        
	}

}
