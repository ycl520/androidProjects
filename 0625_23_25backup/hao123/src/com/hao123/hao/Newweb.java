package com.hao123.hao;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import android.widget.LinearLayout;

import com.hao123.adapter.SetAdapter1;
import com.hao123.asy.OpraterHisData;
import com.hao123.asy.OpraterOftenData;
import com.hao123.utils.DownUtils;

public class Newweb extends Activity {
	// ����ת��
	LayoutInflater inflater;
	// ������
	FrameLayout fra_probar;
	LinearLayout linear;
	// �����޺�����Ŀ���
	int flag;
	// ����˵���ť
	ImageButton btn_sehis;
	ImageButton btn_sehome;
	ImageButton btn_seleft;
	ImageButton btn_seright;
	ImageButton btn_serefrech;
	WebView webview;
	AlertDialog alertDialog;

	String titles = null;
	String urls = null;
	String loadurl;
	// ���ڽ���mainactivity��ֵ
	Intent maiIntent;
	String mainurl;
	// ���ڽ���oftenacivity��ֵ
	Intent oftenIntent;
	String oftenurl;
	// ���ڽ���hisacivity��ֵ
	Intent hisIntent;
	String hisurl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newweb);
		// ������
		fra_probar = (FrameLayout) findViewById(R.id.frameLayout1);
		fra_probar.setVisibility(View.VISIBLE);
		// ��ʼ��webview
		initWebView();
		inflater = LayoutInflater.from(this);

		linear = (LinearLayout) findViewById(R.id.seMenu);
		alertDialog = new AlertDialog.Builder(this).create();

		final View Semenu = inflater.inflate(R.layout.secondmenu, null);
		btn_sehis = (ImageButton) Semenu.findViewById(R.id.sehistory);
		btn_sehome = (ImageButton) Semenu.findViewById(R.id.sehome);
		btn_seleft = (ImageButton) Semenu.findViewById(R.id.seleft);
		btn_seright = (ImageButton) Semenu.findViewById(R.id.seright);
		btn_serefrech = (ImageButton) Semenu.findViewById(R.id.serefresh);
		btn_seleft.setEnabled(true);
		btn_seright.setEnabled(false);
		// ��Ӳ˵�����
		linear.addView(Semenu);
		// ��һҳ
		btn_seleft.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (webview.canGoBack()) {
					btn_seright.setEnabled(true);
					webview.goBack();

				}else{
					
					finish();
				}
			}
		});
		// ��һҳ
		btn_seright.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (webview.canGoForward()) {

					webview.goForward();

				}else{
					Toast.makeText(Newweb.this, "�Ѿ������һҳ", Toast.LENGTH_SHORT).show();
					btn_seright.setEnabled(false);
				}
			}
		});
		// ��ʷ��¼
		btn_sehis.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Newweb.this, WebHistoryActivity.class);
				startActivity(intent);
				//startActivityForResult(intent,0);

			}
		});
		// ˢ��
		btn_serefrech.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				webview.reload();
			}
		});
		// Home
		btn_sehome.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Newweb.this, hao123activity.class);
				startActivity(intent);

				finish();
			}
		});

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		// �����޺�
		flag = SetAdapter1.flagnull;
		maiIntent = getIntent();
		// ����activity��url
		mainurl = maiIntent.getStringExtra("mainurl");
		if (mainurl != null) {
			loadurl = mainurl;
			webview.loadUrl(loadurl);
			webview.setWebChromeClient(new WebChromeClient() {

				@Override
				public void onReceivedTitle(WebView view, String title) {
					titles = title;
					// ��often���ݿ���в���
					if (mainurl != null && titles != null && flag == 1) {

						OpraterOftenData opraterOftenData = new OpraterOftenData(
								Newweb.this, mainurl, titles);

						opraterOftenData.execute();
						mainurl = null;
					}
				}

			});
		}

		webview.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				// ���ƽ���������ʧʱ��
				if (fra_probar.getVisibility() == View.GONE) {
					fra_probar.setVisibility(View.VISIBLE);
				}
				return false;
			}

			// ҳ���쳣����
			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				alertDialog.setTitle("��ַû���ҵ�");
				alertDialog.setMessage(description);
				alertDialog.show();
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				// ���������ݽ������Զ���ʧ
				fra_probar.setVisibility(View.GONE);
				// ��������Ƶ
				if (url.indexOf(".3gp") != -1 || url.indexOf(".mp4") != -1
						|| url.indexOf(".flv") != -1) {
					Intent intent = new Intent(Intent.ACTION_VIEW, Uri
							.parse(url));
					view.getContext().startActivity(intent);
					// "android.intent.action.VIEW"
				}
				// ����ʷ��¼���ݽ��в���
				if (url != null && titles != null && flag == 1) {

					OpraterHisData opraterHisData = new OpraterHisData(
							Newweb.this, url, titles);
					opraterHisData.execute();
				}

			}

		});

		super.onStart();
	}
	@Override
	protected void onResume() {
		
		super.onResume();
	}

	// ��ʼ��webview
	public void initWebView() {
		webview = (WebView) findViewById(R.id.seweb);

		// //����oftenactivity��url
		oftenIntent = getIntent();
		oftenurl = oftenIntent.getStringExtra("oftenurl");
		if (oftenurl != null) {
			loadurl = oftenurl;
			webview.loadUrl(loadurl);
			webview.setWebChromeClient(new WebChromeClient() {

				@Override
				public void onReceivedTitle(WebView view, String title) {
					titles = title;

				}

			});

		}
		// ����ʷ��¼activity��ֵ
		hisIntent = getIntent();
		hisurl = hisIntent.getStringExtra("hisurl");
		if (hisurl != null) {
			loadurl = hisurl;
			webview.loadUrl(loadurl);
			webview.setWebChromeClient(new WebChromeClient() {

				@Override
				public void onReceivedTitle(WebView view, String title) {
					titles = title;

				}

			});
		}

		// ------------------------------------
		webview.getSettings().setJavaScriptEnabled(true);
		webview.requestFocus();
		// ����ʹ�û��棺
		webview.setDownloadListener(new DownUtils(Newweb.this));
		webview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (webview.canGoBack() && event.getKeyCode() == KeyEvent.KEYCODE_BACK
				&& event.getRepeatCount() == 0) {
			webview.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}