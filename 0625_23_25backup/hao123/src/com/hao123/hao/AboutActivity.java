package com.hao123.hao;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends Activity {
	Button btn;
	TextView tv;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.about);
	btn=(Button)findViewById(R.id.but_fanhui);
	tv=(TextView)findViewById(R.id.context);
	btn.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
		}
	});
//	java.io.BufferedReader reader;
//	try {
//		reader = new java.io.BufferedReader(
//			     new java.io.FileReader("file:///assets/testfile.txt"));
//		 String s = reader.readLine();
//		   StringBuffer sb = new StringBuffer();
//		   while (s != null)
//		   {
//		    sb.append(s);
//		    sb.append("\r\n");
//		    s = reader.readLine();
//		   }
//		   tv.setText(sb.toString());
//		   Log.i("********", sb.toString());
//		   reader.close();
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//		  
		
		
	
}
}
