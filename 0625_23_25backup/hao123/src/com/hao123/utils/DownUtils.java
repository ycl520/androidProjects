package com.hao123.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.DownloadListener;

public class DownUtils implements DownloadListener {
	Context context;
public DownUtils(Context context){
	this.context=context;
}
	@Override
	public void onDownloadStart(String url, String userAgent,
			String contentDisposition, String mimetype, long contentLength) {
		// TODO Auto-generated method stub
		Uri uri = Uri.parse(url);  
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);  
        context.startActivity(intent); 
        
	}

}
