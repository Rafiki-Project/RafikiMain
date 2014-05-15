package com.example.PacsMangerPackage;

import java.util.ArrayList;
import java.util.List;

import org.json.*;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;

public class CategoryLoader {

	private static CategoryLoader categoryLoader = null;

	private CategoryLoader() {
	}

	public static CategoryLoader getCategoryLoader() {
		if (categoryLoader == null) {
			return categoryLoader = new CategoryLoader();
		} else {
			return categoryLoader;
		}

	}

	public Pac[] getAllInstalledApplications(PackageManager packageManager) {
		final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		List<ResolveInfo> pacsList = packageManager.queryIntentActivities(
				mainIntent, 0);

		Pac[] pacs = new Pac[pacsList.size()];
		for (int i = 0; i < pacsList.size(); i++) {
			pacs[i] = new Pac();
			pacs[i].setIcon(pacsList.get(i).loadIcon(packageManager));
			pacs[i].setLabel(pacsList.get(i).loadLabel(packageManager)
					.toString());
			pacs[i].setName(pacsList.get(i).activityInfo.packageName);

			Log.d("Apps", pacsList.get(i).loadLabel(packageManager).toString());
		}
		return pacs;

	}

	public Pac[] getMainCategoryApps(PackageManager pm) {
		final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		List<ResolveInfo> pacsList = pm.queryIntentActivities(mainIntent, 0);
		Pac[] pacs = new Pac[4];
		int j = 0;
		for (int i = 0; i < pacsList.size(); i++) {

			if ((pacsList.get(i).loadLabel(pm).toString()
					.equalsIgnoreCase("facebook")
					|| pacsList.get(i).loadLabel(pm).toString().toLowerCase()
							.contains("music")
					|| pacsList.get(i).loadLabel(pm).toString()
							.equalsIgnoreCase("internet") || pacsList.get(i)
					.loadLabel(pm).toString().equalsIgnoreCase("gallery"))
					&& j < 4) {
				pacs[j] = new Pac();
				pacs[j].setIcon(pacsList.get(i).loadIcon(pm));
				pacs[j].setLabel(pacsList.get(i).loadLabel(pm).toString());
				pacs[j].setName(pacsList.get(i).activityInfo.packageName);
				j++;
			}

		}
		return pacs;
	}

	public ArrayList<Pac> getMediaCategoryApps(PackageManager pm) {
		final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		List<ResolveInfo> pacsList = pm.queryIntentActivities(mainIntent, 0);
		ArrayList<Pac> pacs = new ArrayList<Pac>();
		int j = 0;
		for (int i = 0; i < pacsList.size(); i++) {

			if (pacsList.get(i).loadLabel(pm).toString()
					.equalsIgnoreCase("music player")
					|| pacsList.get(i).loadLabel(pm).toString()
							.equalsIgnoreCase("play music")
					|| pacsList.get(i).loadLabel(pm).toString()
							.equalsIgnoreCase("youtube")
					|| pacsList.get(i).loadLabel(pm).toString()
							.equalsIgnoreCase("shazam")
					|| pacsList.get(i).loadLabel(pm).toString()
							.equalsIgnoreCase("photos")
					|| pacsList.get(i).loadLabel(pm).toString()
							.equalsIgnoreCase("music hub")
					|| pacsList.get(i).loadLabel(pm).toString()
							.equalsIgnoreCase("video hub")
					|| pacsList.get(i).loadLabel(pm).toString()
							.equalsIgnoreCase("video player")
					|| pacsList.get(i).loadLabel(pm).toString()
							.equalsIgnoreCase("anghami")
					|| pacsList.get(i).loadLabel(pm).toString()
							.equalsIgnoreCase("soundcloud")) {
				Pac p = new Pac();
				p.setIcon(pacsList.get(i).loadIcon(pm));
				p.setLabel(pacsList.get(i).loadLabel(pm).toString());
				p.setName(pacsList.get(i).activityInfo.packageName);
				pacs.add(p);
				Log.d("Player", pacsList.get(i).loadLabel(pm).toString());

				j++;
			}

		}
		return pacs;
	}

	public ArrayList<Pac> getSocialCategoryApps(PackageManager pm) {
		final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		List<ResolveInfo> pacsList = pm.queryIntentActivities(mainIntent, 0);
		ArrayList<Pac> pacs = new ArrayList<Pac>();
		int j = 0;
		for (int i = 0; i < pacsList.size(); i++) {

			if (pacsList.get(i).loadLabel(pm).toString()
					.equalsIgnoreCase("facebook")
					|| pacsList.get(i).loadLabel(pm).toString()
							.equalsIgnoreCase("twitter")
					|| pacsList.get(i).loadLabel(pm).toString()
							.equalsIgnoreCase("linkedin")
					|| pacsList.get(i).loadLabel(pm).toString()
							.equalsIgnoreCase("whatsapp")
					|| pacsList.get(i).loadLabel(pm).toString()
							.equalsIgnoreCase("pinterest")
					|| pacsList.get(i).loadLabel(pm).toString()
							.equalsIgnoreCase("viber")
					|| pacsList.get(i).loadLabel(pm).toString()
							.equalsIgnoreCase("instagram")
					|| pacsList.get(i).loadLabel(pm).toString()
							.equalsIgnoreCase("messenger")
					|| pacsList.get(i).loadLabel(pm).toString()
							.equalsIgnoreCase("hangouts")
					|| pacsList.get(i).loadLabel(pm).toString()
							.equalsIgnoreCase("group cast")) {
				Pac p = new Pac();
				p.setIcon(pacsList.get(i).loadIcon(pm));
				p.setLabel(pacsList.get(i).loadLabel(pm).toString());
				p.setName(pacsList.get(i).activityInfo.packageName);
				pacs.add(p);
				Log.d("Player", pacsList.get(i).loadLabel(pm).toString());

				j++;
			}

		}
		return pacs;
	}

	public ArrayList<Pac> getFavCategoryApps(PackageManager pm, String favorite) {
		final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

		Log.d("favorites",favorite);
		
		List<ResolveInfo> pacsList = pm.queryIntentActivities(mainIntent, 0);
		ArrayList<Pac> pacs = new ArrayList<Pac>();

		try {
			
			JSONArray arrayOfFav = new JSONArray(favorite);
			Log.d("favorite.length()",String.valueOf(arrayOfFav.length()));
			for (int j = 0; j < pacsList.size(); j++) {
				for (int i = 0; i < arrayOfFav.length(); i++) {
					JSONObject item = arrayOfFav.getJSONObject(i);
					if (item.getString("application").equalsIgnoreCase(
							pacsList.get(j).loadLabel(pm).toString())) {
						Pac p = new Pac();
						p.setIcon(pacsList.get(j).loadIcon(pm))  ;
						p.setLabel(pacsList.get(j).loadLabel(pm).toString()) ;
						p.setName(pacsList.get(j).activityInfo.packageName);
						pacs.add(p);
					}
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pacs;
	}

	public ArrayList<Pac> getOtherAppsCategoryApps(PackageManager pm) {
		final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		List<ResolveInfo> pacsList = pm.queryIntentActivities(mainIntent, 0);
		ArrayList<Pac> pacs = new ArrayList<Pac>();
		int j = 0;
		for (int i = 0; i < pacsList.size(); i++) {
			if (!(pacsList.get(i).loadLabel(pm).toString()
					.equalsIgnoreCase("facebook")
					|| pacsList.get(i).loadLabel(pm).toString()
							.equalsIgnoreCase("twitter")
					|| pacsList.get(i).loadLabel(pm).toString()
							.equalsIgnoreCase("linkedin")
					|| pacsList.get(i).loadLabel(pm).toString()
							.equalsIgnoreCase("whatsapp")
					|| pacsList.get(i).loadLabel(pm).toString()
							.equalsIgnoreCase("pinterest")
					|| pacsList.get(i).loadLabel(pm).toString()
							.equalsIgnoreCase("viber")
					|| pacsList.get(i).loadLabel(pm).toString()
							.equalsIgnoreCase("instagram") || pacsList.get(i)
					.loadLabel(pm).toString().equalsIgnoreCase("messenger")
					|| pacsList.get(i).loadLabel(pm).toString()
							.equalsIgnoreCase("hangouts")
					|| pacsList.get(i).loadLabel(pm).toString()
							.equalsIgnoreCase("group cast")
					|| pacsList.get(i).loadLabel(pm).toString()
							.equalsIgnoreCase("music player")
					|| pacsList.get(i).loadLabel(pm).toString()
							.equalsIgnoreCase("play music")
					|| pacsList.get(i).loadLabel(pm).toString()
							.equalsIgnoreCase("YouTube")
					|| pacsList.get(i).loadLabel(pm).toString()
							.equalsIgnoreCase("shazam")
					|| pacsList.get(i).loadLabel(pm).toString()
							.equalsIgnoreCase("photos")
					|| pacsList.get(i).loadLabel(pm).toString()
							.equalsIgnoreCase("music hub")
					|| pacsList.get(i).loadLabel(pm).toString()
							.equalsIgnoreCase("video hub")
					|| pacsList.get(i).loadLabel(pm).toString()
							.equalsIgnoreCase("video player")
					|| pacsList.get(i).loadLabel(pm).toString()
							.equalsIgnoreCase("anghami")
					|| pacsList.get(i).loadLabel(pm).toString()
							.equalsIgnoreCase("soundcloud"))) {
				Pac p = new Pac();
				p.setIcon(pacsList.get(i).loadIcon(pm));
				p.setLabel(pacsList.get(i).loadLabel(pm).toString());
				p.setName(pacsList.get(i).activityInfo.packageName);
				pacs.add(p);
				Log.d("Player", pacsList.get(i).loadLabel(pm).toString());
			}

		}
		return pacs;
	}
}
