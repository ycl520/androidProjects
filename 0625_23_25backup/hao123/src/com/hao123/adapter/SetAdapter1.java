package com.hao123.adapter;

import java.util.ArrayList;
import java.util.List;


import com.hao123.hao.R;

import com.hao123.utils.MyData;

import android.content.Context;

import android.database.Cursor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.ImageButton;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class SetAdapter1 extends BaseAdapter {
	Context Context;
	
    MyData db;
    LayoutInflater inflater;
    TextView textView;
    ImageButton btn;
    Cursor cursor;
    //控制使用外部浏览器打开
    public static int flag=1;
    //控制使用无痕浏览
    public static int flagnull=1;
    CheckBox checkBox;
    List <String>data;
	public SetAdapter1(Context context) {
		super();
		Context = context;
		data=new ArrayList<String>();
		inflater=LayoutInflater.from(context);
	}
	public void add(String str){
		data.add(str);
	}
	public int getBrowser(){
		return flag;
	}
	public int getNull(){
		return flagnull;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		convertView=inflater.inflate(R.layout.setadapterone, null);
		textView=(TextView)convertView.findViewById(R.id.title1);
		checkBox=(CheckBox)convertView.findViewById(R.id.checkBox1);
		if(position>1){
			checkBox.setVisibility(View.INVISIBLE);
		}
		textView.setText(data.get(position));
		if(position==0){
			checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					if(isChecked){
						flag=0;
					}else{
						flag=1;
					}
					
				}
			});
		}
		if(position==1){
			checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					if(isChecked){
						flagnull=0;
						
					}else{
						flagnull=1;
					}
				}
			});
			
		}
		
		return convertView;
	}
	

}
