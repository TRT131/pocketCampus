package com.demo.adddeleteactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class loading extends Activity{
		Button xytg,xyxw,yjfk;		//校园通告，校园新闻，意见反馈
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.loading);
			findView();
		}

		private void findView() {
			// TODO Auto-generated method stub
			xytg = (Button) findViewById(R.id.xytg);
			xyxw = (Button) findViewById(R.id.xyxw);
			yjfk = (Button) findViewById(R.id.yjfk);
		xyxw.setOnClickListener(new View.OnClickListener() {

				
				@Override
					public void onClick(View v) {
					Intent intent = new Intent(loading.this,FirstActivity.class);
					startActivity(intent);
					
					}
				});
		xytg.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
				Intent intent = new Intent(loading.this,TgActivity.class);
				startActivity(intent);
				
				}
			});
		yjfk.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			Intent intent = new Intent(loading.this,LybActivity.class);
			startActivity(intent);
			
			}
		});
	
		}
			
}

