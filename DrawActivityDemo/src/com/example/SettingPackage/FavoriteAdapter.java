package com.example.SettingPackage;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.PacsMangerPackage.Pac;
import com.example.drawactivitydemo.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;

public class FavoriteAdapter extends BaseAdapter {

	private Context mainActivityContext;
	private Pac[] pacsforAdapter;
	SharedPreferences sharedPref;

	public FavoriteAdapter(Context context, Pac pacs[]) {
		sharedPref = context.getSharedPreferences("FavApp", 0);
		mainActivityContext = context;
		pacsforAdapter = pacs;
	}

	@Override
	public int getCount() {
		return pacsforAdapter.length;
	}

	@Override
	public Object getItem(int arg) {
		return pacsforAdapter[arg];
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	// method to populate rendered list rows of the list view
	@Override
	public View getView(int pos, View convertView, ViewGroup parent) {
		LayoutInflater li = (LayoutInflater) mainActivityContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView = li.inflate(R.layout.favoriteitem, parent, false);

		ImageView imgIcon = (ImageView) convertView
				.findViewById(R.id.favAppImageView);
		imgIcon.setImageDrawable(pacsforAdapter[pos].getIcon());
		final CheckBox checkbox = (CheckBox) convertView
				.findViewById(R.id.favAppCheckBox);
		checkbox.setText(pacsforAdapter[pos].getLabel());

		// check if the app is in the user preferences,if it is, then  the checkbox should be checked while it is rendered
		String favorite = sharedPref.getString("favorite", "empty");
		if (!favorite.equals("empty")) {
			try {
				JSONArray appData = new JSONArray(favorite);
				for (int i = 0; i < appData.length(); i++) {

					JSONObject item = appData.getJSONObject(i);
					if (item.getString("application").equals(
							checkbox.getText().toString())) {
						checkbox.setChecked(true);
						break;
					} else {
						checkbox.setChecked(false);
					}
				}
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		// on checking or un-checking an item in the list
		checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
				String favorite = sharedPref.getString("favorite", "empty");
				Log.e("favorite SharedPreferences ", favorite);
				Editor editor = sharedPref.edit();

				if (isChecked) {
					// adding an app to the preferences
					JSONArray appData;
					try {
						if (favorite.equals("empty"))
							appData = new JSONArray();
						else
							appData = new JSONArray(favorite);

						Log.d("check", "new selectedObj");
						JSONObject selectedObj = new JSONObject();
						selectedObj.put("application", checkbox.getText()
								.toString());
						appData.put(selectedObj);
						editor.putString("favorite", appData.toString());
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					// removing an app from the preferences
					Log.d("check", "selectedObj is removed");
					try {
						ArrayList<JSONObject> favList = new ArrayList<JSONObject>();
						favorite = sharedPref.getString("favorite", "empty");
						JSONArray appData = new JSONArray(favorite);
						for (int i = 0; i < appData.length(); i++) {
							favList.add(appData.getJSONObject(i));
						}
						for (int i = 0; i < appData.length(); i++) {
							if (favList.get(i).getString("application")
									.equals(checkbox.getText().toString())) {
								// item.remove("application");
								favList.remove(i);
								break;
							}

						}
						JSONArray appDataAfterRemove = new JSONArray(favList);
						editor.putString("favorite",
								appDataAfterRemove.toString());
					} catch (JSONException e) {
						e.printStackTrace();
					}

				}

				editor.commit();
			}
		});

		return convertView;
	}

}
