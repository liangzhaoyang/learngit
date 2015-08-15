package com.test.pptcontrol;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter
{
	private Context context;
	private List<String> values;
	
	public ListAdapter(Context context , List<String> values)
	{
		this.context = context;
		this.values = values;
		//Log.i("ListAdapter", "settext");
	}

	@Override
	public int getCount()
	{
		return values.size();
	}

	@Override
	public Object getItem(int position)
	{
		return values.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		TextView text = new TextView(context);
		text.setText(values.get(position));
		text.setTextSize(20);
		text.setTextColor(Color.BLACK);
		//Log.i("ListAdaptergetView", "settext");
		return text;
	}
}