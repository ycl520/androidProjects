package com.hao123.adapter;

import com.hao123.hao.R;

import com.hao123.utils.OftenData;

import android.content.Context;
import android.database.Cursor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.ImageView;

import android.widget.TextView;

public class OftenAdapter extends BaseAdapter {
	Context Context;
	OftenData db;
	LayoutInflater inflater;
	
	Cursor cursor;
	int times = 0;
	int times2 = 0;
	String default_image_name = "app_web_browser_sm";

	public OftenAdapter(Context context, OftenData db, Cursor cursor) {
		super();
		Context = context;
		this.db = db;
		this.cursor = cursor;

		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		if (cursor.getCount() != 0) {
			return cursor.getCount();
		} else {
			return 0;
		}
		// TODO Auto-generated method stub

	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if (db.findAll() != null) {
			return db.findAll().getString(position);
		} else {
			return null;
		}
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub

		return position;
	}

	public final class ViewHolder1 {
      public ImageView img;
      public TextView info;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
          ViewHolder1 holder1=null;
          if(convertView==null){
        	  convertView = inflater.inflate(R.layout.often_gridview_item, null);
        	  holder1=new ViewHolder1();
        	// 显示图片
      		// 显示标题
        	  holder1.img=(ImageView) convertView.findViewById(R.id.img);
        	  holder1.info=(TextView) convertView.findViewById(R.id.info);
        	  convertView.setTag(holder1);
        	  
          }else{
        	  holder1=(ViewHolder1)convertView.getTag();
          }
		
		
		

		// cursor = db.findAll();

		cursor.moveToFirst();
		cursor.move(position);
		holder1.info.setText(cursor.getString(cursor.getColumnIndex(OftenData.TITLE)));
		String iconame = cursor.getString(cursor.getColumnIndex(OftenData.URL));

		// 得到简单的网址
		String ss = iconame.substring(43).split("&")[0];
		// 与图片命名相同
		iconame = ss.replaceFirst(String.valueOf(ss.charAt(0)), "m").replace(
				'.', '_');

		Context context = convertView.getContext();
		// 获取资源图片
		int ico_id = context.getResources().getIdentifier(iconame, "drawable",
				context.getPackageName());
		if (ico_id <= 0) {
			ico_id = context.getResources().getIdentifier(default_image_name,
					"drawable", context.getPackageName());
		}
		 holder1.img.setImageResource(ico_id);

		return convertView;
	}

}
