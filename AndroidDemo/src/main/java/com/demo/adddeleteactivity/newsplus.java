package com.demo.adddeleteactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class newsplus extends Activity{
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newsplus);
 Intent intent =getIntent();
 String title=intent.getStringExtra("title");
 String nr=intent.getStringExtra("nr");
 String fbz=intent.getStringExtra("time");
 TextView text1=(TextView)findViewById(R.id.textView1);
 text1.setText(title);
 TextView nr1=(TextView)findViewById(R.id.textView2);
 nr1.setText(nr);
 TextView fbz1 =(TextView)findViewById(R.id.textView3);
 fbz1.setText(fbz);
}}
