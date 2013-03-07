package com.hao123.hao;

import com.hao123.adapter.SetAdapter1;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;


public class SetActivity extends Activity {
    ListView listBorwser;
    
 
    SetAdapter1 adapter1;
    
   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set);
		listBorwser=(ListView)findViewById(R.id.listView1);
		adapter1=new SetAdapter1(this);
		//adapter1.add("");
		adapter1.add("使用外部浏览器");
		adapter1.add("无痕浏览");
		adapter1.add("关于软件");
//		adapter1.add("意见反馈");
		adapter1.add("使用帮助");
		adapter1.add("给我打分");
		adapter1.add("检查更新");
		
		listBorwser.setAdapter(adapter1);
		
		
        listBorwser.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if(position==3){
					Intent intent=new Intent(SetActivity.this,AboutActivity.class);
					startActivity(intent);
				}
				
			}
		});
		
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if ( event.getKeyCode() == KeyEvent.KEYCODE_BACK
				&& event.getRepeatCount() == 0) {
			Intent intent =new Intent(SetActivity.this,hao123activity.class);
			startActivity(intent);
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	

}
