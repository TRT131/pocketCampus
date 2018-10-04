package com.demo.adddeleteactivity;

import org.json.JSONException;
import org.json.JSONObject;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText username, password;
	Button login;
	TextView register;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findView();
	}

	private void findView() {
		// TODO Auto-generated method stub
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		login = (Button) findViewById(R.id.login);
		register = (TextView) findViewById(R.id.register);
		login.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (username.getText().toString().length() > 0
						&& password.getText().toString().length() > 0) {
					Login(username.getText().toString(), password.getText()
							.toString());
				} else {
					Toast.makeText(MainActivity.this, "�˺����벻��Ϊ��",
							Toast.LENGTH_SHORT).show();
				}

			}

		});
		register.setOnClickListener(new View.OnClickListener() {

			
		@Override
			public void onClick(View v) {
			Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
			startActivity(intent);
			
			}
		});
	}

	private void Login(String string, String string2) {
		/**
		 * ��¼
		 */
		HttpUtils http = new HttpUtils();
		RequestParams params = new RequestParams();
		http.configRequestRetryCount(0);
		http.configCurrentHttpCacheExpiry(10000);// ���ó�ʱʱ��
		http.configTimeout(10000);// ����ʱ�����ӳ�ʱ
		http.configSoTimeout(10000);// ����socketʱ���������
		params.addBodyParameter("name", string);
		params.addBodyParameter("pwd", string2);
		http.send(HttpMethod.POST, Constant.url + "/demo/appLogin", params,
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
								startActivity(new Intent(MainActivity.this,
										loading.class));
							}
							Toast.makeText(MainActivity.this, optString,
									Toast.LENGTH_SHORT).show();
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});

	}


}
