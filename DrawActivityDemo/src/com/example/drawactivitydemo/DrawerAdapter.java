package com.example.drawactivitydemo;

import java.util.ArrayList;

import com.example.PacsMangerPackage.Pac;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class DrawerAdapter extends BaseAdapter {
	private Context mainActivityContext;

	private Pac[] pacsforAdapter;
	private ArrayList<Pac> pacsList = null;

	public DrawerAdapter(Context context, Pac pacs[]) {
		mainActivityContext = context;
		pacsforAdapter = pacs;
	}

	public DrawerAdapter(Context context, ArrayList<Pac> pacs) {
		mainActivityContext = context;
		pacsList = pacs;
	}

	@Override
	public int getCount() {
		if (pacsList != null) {
			return pacsList.size();
		}
		return pacsforAdapter.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	static class ViewHolder {
		TextView text;
		ImageView icon;
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup parent) {
		TextView text;
		ImageView icon;
		LayoutInflater li = (LayoutInflater) mainActivityContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView = li.inflate(R.layout.drawer_item, parent, false);
		text =  (TextView) convertView.findViewById(R.id.icon_text);
		icon = (ImageView) convertView.findViewById(R.id.icon_image);

		if (pacsList != null) {

			text.setText(pacsList.get(pos).getLabel());
			icon.setImageDrawable(pacsList.get(pos).getIcon());
		} else {
			text.setText(pacsforAdapter[pos].getLabel());
			icon.setImageDrawable(pacsforAdapter[pos].getIcon());
		}
		return convertView;
	}
}
