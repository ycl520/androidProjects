package com.hao123.asy;
import com.hao123.utils.OftenBean;
import com.hao123.utils.OftenData;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;

public class OpraterOftenData extends AsyncTask<Void, Void, Void> {
	OftenData oftenData;
	Cursor cursor2;
    String urls;
    String titles;
    Context  context;
    
	public OpraterOftenData(Context context,String url,String title) {
		super();
		this.urls = url;
		this.titles=title;
		this.context=context;
	}
	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		oftenData = new OftenData(context);
		//根据标题查询结果
        cursor2 = oftenData.findByname(titles);
        //如果数据库中有该数据则count+1
		if (cursor2.getCount()!=0) {
			
			for (cursor2.moveToFirst(); !cursor2.isAfterLast(); cursor2
					.moveToNext()) {
				int id2 = cursor2.getInt(cursor2.getColumnIndex(OftenData._ID));
				int count2 = cursor2.getInt(cursor2
						.getColumnIndex(OftenData.COUNT));
				String url2 = cursor2.getString(cursor2
						.getColumnIndex(OftenData.URL));
				String title2 = cursor2.getString(cursor2
						.getColumnIndex(OftenData.TITLE));
				OftenBean often = new OftenBean(id2, url2, title2, count2);
				often.setCount(count2+1);
				oftenData.updata(often);
               }
			cursor2.close();
			oftenData.close();
			
			
		} else {
			
			//如果数据库没有该标题 则插入新的数据
			OftenBean often = new OftenBean();
			often.setCount(1);
			often.setTitle(titles);
			often.setUrl(urls);
			oftenData.insert(often);
	        cursor2.close();
	        oftenData.close();
		}

		return null;
	}
	
	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		if(cursor2!=null){
			cursor2.close();
		}
		if(oftenData!=null){
			oftenData.close();
		}
	}
	

}
