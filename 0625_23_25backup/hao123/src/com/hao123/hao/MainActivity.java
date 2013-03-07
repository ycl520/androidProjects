package com.hao123.hao;





import com.hao123.adapter.SetAdapter1;
import com.hao123.utils.AnthorBrowser;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;

import android.content.Intent;


import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;



import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends Activity {
    //ѡ��������Ŀ���
	int flag;
	// ����Ĳ���
	LinearLayout linearwindow;
	// ת��
	LayoutInflater inflater;
	WebView webview;
	// ������Ϣ
	AlertDialog alertDialog;
	private static final String BASE_URL = "http://m.hao123.com/?vit=h123&from=381e";

	// ����������
	Dialog dialog;
	View mainwebView;
    View mainback;
     Dialog backdialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainupdata);
		
		alertDialog = new AlertDialog.Builder(this).create();
		//��ʼ������
		dialog = new Dialog(MainActivity.this);
		dialog.setContentView(R.layout.dialog);
		dialog.show();

		linearwindow = (LinearLayout) findViewById(R.id.Mainwindow);
		inflater = LayoutInflater.from(MainActivity.this);
		mainwebView = inflater.inflate(R.layout.mainwebview, null);
        //���web����
		linearwindow.addView(mainwebView);
		// ��ʼ��
		webinit();

	}
  @Override
protected void onResume() {
	// TODO Auto-generated method stub
	  flag=SetAdapter1.flag;
	  webview.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				// ���µ�activity
				if (!url.equals(BASE_URL)) {
					
						
						if(flag==0){
							AnthorBrowser browser=new AnthorBrowser(MainActivity.this, url);
//					
						}else{
							Intent intent = new Intent(MainActivity.this, Newweb.class);

							intent.putExtra("mainurl", url);
							startActivity(intent);
							
						}
					return true;
				}
				return false;
			}

			// ҳ��������

			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				dialog.dismiss();

			}

			// ������Ϣ��ʾ
			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				alertDialog.setTitle("��ַû���ҵ�");
				alertDialog.setMessage(description);
				alertDialog.show();
			}

		});
	super.onResume();
}
	private void webinit() {
		webview = (WebView) mainwebView.findViewById(R.id.web);
		webview.getSettings().setJavaScriptEnabled(true);
		webview.requestFocus();
		webview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		webview.loadUrl(BASE_URL);

		
	}

	// ���˰�������
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
				&& event.getRepeatCount() == 0) {
			backdialog=new Dialog(MainActivity.this);
						backdialog.setTitle("��ʾ��");
			mainback=inflater.inflate(R.layout.mainbackdialog, null);
		    Button btn_yes=(Button)mainback.findViewById(R.id.yes);
		    Button btn_no=(Button)mainback.findViewById(R.id.no);
		    
		    backdialog.setContentView(mainback);
		    backdialog.show();
			btn_yes.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					backdialog.dismiss();
					finish();
				}
			});
			btn_no.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					backdialog.dismiss();
				}
			});
			
			
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
