package com.demo.adddeleteactivity;

import com.demo.adddeleteactivity.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * 未用到 需要自定义引导页时使用
 * @author Administrator
 *
 */
public class Fragment1 extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View inflate = inflater.inflate(R.layout.item_frament1, container,false);
		return inflate;
	}
}
