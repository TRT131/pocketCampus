package com.demo.adddeleteactivity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class FirstActivity extends Activity {

	private Spinner spinner;
	private ArrayAdapter<String> adapter;
	private TextView add;
	private static final String[] m = { "学校概况", "组织机构" ,"德育","教育成果"};
	List<News> parseArray;
	private ListView listview;
	ListAdapter listadapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		/**
		 * 获取商品列表
		 */
		getDataList();
		findView();
	}

	private void findView() {

		listview = (ListView) findViewById(R.id.listview);
		listadapter = new ListAdapter(parseArray);
		listview.setAdapter(listadapter);
		/**
		 * 点击修改
		 */
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if (listadapter.getDatas() != null) {
					Intent intent=new Intent(FirstActivity.this,newsplus.class);
					final News datas=listadapter.getDatas().get(arg2);
					intent.putExtra("title",datas.getTitle() );
					intent.putExtra("nr",datas.getC() );
					intent.putExtra("time", datas.getTeacher() );
					startActivity(intent);
				}
			}
		});
		/**
		 * 长按删除
		 */
		listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				if (listadapter.getDatas() != null) {
					deletedialog(listadapter.getDatas().get(arg2));
				}
				/**
				 * 返回值必须为true 不然点击事件也会触发
				 */
				return true;
			}
		});
		/**
		 * 下拉框选择类别
		 */
		spinner = (Spinner) findViewById(R.id.Spinner01);
		// 将可选内容与ArrayAdapter连接起来
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, m);

		// 设置下拉列表的风格
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// 将adapter 添加到spinner中
		spinner.setAdapter(adapter);

		// 添加事件Spinner事件监听
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				if (parseArray != null) {
					List<News> datas = new ArrayList<News>();

					for (int i = 0; i < parseArray.size(); i++) {
						if (m[arg2].equals(parseArray.get(i).getCategory())) {
							datas.add(parseArray.get(i));
						
						}
					}
					listadapter.setDatas(datas);
					listadapter.notifyDataSetChanged();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

		// 设置默认值
		/*spinner.setVisibility(View.VISIBLE);
		add = (TextView) findViewById(R.id.add);
		add.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				addorchangedialog(null);
			}
		});*/
	}

	/**
	 * 查询内容
	 */
	private void getDataList() {
		HttpUtils http = new HttpUtils();
		RequestParams params = new RequestParams();
		http.configRequestRetryCount(0);
		http.configCurrentHttpCacheExpiry(10000);// 设置超时时间
		http.configTimeout(10000);// 配置时间链接超时
		http.configSoTimeout(10000);// 配置socket时间连接溢出
		http.send(HttpMethod.POST, Constant.url + "/demo/getNewsList", params,
				new RequestCallBack<String>() {
					@Override
					public void onFailure(HttpException arg0, String arg1) {

					}

					@SuppressWarnings("rawtypes")
					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						String type = arg0.result;
						try {
							parseArray = JSONArray.parseArray(type, News.class);
							if (parseArray != null) {
								List<News> datas = new ArrayList<News>();

								for (int i = 0; i < parseArray.size(); i++) {
									if (m[spinner.getSelectedItemPosition()]
											.equals(parseArray.get(i)
													.getCategory())) {
										datas.add(parseArray.get(i));
									}
								}
								listadapter.setDatas(datas);
								listadapter.notifyDataSetChanged();
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
	}

	/**
	 * 传入参数不为空则会修改 传入参数为空则是添加
	 */
/*	private AlertDialog addorchangedialog(final News news) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this); // 先得到构造器
		// builder.setTitle("正在更新中，请稍后...."); // 设置标题
		View contentView = LayoutInflater.from(FirstActivity.this).inflate(
				R.layout.dialog_add, null);
		*//**
		 * 下拉框选择类别
		 *//*
		final Spinner spinner2 = (Spinner) contentView
				.findViewById(R.id.Spinner02);

		// 将adapter 添加到spinner中
		spinner2.setAdapter(adapter);

		TextView sure = (TextView) contentView.findViewById(R.id.sure);
		TextView cancel = (TextView) contentView.findViewById(R.id.cancel);
		final EditText edname = (EditText) contentView
				.findViewById(R.id.edname);
		final EditText ednum = (EditText) contentView.findViewById(R.id.ednum);
		final EditText edprice = (EditText) contentView
				.findViewById(R.id.edprice);
		if (news != null) {
			edname.setText(news.getTitle());
			ednum.setText(news.getC());
			//edprice.setText(news.getPrice());
			for (int i = 0; i < m.length; i++) {
				if (m[i].equals(news.getCategory())) {
					spinner2.setSelection(i);
				}
			}
		}
		// 设置默认值
		spinner2.setVisibility(View.VISIBLE);
		builder.setView(contentView);
		// 参数都设置完成了，创建并显示出来
		builder.setCancelable(false);
		final AlertDialog create = builder.create();
		create.show();
		sure.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (edname.getText().toString().length() == 0) {
					Toast.makeText(FirstActivity.this, "名称不能为空",
							Toast.LENGTH_LONG).show();
					return;
				}
				if (ednum.getText().toString().length() == 0) {
					Toast.makeText(FirstActivity.this, "数量不能为空",
							Toast.LENGTH_LONG).show();
					return;
				}
				if (edprice.getText().toString().length() == 0) {
					Toast.makeText(FirstActivity.this, "价格不能为空",
							Toast.LENGTH_LONG).show();
					return;
				}
				if (create != null && create.isShowing()) {
					Data da = new Data();
					da.setCategory(m[spinner2.getSelectedItemPosition()]);
					da.setName(edname.getText().toString());
					if (news != null) {
						da.setId(news.getId());
						addorchange(Constant.url + "/demo/updateNews", da);
					} else {
						addorchange(Constant.url + "/demo/addNews", da);
					}
					create.dismiss();
				}
			}
		});
		cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (create != null && create.isShowing()) {
					create.dismiss();
				}
			}
		});
		return create;
	}*/

	/**
	 * 新增和修改请求
	 */
	private void addorchange(String url, Data data) {
		HttpUtils http = new HttpUtils();
		RequestParams params = new RequestParams();
		http.configRequestRetryCount(0);
		http.configCurrentHttpCacheExpiry(10000);// 设置超时时间
		http.configTimeout(10000);// 配置时间链接超时
		http.configSoTimeout(10000);// 配置socket时间连接溢出
		if (data.getId() != null && !data.getId().equals("")) {
			params.addBodyParameter("id", data.getId());
		}
		params.addBodyParameter("name", data.getName());
		params.addBodyParameter("url", "");
		params.addBodyParameter("category", data.getCategory());
		params.addBodyParameter("location", "");
		http.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {
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
						getDataList();
					}
					Toast.makeText(FirstActivity.this, optString,
							Toast.LENGTH_SHORT).show();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void delete(News news) {
		HttpUtils http = new HttpUtils();
		RequestParams params = new RequestParams();
		http.configRequestRetryCount(0);
		http.configCurrentHttpCacheExpiry(10000);// 设置超时时间
		http.configTimeout(10000);// 配置时间链接超时
		http.configSoTimeout(10000);// 配置socket时间连接溢出
		params.addBodyParameter("id", news.getId());
		http.send(HttpMethod.POST, Constant.url + "/demo/delNews", params,
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
								getDataList();
							}
							Toast.makeText(FirstActivity.this, optString,
									Toast.LENGTH_SHORT).show();
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				});
	}

	/**
	 * 删除
	 */
	private void deletedialog(final News news) {
		AlertDialog.Builder builder = new AlertDialog.Builder(
				FirstActivity.this);

		builder.setTitle("是否删除");
		builder.setPositiveButton("确  定",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
						delete(news);
					}
				});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				dialog.cancel();
			}
		});
		Dialog dialog = builder.create();
		dialog.show();

	}

	class ListAdapter extends BaseAdapter {
		List<News> datas;

		public ListAdapter(List<News> datas) {
			this.datas = datas;
		}

		@Override
		public int getCount() {
			if (datas != null) {
				return datas.size();
			} else {
				return 0;
			}

		}

		public List<News> getDatas() {
			return datas;
		}

		public void setDatas(List<News> datas) {
			this.datas = datas;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int postion, View view, ViewGroup arg2) {
			viewHander holdel;
			if (view == null) {
				holdel = new viewHander();
				view = LayoutInflater.from(FirstActivity.this).inflate(
						R.layout.item_list, null);
				holdel.name01 = (TextView) view.findViewById(R.id.name01);
				holdel.num01 = (TextView) view.findViewById(R.id.num01);
				holdel.price = (TextView) view.findViewById(R.id.price);
				view.setTag(holdel);
			} else {
				holdel = (viewHander) view.getTag();
			}
			News data = datas.get(postion);
			//holdel.name01.setText(data.getTitle());
			holdel.price.setText("标题:" + data.getTitle());
			holdel.num01.setText("内容:" + data.getC());

			return view;
		}

		class viewHander {
			TextView name01;
			TextView num01;
			TextView price;
		}
	}
}
