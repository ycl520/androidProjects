package com.hao123.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class AnthorBrowser {
	Context context;
	String url;
	public AnthorBrowser(Context context, String url) {
		super();
		this.context = context;
		this.url = url;
		Intent intent= new Intent();        
	    intent.setAction("android.intent.action.VIEW");    
	    Uri content_url = Uri.parse(url);   
	    intent.setData(content_url);  
	    context.startActivity(intent);
	}
	
}
