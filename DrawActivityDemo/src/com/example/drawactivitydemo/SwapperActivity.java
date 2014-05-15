package com.example.drawactivitydemo;

import android.os.Bundle;

import android.app.Activity;


import android.content.Intent;

import android.view.Menu;

import android.view.View;
import android.widget.Toast;


public class SwapperActivity extends Activity {
	public static SwapperActivity swapperActivity;
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        swapperActivity= this;

        Toast.makeText(this, "on Create", Toast.LENGTH_LONG).show();
       // setContentView(R.layout.activity_rafiki_main);
        setContentView(R.layout.main);
     

    }


    // Start the  service

    public void startNewService(View view) {

    

        Toast.makeText(this, "start new service", Toast.LENGTH_LONG).show();
        startService(new Intent(this, SwaperService.class));

    }

    // Stop the  service

    public void stopNewService(View view) {

        Toast.makeText(this, "stop service", Toast.LENGTH_LONG).show();

        stopService(new Intent(this, SwaperService.class));

    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.

  getMenuInflater().inflate(R.menu.main, menu);

        return true;

    }

}
