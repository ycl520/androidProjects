package com.hao123.asy;



import com.hao123.utils.HistoryBean;
import com.hao123.utils.MyData;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;

import android.webkit.WebView;



public class OpraterHisData extends AsyncTask<Void, Void, Void> {
	public WebView webview=null;
	MyData hisData;
	Cursor cursor2;
	Cursor cursor;
    String urls;
    String titles;
    Context  context;

	public OpraterHisData(Context context,String url,String title) {
		super();
		this.urls = url;
		this.titles=title;
		this.context=context;
	}
	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		hisData = new MyData(context);
		
        cursor2 = hisData.findByname(titles);
		if (cursor2.getCount()!=0) {
			
			for (cursor2.moveToFirst(); !cursor2.isAfterLast(); cursor2
					.moveToNext()) {
				int id2 = cursor2.getInt(cursor2.getColumnIndex(MyData._ID));
				int count2 = cursor2.getInt(cursor2
						.getColumnIndex(MyData.COUNT));
				String url2 = cursor2.getString(cursor2
						.getColumnIndex(MyData.URL));
				String title2 = cursor2.getString(cursor2
						.getColumnIndex(MyData.TITLE));
				HistoryBean his = new HistoryBean(id2, url2, title2, count2);
				his.setCount(count2+1);
				hisData.updata(his);
               }
			cursor2.close();
			hisData.close();
			
			
		} else {
			
			
			HistoryBean his = new HistoryBean();
			his.setCount(1);
			his.setTitle(titles);
			his.setUrl(urls);
	        hisData.insert(his);
	        cursor2.close();
	        hisData.close();
		}

		return null;
	}
	
	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		if(cursor2!=null){
			cursor2.close();
		}
		if(hisData!=null){
			hisData.close();
		}
		
		
		
	}
	

}
