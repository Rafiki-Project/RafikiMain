package com.example.drawactivitydemo;

import java.util.ArrayList;

import com.example.PacsMangerPackage.CategoryLoader;
import com.example.PacsMangerPackage.Pac;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.RelativeLayout;

public class CategorySawper {

	private ArrayList<Pac> pacs = new ArrayList<Pac>();

	
	
	
	public ArrayList<Pac> getLayout(GridView gridCategories, int catergoryFlag,
			PackageManager pm, Context context,View mTopView) {

		switch (catergoryFlag) {

		case 1: // media

			pacs = CategoryLoader.getCategoryLoader().getMediaCategoryApps(pm);
			break;
		case 2: // social
			pacs = CategoryLoader.getCategoryLoader().getSocialCategoryApps(pm);
			break;

		case 3: // app
			pacs = CategoryLoader.getCategoryLoader().getOtherAppsCategoryApps(pm);
			break;
		case 4: // fav
			SharedPreferences sharedPref = context.getSharedPreferences("FavApp", 0);
			String favorite = sharedPref.getString("favorite", "empty");
			if(!favorite.equals("empty")){
			pacs =  CategoryLoader.getCategoryLoader()
					.getFavCategoryApps(pm,favorite);
			}
			break;
		case 5: // settings

			break;
		}

		DrawerAdapter catAdapter = new DrawerAdapter(context, pacs);
		gridCategories.setAdapter(catAdapter);
		return pacs;
	}

}
