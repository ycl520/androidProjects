package com.hao123.hao;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;

import android.os.Bundle;

import android.webkit.WebView;
import android.widget.LinearLayout;

import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;


public class hao123activity extends TabActivity {

	TabSpec spec;
	LinearLayout linearLayout;
	WebView webView;
	Intent intent; // Reusable Intent for each tab

	/** Called when the activity is first created. */
	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
        
		Resources res = getResources(); // Resource object to get Drawables
		final TabHost tabHost = getTabHost(); // The activity TabHost
		
		 
		// 第一个TAB
		intent = new Intent(this, MainActivity.class);// 新建一个Intent用作Tab1显示的内容
		spec = tabHost.newTabSpec("ys");// 新建一个 Tab
		spec.setIndicator("首页", res.getDrawable(R.drawable.go_home_press_off));// 设置名称以及图标
		// 设置显示的intent，这里的参数也可以是R.id.xxx
		spec.setContent(intent);
		tabHost.addTab(spec);// 添加进tabHost
		
		//tabHost.setForeground(null);
		// //
		// 第二个TAB
		intent = new Intent(this, OftenActivity.class);// 第二个Intent用作Tab1显示的内容
		spec = tabHost
				.newTabSpec("最常访问")
				// 新建一个 Tab
				.setIndicator("最常访问",
						res.getDrawable(R.drawable.go_menu_press_off))// 设置名称以及图标
				.setContent(intent);// 设置显示的intent，这里的参数也可以是R.id.xxx
		tabHost.addTab(spec);// 添加进tabHost
		// 第三个
		
		intent = new Intent(this, HistoryActivity.class);// 第二个Intent用作Tab1显示的内容
		spec = tabHost
				.newTabSpec("his")
				// 新建一个 Tab
				.setIndicator("历史记录",
						res.getDrawable(R.drawable.menu_fav_unpress_night))// 设置名称以及图标
				.setContent(intent);// 设置显示的intent，这里的参数也可以是R.id.xxx
		tabHost.addTab(spec);// 添加进tabHost
		
		// 第4个
		intent = new Intent(this, SetActivity.class);// 第二个Intent用作Tab1显示的内容
		spec = tabHost
				.newTabSpec("设置")
				// 新建一个 Tab
				.setIndicator("设置",
						res.getDrawable(R.drawable.menu_set_unpress_night))// 设置名称以及图标
				.setContent(intent);// 设置显示的intent，这里的参数也可以是R.id.xxx
		tabHost.addTab(spec);// 添加进tabHost
//		tabHost.setCurrentTab(1);
		tabHost.setCurrentTab(0);
	

	}
}