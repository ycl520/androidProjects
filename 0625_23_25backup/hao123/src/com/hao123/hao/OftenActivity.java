package com.hao123.hao;




import com.hao123.adapter.OftenAdapter;
import com.hao123.adapter.SetAdapter1;

import com.hao123.utils.AnthorBrowser;
import com.hao123.utils.OftenData;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import android.widget.SimpleCursorAdapter;




public class OftenActivity extends Activity {
	GridView gridView;
	//自定义adapter
	OftenAdapter adapter;

	OftenData oftenData;
	Button btn_qingkong;
	OftenData db;
	Cursor cursor;
	int flag;

	OftenAdapter oftenAdapter;
    SimpleCursorAdapter smpleadapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.often);
		
		gridView = (GridView) findViewById(R.id.often_gridview);
		btn_qingkong = (Button) findViewById(R.id.btn_often_qingkong);
		// 清空按钮的操作
		btn_qingkong.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				// TODO Auto-generated method stub
				 db = new OftenData(OftenActivity.this);
				 db.delteAll();
				  cursor.requery();
				oftenAdapter.notifyDataSetChanged();
				
				gridView.setAdapter(oftenAdapter);
				cursor.close();
				db.close();
			}
		});

		
		
	}
	
	@Override
	protected void onResume() {
		flag=SetAdapter1.flag;
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				OftenData oftenData2 = new OftenData(OftenActivity.this);

				 Cursor cursor = oftenData2.findAll();
				cursor.moveToPosition(position);
				String titleString = cursor.getString(cursor
						.getColumnIndex(OftenData.TITLE));
				Cursor cursor2 = oftenData2.findByname(titleString);
				String url2 = null;
				if (cursor2.getCount() != 0) {

					for (cursor2.moveToFirst(); !cursor2.isAfterLast(); cursor2
							.moveToNext()) {

						url2 = cursor2.getString(cursor2
								.getColumnIndex(OftenData.URL));

					}

				}
				cursor2.close();
				cursor.close();
				oftenData2.close();
				if(flag==0){
					AnthorBrowser browser=new AnthorBrowser(OftenActivity.this, url2);
				}else{
					Intent intent = new Intent(OftenActivity.this, Newweb.class);
					intent.putExtra("oftenurl", url2);
					startActivity(intent);

				}
				
			}
		});
		// TODO Auto-generated method stub
		 db=new OftenData(this);
		 cursor=db.findAll();
		oftenAdapter = new OftenAdapter(this,db,cursor);
		
		oftenAdapter.notifyDataSetChanged();
		gridView.setAdapter(oftenAdapter);
		// 单击事件
		
		
		super.onResume();
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
       //oftenAdapter=null;
		if(cursor!=null){
			cursor.close();
		}
		if(db!=null){
			db.close();
		}
		
		
		super.onPause();
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if ( event.getKeyCode() == KeyEvent.KEYCODE_BACK
				&& event.getRepeatCount() == 0) {
			Intent intent =new Intent(OftenActivity.this,hao123activity.class);
			startActivity(intent);
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	

}
