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
		
		 
		// ��һ��TAB
		intent = new Intent(this, MainActivity.class);// �½�һ��Intent����Tab1��ʾ������
		spec = tabHost.newTabSpec("ys");// �½�һ�� Tab
		spec.setIndicator("��ҳ", res.getDrawable(R.drawable.go_home_press_off));// ���������Լ�ͼ��
		// ������ʾ��intent������Ĳ���Ҳ������R.id.xxx
		spec.setContent(intent);
		tabHost.addTab(spec);// ��ӽ�tabHost
		
		//tabHost.setForeground(null);
		// //
		// �ڶ���TAB
		intent = new Intent(this, OftenActivity.class);// �ڶ���Intent����Tab1��ʾ������
		spec = tabHost
				.newTabSpec("�����")
				// �½�һ�� Tab
				.setIndicator("�����",
						res.getDrawable(R.drawable.go_menu_press_off))// ���������Լ�ͼ��
				.setContent(intent);// ������ʾ��intent������Ĳ���Ҳ������R.id.xxx
		tabHost.addTab(spec);// ��ӽ�tabHost
		// ������
		
		intent = new Intent(this, HistoryActivity.class);// �ڶ���Intent����Tab1��ʾ������
		spec = tabHost
				.newTabSpec("his")
				// �½�һ�� Tab
				.setIndicator("��ʷ��¼",
						res.getDrawable(R.drawable.menu_fav_unpress_night))// ���������Լ�ͼ��
				.setContent(intent);// ������ʾ��intent������Ĳ���Ҳ������R.id.xxx
		tabHost.addTab(spec);// ��ӽ�tabHost
		
		// ��4��
		intent = new Intent(this, SetActivity.class);// �ڶ���Intent����Tab1��ʾ������
		spec = tabHost
				.newTabSpec("����")
				// �½�һ�� Tab
				.setIndicator("����",
						res.getDrawable(R.drawable.menu_set_unpress_night))// ���������Լ�ͼ��
				.setContent(intent);// ������ʾ��intent������Ĳ���Ҳ������R.id.xxx
		tabHost.addTab(spec);// ��ӽ�tabHost
//		tabHost.setCurrentTab(1);
		tabHost.setCurrentTab(0);
	

	}
}