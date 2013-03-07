package com.hao123.adapter;

import com.hao123.hao.Newweb;
import com.hao123.hao.R;
import com.hao123.utils.AnthorBrowser;
import com.hao123.utils.MyData;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.ImageButton;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyHisAdapter extends BaseAdapter {
	Context Context;
    MyData db;
    LayoutInflater inflater;
   // TextView textView;
    //ImageButton btn;
    Cursor cursor;
    int flag;
    
	public MyHisAdapter(Context context,MyData db,int flag) {
		super();
		Context = context;
		this.db=db;
		this.flag=flag;
		inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if( db.findAll().getCount()!=0){
			return db.findAll().getCount();
		}else{
			return 0;
		}
		
	}

	@Override
	public Object getItem(int position) {
		if(db.findAll()!=null){
			return db.findAll().getString(position);
		}else{
			return null;
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	public final class ViewHolder{
		public TextView textView;
		public ImageButton btn;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder=null;
		if(convertView==null){
			convertView=inflater.inflate(R.layout.hisadapter, null);
			holder=new ViewHolder();
			holder.textView=(TextView)convertView.findViewById(R.id.hisdata);
			holder.btn=(ImageButton)convertView.findViewById(R.id.hisdelete);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder)convertView.getTag();
		}
		
//		textView=(TextView)convertView.findViewById(R.id.hisdata);
//		btn=(ImageButton)convertView.findViewById(R.id.hisdelete);
		cursor=db.findAll();
		cursor.moveToFirst();
		cursor.move(position);
		holder.textView.setText(cursor.getString(cursor.getColumnIndex(MyData.TITLE)));
		
		holder.btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//cursor=db.findAll();
				getMycursor();
				cursor.moveToFirst();
				cursor.move(position);
				String title=cursor.getString(cursor.getColumnIndex(MyData.TITLE));
				
				db.deleteByid(title);
			    MyHisAdapter.this.notifyDataSetChanged();  
			    cursor.requery();
			    cursor.close();
				db.close();
			}
		});
		holder.textView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				getMycursor();
				cursor.moveToFirst();
				cursor.move(position);
				String url=cursor.getString(cursor.getColumnIndex(MyData.URL));
				if(flag==0){
					AnthorBrowser browser=new AnthorBrowser(Context, url);
				}else{
					Intent intent=new Intent(Context,Newweb.class);
					intent.putExtra("hisurl", url);
					Context.startActivity(intent);
				}
				
				
				 cursor.close();
					db.close();
			}
		});

		return convertView;
	}
	private void getMycursor(){
		cursor=db.findAll();
	
	}

}
