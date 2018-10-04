package com.demo.adddeleteactivity;
	import org.json.JSONException;
	import org.json.JSONObject;

	import com.gyf.barlibrary.ImmersionBar;
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
	import android.widget.Toast;

	public class RegisterActivity  extends Activity{
		EditText username1,password1,tel1;
		Button register1,cancel;
			
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			ImmersionBar.with(this).init();
			setContentView(R.layout.activity_register);
//			findView();
//		}
//		private void findView() {
			username1 = (EditText) findViewById(R.id.usename1);
			password1 = (EditText) findViewById(R.id.password1);
			tel1 = (EditText) findViewById(R.id.tel1);
			register1 = (Button) findViewById(R.id.register1);
			cancel=(Button) findViewById(R.id.cancel);
			register1.setOnClickListener(new View.OnClickListener() {

				
				@Override
					public void onClick(View v) {
					if (username1.getText().toString().length() > 0
							&& password1.getText().toString().length() > 0&&tel1.getText().toString().length() > 0) {
						Register(username1.getText().toString(), password1.getText()
								.toString(), tel1.getText().toString());
					} else {
						Toast.makeText(RegisterActivity.this, "账号密码不能为空",
								Toast.LENGTH_SHORT).show();
					}
					}}
				);
			
			cancel.setOnClickListener(new View.OnClickListener() {
				
					public void onClick(View v) {
						RegisterActivity.this.finish();
						
					}
					});
		}
				
			
		
		
		
		private void Register(String string, String string2,String string3) {

			/**
			 * 注册用户
			 */
			HttpUtils http = new HttpUtils();
			RequestParams params = new RequestParams();
			http.configRequestRetryCount(0);
			http.configCurrentHttpCacheExpiry(10000);// 设置超时时间
			http.configTimeout(10000);// 配置时间链接超时
			http.configSoTimeout(10000);// 配置socket时间连接溢出
			params.addBodyParameter("name", string);
			params.addBodyParameter("pwd", string2);
			params.addBodyParameter("tel",string3);
			http.send(HttpMethod.POST, Constant.url + "/demo/addUser", params,
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
								Toast.makeText(RegisterActivity.this, optString,
										Toast.LENGTH_SHORT).show();
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
		}
		}
		



