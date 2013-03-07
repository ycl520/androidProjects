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
	// 布局转换
	LayoutInflater inflater;
	// 进度条
	FrameLayout fra_probar;
	LinearLayout linear;
	// 控制无痕浏览的开关
	int flag;
	// 五个菜单按钮
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
	// 用于接受mainactivity传值
	Intent maiIntent;
	String mainurl;
	// 用于接受oftenacivity传值
	Intent oftenIntent;
	String oftenurl;
	// 用于接受hisacivity传值
	Intent hisIntent;
	String hisurl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newweb);
		// 进度条
		fra_probar = (FrameLayout) findViewById(R.id.frameLayout1);
		fra_probar.setVisibility(View.VISIBLE);
		// 初始化webview
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
		// 添加菜单布局
		linear.addView(Semenu);
		// 上一页
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
		// 下一页
		btn_seright.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (webview.canGoForward()) {

					webview.goForward();

				}else{
					Toast.makeText(Newweb.this, "已经是最后一页", Toast.LENGTH_SHORT).show();
					btn_seright.setEnabled(false);
				}
			}
		});
		// 历史记录
		btn_sehis.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Newweb.this, WebHistoryActivity.class);
				startActivity(intent);
				//startActivityForResult(intent,0);

			}
		});
		// 刷新
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
		// 控制无痕
		flag = SetAdapter1.flagnull;
		maiIntent = getIntent();
		// 从主activity传url
		mainurl = maiIntent.getStringExtra("mainurl");
		if (mainurl != null) {
			loadurl = mainurl;
			webview.loadUrl(loadurl);
			webview.setWebChromeClient(new WebChromeClient() {

				@Override
				public void onReceivedTitle(WebView view, String title) {
					titles = title;
					// 对often数据库进行操作
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
				// 控制进度条的消失时间
				if (fra_probar.getVisibility() == View.GONE) {
					fra_probar.setVisibility(View.VISIBLE);
				}
				return false;
			}

			// 页面异常处理
			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				alertDialog.setTitle("网址没有找到");
				alertDialog.setMessage(description);
				alertDialog.show();
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				// 加载完数据进度条自动消失
				fra_probar.setVisibility(View.GONE);
				// 打开网络视频
				if (url.indexOf(".3gp") != -1 || url.indexOf(".mp4") != -1
						|| url.indexOf(".flv") != -1) {
					Intent intent = new Intent(Intent.ACTION_VIEW, Uri
							.parse(url));
					view.getContext().startActivity(intent);
					// "android.intent.action.VIEW"
				}
				// 对历史记录数据进行操作
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

	// 初始化webview
	public void initWebView() {
		webview = (WebView) findViewById(R.id.seweb);

		// //从主oftenactivity传url
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
		// 从历史记录activity传值
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
		// 优先使用缓存：
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