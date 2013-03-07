package com.hao123.hao;

import java.util.Timer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class WelcomeActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		/** ����������View�ģ����Դ������е����β��ֱ����غ������Ȼ��Ч */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.welcome);

		new Thread() {
			public void run() {
				try {
					Thread.sleep(1000);
					Intent intent = new Intent(WelcomeActivity.this,
							hao123activity.class);
					startActivity(intent);
					finish();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}.start();

	}

}
