package com.hao123.hao;

import com.hao123.adapter.MyHisAdapter;
import com.hao123.adapter.SetAdapter1;
import com.hao123.utils.MyData;

import android.app.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class HistoryActivity extends Activity {
	MyData hisData;
	Button btn_qingkong;
	MyData db;
	Cursor cursor;
	int flag = 0;
	ListView hisView;
	MyHisAdapter hisadapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lishijilu);
		hisView = (ListView) findViewById(R.id.hislist);
		btn_qingkong = (Button) findViewById(R.id.qingkong);
		btn_qingkong.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				// TODO Auto-generated method stub
				hisData = new MyData(HistoryActivity.this);
				hisData.delteAll();
				cursor = hisData.findAll();
				hisadapter.notifyDataSetChanged();
				cursor.close();
				hisData.close();
			}
		});

	}

	@Override
	protected void onResume() {
		flag = SetAdapter1.flag;
		// TODO Auto-generated method stub
		db = new MyData(this);
		hisadapter = new MyHisAdapter(this, db, flag);
		hisadapter.notifyDataSetChanged();
		hisView.setAdapter(hisadapter);
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		if (db != null) {
			db.close();
		}

		super.onPause();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
				&& event.getRepeatCount() == 0) {
			Intent intent = new Intent(HistoryActivity.this, hao123activity.class);
			startActivity(intent);
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
