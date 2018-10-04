package com.demo.adddeleteactivity;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class LybActivity extends Activity{
	EditText title,lyb,lypeople,tel;
	Button tj;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lyb);
//		findView();
//	}
//	private void findView() {
		title = (EditText) findViewById(R.id.title);
		lyb = (EditText) findViewById(R.id.lyb);
		lypeople = (EditText) findViewById(R.id.lypeople);
		tel = (EditText) findViewById(R.id.tel);
		tj = (Button) findViewById(R.id.tj);
		tj.setOnClickListener(new View.OnClickListener() {

			
			@Override
				public void onClick(View v) {
				if (title.getText().toString().length() > 0
						&& lyb.getText().toString().length() > 0&&lypeople.getText().toString().length() > 0&&
						tel.getText().toString().length()>0) {
					Register(title.getText().toString(), lyb.getText()
							.toString(), lypeople.getText().toString(), tel.getText().toString());
					System.out.println("1"+lyb.getText()
							.toString());
				} else {
					Toast.makeText(LybActivity.this, "留言不能为空",
							Toast.LENGTH_SHORT).show();
				}
				}}
			);
	}
		
	

			
		
	
	
	
	private void Register(String string, String string2,String string3,String string4) {

		/**
		 * 注册用户
		 */
		HttpUtils http = new HttpUtils();
		RequestParams params = new RequestParams();
		http.configRequestRetryCount(0);
		http.configCurrentHttpCacheExpiry(10000);// 设置超时时间
		http.configTimeout(10000);// 配置时间链接超时
		http.configSoTimeout(10000);// 配置socket时间连接溢出
		params.addBodyParameter("title", string);
		params.addBodyParameter("lywork", string2);
		
		params.addBodyParameter("lypeople",string3);
		
		params.addBodyParameter("tel",string4);
		http.send(HttpMethod.POST, Constant.url + "/demo/addLyb", params,
				new RequestCallBack<String>() {
					@Override
					public void onFailure(HttpException arg0, String arg1) {

					}

					@SuppressWarnings("rawtypes")
					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						String type = arg0.result;
						try {
							JSONObject json = new JSONObject(type);
							String optString = json.optString("data");
							Boolean success = json.optBoolean("success");
							if (success) {

							}
							Toast.makeText(LybActivity.this, optString,
									Toast.LENGTH_SHORT).show();
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
	}
}
